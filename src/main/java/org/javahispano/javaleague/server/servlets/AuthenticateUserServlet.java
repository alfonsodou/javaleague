/**
 * 
 */
package org.javahispano.javaleague.server.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javahispano.javaleague.domain.AppUser;
import org.javahispano.javaleague.server.service.AppUserDao;
import org.javahispano.javaleague.server.utils.ServletUtils;

/**
 * @author adou
 * 
 */
public class AuthenticateUserServlet extends HttpServlet {
	private static final Logger log = Logger
			.getLogger(AuthenticateUserServlet.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppUserDao appUserDao = new AppUserDao();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		AppUser appUserTemp = null;
		Date now = new Date();
		try {
			appUserTemp = appUserDao.getByProperty("email", req.getParameter("email"));
			if ((appUserTemp != null)
					&& (req.getParameter("token")
							.equals(appUserTemp.getTokenActivate()))) {
				appUserTemp.setActive(true);

				appUserDao.put(appUserTemp);

				/*
				 * All done.
				 */
				resp.sendRedirect(ServletUtils.getBaseUrl(req)
						+ "#/authenticate/{true}");
				/*
				 * resp.sendRedirect(LoginHelper.getApplicationURL(req) +
				 * "/authuser.jsp");
				 */

			} else {
				resp.sendRedirect(ServletUtils.getBaseUrl(req)
						+ "#/authenticate/{false}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warning(e.getMessage());

		}
	}

	/**
	 * Agrega o quita minutos a una fecha dada. Para quitar minutos hay que
	 * sumarle valores negativos.
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutesToDate(Date date, int minutes) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.add(Calendar.MINUTE, minutes);
		return calendarDate.getTime();
	}
}
