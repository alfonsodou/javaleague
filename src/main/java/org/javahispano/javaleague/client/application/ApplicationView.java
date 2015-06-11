package org.javahispano.javaleague.client.application;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers>
		implements ApplicationPresenter.MyView {

	interface Binder extends UiBinder<Widget, ApplicationView> {
	}

	@UiField
	SimplePanel contentContainer;

	@Inject
	ApplicationView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == ApplicationPresenter.SLOT_SetMainContent) {
			contentContainer.setWidget(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	@UiHandler("loginButton")
	public void onLoginClick(ClickEvent event) {
		if (getUiHandlers() != null)
			getUiHandlers().doShowLoginPage();
	}

	@UiHandler("downloadButton")
	public void onDownloadClick(ClickEvent event) {
		if (getUiHandlers() != null)
			getUiHandlers().doShowDownloadPage();
	}

	@UiHandler("rulesButton")
	public void onRulesClick(ClickEvent event) {
		if (getUiHandlers() != null)
			getUiHandlers().doShowRulesPage();
	}

	@UiHandler("tournamentButton")
	public void onTournamentClick(ClickEvent event) {
		if (getUiHandlers() != null)
			getUiHandlers().doShowTournamentPage();
	}
}