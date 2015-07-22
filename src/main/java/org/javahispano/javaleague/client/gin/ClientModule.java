package org.javahispano.javaleague.client.gin;

import org.javahispano.javaleague.client.application.ApplicationModule;
import org.javahispano.javaleague.client.dispatch.rest.AppRestDispatchHooks;
import org.javahispano.javaleague.client.dispatch.rest.RestInterceptorRegistry;
import org.javahispano.javaleague.client.dispatch.rpc.AppRpcDispatchHooks;
import org.javahispano.javaleague.client.place.NameTokens;
import org.javahispano.javaleague.client.place.NavigationTracker;
import org.javahispano.javaleague.client.security.SecurityModule;
import org.javahispano.javaleague.shared.api.ApiPaths;

import com.arcbees.analytics.client.AnalyticsModule;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.dispatch.rpc.client.interceptor.RpcInterceptorRegistry;
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
		// GWTP libraries
		install(new DefaultModule.Builder().tokenFormatter(
				RouteTokenFormatter.class).build());
		install(new ApplicationModule());
		
		 install(new RestDispatchAsyncModule.Builder()
		  .dispatchHooks(AppRestDispatchHooks.class)
		  .interceptorRegistry(RestInterceptorRegistry.class).build());
		 
		/*RestDispatchAsyncModule.Builder dispatchBuilder = new RestDispatchAsyncModule.Builder();
		install(dispatchBuilder.build());*/
		bindConstant().annotatedWith(RestApplicationPath.class).to(
				ApiPaths.ROOT);

		install(new RpcDispatchAsyncModule.Builder()
				.dispatchHooks(AppRpcDispatchHooks.class)
				.interceptorRegistry(RpcInterceptorRegistry.class).build());

		// CarStore modules
		install(new SecurityModule());

		// Load and inject CSS resources at startup
		bind(ResourceLoader.class).asEagerSingleton();

		// DefaultPlaceManager Places
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);
		bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.HOME);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(
				NameTokens.UNAUTHORIZED);

		install(new AnalyticsModule.Builder("UX-XXXX-Y").build());
		bind(NavigationTracker.class).asEagerSingleton();

	}
}