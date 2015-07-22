package org.javahispano.javaleague.client.application;


import org.javahispano.javaleague.client.application.authenticate.AuthenticateModule;
import org.javahispano.javaleague.client.application.home.HomeModule;
import org.javahispano.javaleague.client.application.login.LoginModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());
        install(new LoginModule());
        install(new AuthenticateModule());
        install(new UnauthorizedModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}