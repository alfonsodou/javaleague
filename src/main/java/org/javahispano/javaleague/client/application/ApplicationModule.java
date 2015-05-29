package org.javahispano.javaleague.client.application;

import org.javahispano.javaleague.client.application.home.HomePagePresenter;
import org.javahispano.javaleague.client.application.home.HomePageView;
import org.javahispano.javaleague.client.application.login.LoginPagePresenter;
import org.javahispano.javaleague.client.application.login.LoginPageView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        //install(new HomeModule());
        //install(new LoginModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
        
        bindPresenter(HomePagePresenter.class, HomePagePresenter.MyView.class, HomePageView.class,
                HomePagePresenter.MyProxy.class);
        
        bindPresenter(LoginPagePresenter.class, LoginPagePresenter.MyView.class, LoginPageView.class,
                LoginPagePresenter.MyProxy.class);        
    }
}