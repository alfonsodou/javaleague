package org.javahispano.javaleague.client.gin;

import org.javahispano.javaleague.client.application.ApplicationModule;
import org.javahispano.javaleague.client.dispatch.AppRestDispatchHooks;
import org.javahispano.javaleague.client.place.NameTokens;
import org.javahispano.javaleague.client.place.NavigationTracker;

import com.arcbees.analytics.client.AnalyticsModule;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.dispatch.rest.client.interceptor.RestInterceptorRegistry;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

/**
 * See more on setting up the PlaceManager on <a
 * href="// See more on: https://github.com/ArcBees/GWTP/wiki/PlaceManager"
 * >DefaultModule's > DefaultPlaceManager</a>
 */
public class ClientModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new DefaultModule.Builder().tokenFormatter(
				RouteTokenFormatter.class).build());
		install(new RpcDispatchAsyncModule());
		install(new ApplicationModule());

		// DefaultPlaceManager Places
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);
		bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.HOME);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(
				NameTokens.HOME);

		install(new RestDispatchAsyncModule.Builder()
				.dispatchHooks(AppRestDispatchHooks.class)
				.interceptorRegistry(RestInterceptorRegistry.class).build());

		install(new AnalyticsModule.Builder("UX-XXXX-Y").build());
		bind(NavigationTracker.class).asEagerSingleton();
	}
}