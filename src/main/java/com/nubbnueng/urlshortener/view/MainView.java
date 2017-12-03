package com.nubbnueng.urlshortener.view;

import java.nio.charset.StandardCharsets;


import com.google.common.hash.Hashing;
import com.nubbnueng.urlshortener.model.URL;
import com.nubbnueng.urlshortener.repository.URLRepository;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
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
		
	// URLRepository can't @Autowired here.
	URLRepository urlRepository; 
	
	final static String hostUrl = "http://localhost:8080/";
	
	VerticalLayout mainLayout = new VerticalLayout();
	
	Label headerLabel;
	TextField urlTextField;
	Button shortenUrlButton;
	Link shortUrlLink;
	
	public MainView(URLRepository urlRepository) {
		this.urlRepository = urlRepository;	
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addHeader();
		addForm();
		addResult();
	}

//	@Override
//	protected void init(VaadinRequest request) {
//		mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
//		addHeader();
//		addForm();
//		addResult();
////		setContent(mainLayout);
//		
//	}


	private void addHeader() {
		headerLabel = new Label("URL SHORTENER APPLICATION");
		headerLabel.setStyleName(ValoTheme.LABEL_H1);
//		mainLayout.addComponent(headerLabel);
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
			String shortUrlSuffix = Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).toString();
			String shortUrl = hostUrl + shortUrlSuffix;
			
			urlTextField.clear();
			shortUrlLink.setCaption(shortUrl);
			shortUrlLink.setResource(new ExternalResource(shortUrl));
			shortUrlLink.setVisible(true);
			
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
		addComponent(formLayout);
//		mainLayout.addComponent(formLayout);
	}

	private void addResult() {		
		shortUrlLink = new Link("", new ExternalResource(hostUrl));
		shortUrlLink.setVisible(false);
		
		addComponent(shortUrlLink);
//		mainLayout.addComponent(shortUrlLink);
	}


}