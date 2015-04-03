package org.javahispano.javaleague.client.application;


import org.javahispano.javaleague.client.application.general.HomePresenter;
import org.javahispano.javaleague.client.application.general.HomeView;
import org.javahispano.javaleague.client.application.login.LoginPresenter;
import org.javahispano.javaleague.client.application.login.LoginView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        // Main Application View
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);

        // General Views
        bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
        
        // Login View
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class, LoginPresenter.MyProxy.class);
    }
}
