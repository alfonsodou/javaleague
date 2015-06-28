/**
 * 
 */
package org.javahispano.javaleague.domain;

import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PutContext;
import com.googlecode.objectify.annotation.Id;

/**
 * @author alfonso
 *
 */
public class DatastoreObject {
	@Id
	private Long id = null;
	private Integer version = 0;

	/**
	 * Auto-increment version # whenever persisted
	 */
	@PrePut
	void onPersist(PutContext context) {
		this.version++;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
