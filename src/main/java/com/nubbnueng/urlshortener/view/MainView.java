package com.nubbnueng.urlshortener.view;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.hash.Hashing;
import com.nubbnueng.urlshortener.model.URL;
import com.nubbnueng.urlshortener.repository.URLRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")
public class MainView extends UI {
		
	@Autowired
	URLRepository urlRepository;
	
	final static String hostUrl = "http://localhost:8080/";
	
	VerticalLayout mainLayout = new VerticalLayout();
	
	Label headerLabel;
	TextField urlTextField;
	Button shortenUrlButton;
	Link shortUrlLink;

	@Override
	protected void init(VaadinRequest request) {
		mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addHeader();
		addForm();
		addResult();
		setContent(mainLayout);
		
	}


	private void addHeader() {
		headerLabel = new Label("URL SHORTENER APPLICATION");
		headerLabel.setStyleName(ValoTheme.LABEL_H1);
		mainLayout.addComponent(headerLabel);
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
			String shortUrlSuffix = Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).toString();
			String shortUrl = hostUrl + shortUrlSuffix;
			
			urlTextField.clear();
			shortUrlLink.setCaption(shortUrl);
			shortUrlLink.setResource(new ExternalResource(shortUrl));
			
			try {
				urlRepository.save(new URL(shortUrlSuffix, originalUrl));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});

		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.addComponents(urlTextField, shortenUrlButton);
		formLayout.setExpandRatio(urlTextField, 1);
		formLayout.setWidth("60%");
		mainLayout.addComponent(formLayout);
	}

	private void addResult() {		
		shortUrlLink = new Link(hostUrl, new ExternalResource(hostUrl));

		mainLayout.addComponent(shortUrlLink);
	}


}