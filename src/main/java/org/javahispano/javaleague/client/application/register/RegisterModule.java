package org.javahispano.javaleague.client.application.register;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RegisterModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(RegisterPagePresenter.class, RegisterPagePresenter.MyView.class, RegisterPageView.class,
                RegisterPagePresenter.MyProxy.class);
    }
}