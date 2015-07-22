package org.javahispano.javaleague.server.guice;

import javax.inject.Singleton;

import org.javahispano.javaleague.server.api.ApiModule;
import org.javahispano.javaleague.server.authentication.BCryptPasswordSecurity;
import org.javahispano.javaleague.server.authentication.PasswordSecurity;
import org.javahispano.javaleague.server.dispatch.DispatchModule;

import com.google.inject.AbstractModule;
import com.googlecode.objectify.ObjectifyFilter;

public class ServerModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ApiModule());
		install(new DispatchModule());

		bind(PasswordSecurity.class).to(BCryptPasswordSecurity.class).in(
				Singleton.class);
		bind(ObjectifyFilter.class).in(Singleton.class);
	}
}