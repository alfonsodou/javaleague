/**
 * 
 */
package org.javahispano.javaleague.client.gin;

import javax.inject.Singleton;

import org.javahispano.javaleague.client.application.ApplicationModule;
import org.javahispano.javaleague.client.place.NameTokens;
import org.javahispano.javaleague.shared.dto.CurrentUserDto;

import com.googlecode.objectify.ObjectifyFilter;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

/**
 * @author adou
 *
 */
public class JavaleagueModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule());
		install(new ApplicationModule());
				
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);
		bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.HOME);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(
				NameTokens.HOME);
		
		bind(CurrentUserDto.class).asEagerSingleton();
		bind(ObjectifyFilter.class).in(Singleton.class);
	}
}