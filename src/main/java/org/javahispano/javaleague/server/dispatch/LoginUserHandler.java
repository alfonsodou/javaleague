/**
 * 
 */
package org.javahispano.javaleague.server.dispatch;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.javahispano.javaleague.domain.AppUser;
import org.javahispano.javaleague.server.service.AppUserDao;
import org.javahispano.javaleague.shared.dispatch.LoginUserAction;
import org.javahispano.javaleague.shared.dispatch.LoginUserResult;
import org.javahispano.javaleague.shared.exception.TooManyResultsException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author alfonso
 *
 */
public class LoginUserHandler implements
		ActionHandler<LoginUserAction, LoginUserResult> {
	private Provider<HttpServletRequest> requestProvider;
	private ServletContext servletContext;
	private AppUserDao appUserDao = new AppUserDao();

	@Inject
	LoginUserHandler(ServletContext servletContext,
			Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;
	}

	@Override
	public LoginUserResult execute(LoginUserAction arg0, ExecutionContext arg1)
			throws ActionException {
		LoginUserResult loginUserResult = new LoginUserResult("OK!");
		AppUser appUser = null;
		try {
			appUser = appUserDao.getByProperty("email", arg0.getEmail());
			if (appUser == null) {
				loginUserResult.setResponse("KO!");
			}
		} catch (TooManyResultsException e) {
			loginUserResult.setResponse("KO!");
		}

		return loginUserResult;
	}

	@Override
	public Class<LoginUserAction> getActionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo(LoginUserAction arg0, LoginUserResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
