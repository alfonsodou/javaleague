/**
 * 
 */
package org.javahispano.javaleague.domain;

import java.util.Date;

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
	private Date modified;

	@PrePut
	void onPersist(PutContext context) {
		this.version++;
		this.modified = new Date();
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

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	
}
