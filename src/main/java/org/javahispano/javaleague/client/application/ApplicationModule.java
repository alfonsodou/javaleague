package org.javahispano.javaleague.client.application;

import org.javahispano.javaleague.client.application.home.HomeModule;
import org.javahispano.javaleague.client.application.login.LoginModule;
import org.javahispano.javaleague.client.application.register.RegisterModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());
        install(new LoginModule());
        install(new RegisterModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}