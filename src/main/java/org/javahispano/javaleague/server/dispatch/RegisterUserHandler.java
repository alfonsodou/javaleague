/**
 * 
 */
package org.javahispano.javaleague.server.dispatch;

import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.javahispano.javaleague.server.domain.AppUser;
import org.javahispano.javaleague.server.service.AppUserDao;
import org.javahispano.javaleague.server.utils.ServletUtils;
import org.javahispano.javaleague.server.utils.SessionIdentifierGenerator;
import org.javahispano.javaleague.server.utils.VelocityHelper;
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
				SessionIdentifierGenerator userTokenGenerator = new SessionIdentifierGenerator();
				appUser = new AppUser(arg0.getEmail(), arg0.getPassword(),
						arg0.getUserName());
				appUser.setTokenActivate(userTokenGenerator.nextSessionId());
				appUserDao.put(appUser);
				VelocityContext context = new VelocityContext();
				context.put("username", appUser.getUserName());
				context.put("url",
						ServletUtils.getBaseUrl() + "authenticateUser?token="
								+ appUser.getTokenActivate() + "&email="
								+ appUser.getEmail());

				VelocityEngine ve = VelocityHelper.getVelocityEngine();

				// Finds template in WEB-INF/classes
				Template template = ve.getTemplate("emailTemplate.vm");

				StringWriter writer = new StringWriter();
				template.merge(context, writer);

				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("javaleague@gmail.com",
						"Administrador javaleague"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						appUser.getEmail(), appUser.getUserName()));
				msg.setSubject("Bienvenido a javaleague!");

				msg.setContent(writer.toString(), "text/html; charset=utf-8");
				msg.setSentDate(new Date());

				Transport.send(msg);

			} else {
				registerUserResult.setResponse("KO!");
			}
		} catch (Exception e) {
			registerUserResult.setResponse("KO!");
			if (appUser != null) {
				appUserDao.delete(appUser);
			}
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
