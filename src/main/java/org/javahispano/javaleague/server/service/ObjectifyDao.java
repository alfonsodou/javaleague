package org.javahispano.javaleague.server.service;

import static org.javahispano.javaleague.server.dao.objectify.OfyService.factory;
import static org.javahispano.javaleague.server.dao.objectify.OfyService.ofy;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.javahispano.javaleague.shared.exception.TooManyResultsException;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

/**
 * Generic DAO for use with Objectify
 * 
 * @author turbomanage
 * 
 * @param <T>
 */
public class ObjectifyDao<T> {
	
	private static Logger logger = Logger.getLogger(ObjectifyDao.class.getName());

	static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC
			| Modifier.TRANSIENT;

	protected Class<T> clazz;

	public ObjectifyDao() {
		Type genericSuperclass = getClass().getGenericSuperclass();
		// Allow this class to be safely instantiated with or without a
		// parameterized type
		if (genericSuperclass instanceof ParameterizedType)
			clazz = (Class<T>) ((ParameterizedType) genericSuperclass)
					.getActualTypeArguments()[0];
	}

	public Key<T> put(T entity)

	{
		return ofy().save().entity(entity).now();
	}

	public Map<Key<T>, T> putAll(Iterable<T> entities) {
		return ofy().save().entities(entities).now();
	}

	public void delete(T entity) {
		ofy().delete().entity(entity).now();
	}

	public void deleteKey(Key<T> entityKey) {
		ofy().delete().key(entityKey).now();
	}

	public void deleteAll(Iterable<T> entities) {
		ofy().delete().entities(entities).now();
	}

	public void deleteKeys(Iterable<Key<T>> keys) {
		ofy().delete().keys(keys).now();
	}

	public T get(Long id) throws EntityNotFoundException {
		return ofy().load().type(this.clazz).id(id).now();
	}

	public T get(Key<T> key) throws EntityNotFoundException {
		return ofy().load().key(key).now();
	}

	public Map<Key<T>, T> get(Iterable<Key<T>> keys) {
		return ofy().load().keys(keys);
	}

	public List<T> listAll() {
		Query<T> q = ofy().load().type(clazz);
		return q.list();
	}

	/**
	 * Convenience method to get all objects matching a single property
	 * 
	 * @param propName
	 * @param propValue
	 * @return T matching Object
	 * @throws TooManyResultsException
	 */
	public T getByProperty(String propName, Object propValue)
			throws TooManyResultsException {
		Query<T> q = ofy().load().type(clazz);
		q.filter(propName, propValue);
		Iterator<T> fetch = q.limit(2).list().iterator();
		if (!fetch.hasNext()) {
			return null;
		}
		T obj = fetch.next();
		if (fetch.hasNext()) {
			throw new TooManyResultsException(q.toString()
					+ " returned too many results");
		}
		return obj;
	}

	public List<T> listByProperty(String propName, Object propValue) {
		Query<T> q = ofy().load().type(clazz);
		q.filter(propName, propValue);
		return q.list();
	}

	public List<Key<T>> listKeysByProperty(String propName, Object propValue) {
		Query<T> q = ofy().load().type(clazz);
		q.filter(propName, propValue);
		return q.keys().list();
	}

	public T getByExample(T exampleObj) throws TooManyResultsException {
		Query<T> q = buildQueryByExample(exampleObj);
		Iterator<T> fetch = q.limit(2).list().iterator();
		if (!fetch.hasNext()) {
			return null;
		}
		T obj = fetch.next();
		if (fetch.hasNext()) {
			throw new TooManyResultsException(q.toString()
					+ " returned too many results");
		}
		return obj;
	}

	public List<T> listByExample(T exampleObj) {
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		return queryByExample.list();
	}

	public Key<T> getKey(Long id) {
		return factory().keys().keyOf(
				ofy().load().type(this.clazz).id(id).now());
	}

	public Key<T> key(T obj) {
		return factory().keys().keyOf(obj);
	}

	public List<T> listChildren(Object parent) {
		return ofy().load().type(clazz).ancestor(parent).list();
	}

	public List<Key<T>> listChildKeys(Object parent) {
		return ofy().load().type(clazz).ancestor(parent).keys().list();
	}

	protected Query<T> buildQueryByExample(T exampleObj) {
		Query<T> q = ofy().load().type(clazz);

		// Add all non-null properties to query filter
		for (Field field : clazz.getDeclaredFields()) {
			// Ignore transient, embedded, array, and collection properties
			if (field.isAnnotationPresent(Transient.class)
					// || (field.isAnnotationPresent(Embedded.class))
					|| (field.getType().isArray())
					|| (Collection.class.isAssignableFrom(field.getType()))
					|| ((field.getModifiers() & BAD_MODIFIERS) != 0))
				continue;

			field.setAccessible(true);

			Object value;
			try {
				value = field.get(exampleObj);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			if (value != null) {
				q.filter(field.getName(), value);
			}
		}

		return q;
	}
}
