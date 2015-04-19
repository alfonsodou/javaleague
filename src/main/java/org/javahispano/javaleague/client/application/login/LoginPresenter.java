/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import org.gwtbootstrap3.client.ui.ImageAnchor;
import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.gin.ClientGinjector;
import org.javahispano.javaleague.client.place.NameTokens;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * @author adou
 *
 */
public class LoginPresenter extends
		Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> {
	
	public final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

	@NameToken(NameTokens.LOGIN)
	@ProxyCodeSplit
	public interface MyProxy extends ProxyPlace<LoginPresenter> {
	}

	public interface MyView extends View, Editor<Credentials> {
		ImageAnchor getGoogleLink();
	}

	@Inject
	public LoginPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

		view.getGoogleLink().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest.Builder myRequestBuilder = new PlaceRequest.Builder().nameToken(NameTokens.HOME);
				ginjector.getPlaceManager().revealPlace(myRequestBuilder.build());
			}
		});
	}
}
