package org.javahispano.javaleague.client.application.login;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class LoginModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(LoginPagePresenter.class, LoginPagePresenter.MyView.class, LoginPageView.class,
                LoginPagePresenter.MyProxy.class);
        
        bind(LoginUiHandlers.class).to(LoginPagePresenter.class);
    }
}