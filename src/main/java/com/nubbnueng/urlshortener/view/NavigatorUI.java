package com.nubbnueng.urlshortener.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.nubbnueng.urlshortener.service.URLService;
import com.nubbnueng.urlshortener.service.UserService;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")

public class NavigatorUI extends UI {

	@Autowired
	private URLService urlService;
	
	@Autowired
	private UserService userService;
	
	private Navigator navigator;
	
	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("URL SHORTENER APPLICATION");
		
		// Create a navigator to control the views
		navigator = new Navigator(this, this);
		
		TemplateView homeView = new TemplateView(new MainView(urlService), navigator, userService);
		TemplateView statisticView = new TemplateView(new StatisticsView(urlService), navigator, userService);
		TemplateView loginView = new TemplateView(new LoginView(userService), navigator, userService);
		
		// Create and register the views
		navigator.addView("", homeView);
		navigator.addView("statistics", statisticView);
		navigator.addView("login", loginView);
	}

}
