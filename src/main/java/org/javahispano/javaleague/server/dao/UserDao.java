package org.javahispano.javaleague.server.dao;

import static org.javahispano.javaleague.server.dao.objectify.OfyService.ofy;

import org.javahispano.javaleague.shared.domain.User;

public class UserDao {
    public UserDao() {
        
    }

    public User findByGoogleId(String googleId) {
        return ofy().load().type(User.class).filter("googleId =", googleId).first().now();
    }

	public User put(User user) {
		ofy().save().entity(user).now();
		return user;
	}
}
