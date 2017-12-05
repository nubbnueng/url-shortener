package com.nubbnueng.urlshortener.view;

import java.nio.charset.StandardCharsets;


import com.google.common.hash.Hashing;
import com.nubbnueng.urlshortener.service.URLService;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
public class MainView extends VerticalLayout implements View {
		
	// URLService can't @Autowired here.
	private URLService urlService; 
	
	private VerticalLayout mainLayout = new VerticalLayout();
	
	private Label headerLabel;
	private TextField urlTextField;
	private Button shortenUrlButton;
	private Link shortUrlLink;
	
	public MainView(URLService urlService) {
		this.urlService = urlService;	
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addHeader();
		addForm();
		addResult();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		urlTextField.clear();
		shortUrlLink.setVisible(false);
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
		shortenUrlButton.addClickListener(click -> {
			String originalUrl = urlTextField.getValue();
			String shortUrl = urlService.makeShortUrl(originalUrl);

			urlTextField.clear();
			shortUrlLink.setCaption(shortUrl);
			shortUrlLink.setResource(new ExternalResource(shortUrl));
			shortUrlLink.setVisible(true);
			
			urlService.saveUrl(shortUrl, originalUrl);
		});

		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.addComponents(urlTextField, shortenUrlButton);
		formLayout.setExpandRatio(urlTextField, 1);
		formLayout.setWidth("60%");
		addComponent(formLayout);
	}

	private void addResult() {		
		shortUrlLink = new Link("", new ExternalResource(urlService.getHostUrl()));
		shortUrlLink.setVisible(false);
		
		addComponent(shortUrlLink);
	}
}
