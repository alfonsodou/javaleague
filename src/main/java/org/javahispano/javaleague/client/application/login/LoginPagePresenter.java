/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.place.NameTokens;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

/**
 * @author adou
 *
 */
public class LoginPagePresenter extends
		Presenter<LoginPagePresenter.MyView, LoginPagePresenter.MyProxy> implements
		LoginUiHandlers {
    public interface MyView extends View {
    }	
	
    @ProxyStandard
    @NameToken(NameTokens.LOGIN)
    //@ProxyCodeSplit
	public
    interface MyProxy extends ProxyPlace<LoginPagePresenter> {
    }

    @Inject
    LoginPagePresenter(EventBus eventBus,
                      MyView view,
                      MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
    }

	@Override
	public void doLogin(String password, String email) {
		// TODO Auto-generated method stub
		
	}
}

