package org.javahispano.javaleague.client.application.authenticate;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AuthenticateModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(AuthenticatePagePresenter.class,
				AuthenticatePagePresenter.MyView.class,
				AuthenticatePageView.class,
				AuthenticatePagePresenter.MyProxy.class);
	}

}
