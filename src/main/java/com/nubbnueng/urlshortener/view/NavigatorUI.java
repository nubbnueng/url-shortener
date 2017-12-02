package com.nubbnueng.urlshortener.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
public class NavigatorUI extends UI {

	Navigator navigator;
	
	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("URL SHORTENER APPLICATION");
		
		// Create a navigator to control the views
		navigator = new Navigator(this, this);
		
		// Create and register the views
		navigator.addView("", new MainView());
	}

}
