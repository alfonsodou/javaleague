/**
 * 
 */
package org.javahispano.javaleague.client.rpc;

import org.javahispano.javaleague.client.model.Credential;
import org.javahispano.javaleague.client.model.SocialUser;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author adou
 *
 */
public interface OAuthLoginServiceAsync {

	public void     getAuthorizationUrl(Credential credential, AsyncCallback<String> callback);
	public void verifySocialUser(Credential credential, AsyncCallback<SocialUser> callback);
	public void fetchMe(String sessionId, AsyncCallback<SocialUser> callback);
	public void     getAccessToken(String sessionId, AsyncCallback<String> callback);
	public void       logout(String sessionId, AsyncCallback<Void> callback);
	

}
