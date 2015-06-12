package org.javahispano.javaleague.client.application;

import javax.inject.Inject;

import org.javahispano.javaleague.client.place.NameTokens;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.Presenter.RevealType;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class ApplicationPresenter extends
		Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
		
	interface MyView extends View {
	}

	@ContentSlot
	// public static final Type<RevealContentHandler<?>> SLOT_SetMainContent =
	// new Type<>();
	public static final GwtEvent.Type<RevealContentHandler<?>> SLOT_SetMainContent = new GwtEvent.Type<RevealContentHandler<?>>();

	@ProxyStandard
	interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	@Inject
	ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

		// Making the window scroll to top on every page change
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Scheduler.get().scheduleDeferred(new Command() {
					@Override
					public void execute() {
						Window.scrollTo(0, 0);
					}
				});
			}
		});
	}

}