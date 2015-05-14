package org.javahispano.javaleague.server.dao;

import static org.javahispano.javaleague.server.dao.objectify.OfyService.ofy;

import org.javahispano.javaleague.shared.domain.Task;

public class TaskDao  {
    protected TaskDao() {
        
    }

    public Task get(Long id) {
        return ofy().load().type(Task.class).id(id).now();
    }    
    
    public Integer findTotalCount() {
        return ofy().load().type(Task.class).count();
    }
}
