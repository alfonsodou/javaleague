/**
 * 
 */
package org.javahispano.javaleague.server.dispatch;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.javahispano.javaleague.domain.AppUser;
import org.javahispano.javaleague.server.service.AppUserDao;
import org.javahispano.javaleague.shared.dispatch.RegisterUserAction;
import org.javahispano.javaleague.shared.dispatch.RegisterUserResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author adou
 *
 */
public class RegisterUserHandler implements
		ActionHandler<RegisterUserAction, RegisterUserResult> {

	private Provider<HttpServletRequest> requestProvider;
	private ServletContext servletContext;
	private AppUserDao appUserDao = new AppUserDao();

	@Inject
	RegisterUserHandler(ServletContext servletContext,
			Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;
	}

	@Override
	public RegisterUserResult execute(RegisterUserAction arg0,
			ExecutionContext arg1) throws ActionException {
		RegisterUserResult registerUserResult = new RegisterUserResult("OK!");
		AppUser appUser = null;
		try {
			appUser = appUserDao.getByProperty("email", arg0.getEmail());
			if (appUser == null) {
				appUser = new AppUser(arg0.getEmail(), arg0.getPassword(),
						arg0.getUserName());
				appUserDao.put(appUser);
			} else {
				registerUserResult.setResponse("KO!");
			}
		} catch (Exception e) {
			registerUserResult.setResponse("KO!");
		}
		return registerUserResult;
	}

	@Override
	public Class<RegisterUserAction> getActionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo(RegisterUserAction arg0, RegisterUserResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
