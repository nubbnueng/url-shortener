package com.nubbnueng.urlshortener.view;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout implements View {

	Label headerLabel;
	TextField urlTextField;
	Button shortenUrlButton;
	Link shortUrlLink;
	
    @Override
    public void enter(ViewChangeEvent event) {
    }
	
	public MainView() {
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addHeader();
		addForm();
		addResult();
	}

	private void addHeader() {
		headerLabel = new Label("URL SHORTENER APPLICATION");
		headerLabel.setStyleName(ValoTheme.LABEL_H1);
		addComponent(headerLabel);
	}

	private void addForm() {
		urlTextField = new TextField();
		urlTextField.focus();
		urlTextField.setWidth("100%");
		
		shortenUrlButton = new Button("SHORTEN !");
		shortenUrlButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		shortenUrlButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		
		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.addComponents(urlTextField, shortenUrlButton);
		formLayout.setExpandRatio(urlTextField, 1);
		formLayout.setWidth("60%");
		addComponent(formLayout);
	}
	
	private void addResult() {
		final String hostUrl = "http://localhost:8080/";
		shortUrlLink = new Link(hostUrl, new ExternalResource(hostUrl));

		addComponent(shortUrlLink);
	}

}