/**
 * 
 */
package org.javahispano.javaleague.server.dao;

import static org.javahispano.javaleague.server.dao.objectify.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.javahispano.javaleague.shared.domain.AppUser;

/**
 * @author adou
 * 
 */
public class AppUserDao {

	private static Logger logger = Logger.getLogger(AppUserDao.class.getName());

	public AppUserDao() {
		super();
	}

	public void save(AppUser appUser) {
		ofy().save().entity(appUser).now();
	}

	public void remove(Long id) {
		try {
			AppUser appUser = fetch(id);
			ofy().delete().entity(appUser).now();
		} catch (Exception e) {
			e.printStackTrace();
			String message = e.getMessage() + " :: ";
			logger.warning(buildStackTrace(e, message));
		}
	}

	public void remove(AppUser appUser) {
		try {
			ofy().delete().entity(appUser).now();
		} catch (Exception e) {
			e.printStackTrace();
			String message = e.getMessage() + " :: ";
			logger.warning(buildStackTrace(e, message));
		}
	}

	public AppUser fetch(Long id) {
		AppUser appUser = null;
		try {
			appUser = ofy().load().type(AppUser.class).id(id).now();
		} catch (Exception e) {
			e.printStackTrace();
			String message = e.getMessage() + " :: ";
			logger.warning(buildStackTrace(e, message));
		}

		return appUser;
	}

	public AppUser findByEmail(String email) {
		AppUser appUser = null;
		try {
			appUser = ofy().load().type(AppUser.class).filter("email", email)
					.first().now();
		} catch (Exception e) {
			e.printStackTrace();
			String message = e.getMessage() + " :: ";
			logger.warning(buildStackTrace(e, message));
		}

		return appUser;
	}

	public List<AppUser> getAuthUser(Boolean active) {
		List<AppUser> listAppUser = new ArrayList<AppUser>();

		try {

			listAppUser = ofy().load().type(AppUser.class)
					.filter("active", active).list();
		} catch (Exception e) {
			e.printStackTrace();
			String message = e.getMessage() + " :: ";
			logger.warning(buildStackTrace(e, message));
		}

		return listAppUser;
	}



	private String buildStackTrace(Throwable t, String log) {
		// return "disabled";
		if (t != null) {
			log += t.getClass().toString();
			log += t.getMessage();
			//
			StackTraceElement[] stackTrace = t.getStackTrace();
			if (stackTrace != null) {
				StringBuffer trace = new StringBuffer();

				for (int i = 0; i < stackTrace.length; i++) {
					trace.append(stackTrace[i].getClassName() + "."
							+ stackTrace[i].getMethodName() + "("
							+ stackTrace[i].getFileName() + ":"
							+ stackTrace[i].getLineNumber());
				}

				log += trace.toString();
			}
			//
			Throwable cause = t.getCause();
			if (cause != null && cause != t) {

				log += buildStackTrace(cause, "CausedBy:\n");

			}
		}
		return log;
	}
}
