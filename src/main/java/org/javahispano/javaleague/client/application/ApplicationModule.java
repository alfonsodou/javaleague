package org.javahispano.javaleague.client.application;


import org.javahispano.javaleague.client.application.general.HomePresenter;
import org.javahispano.javaleague.client.application.general.HomeView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        // Main Application View
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);

        // General Views
        bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
    }
}
