package com.nubbnueng.urlshortener.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class TemplateView extends VerticalLayout implements View{
	
	VerticalLayout contentLayout;
	Navigator navigator;
	
	public TemplateView(VerticalLayout contentLayout, Navigator navigator) {
		this.contentLayout = contentLayout;
		this.navigator = navigator;
		setSpacing(true);
		setWidth("100%");
		setMargin(false);
		setDefaultComponentAlignment(Alignment.TOP_CENTER);
		
		addNavigatorBar();
		addContent();
	}
	
	private void addNavigatorBar() {
		HorizontalLayout navbarLayout = new HorizontalLayout();
		navbarLayout.setStyleName(ValoTheme.LAYOUT_CARD);
		navbarLayout.setWidth("100%");
		
		HorizontalLayout navbar = new HorizontalLayout();
		navbar.setMargin(false);
		navbar.setSpacing(false);
		
		Button homeNav = new Button("Home");
		homeNav.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		homeNav.addClickListener(click -> navigator.navigateTo(""));
		
		Button loginNav = new Button("Login");
		loginNav.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		loginNav.addClickListener(click -> navigator.navigateTo("login"));
		
		navbar.addComponents(homeNav,loginNav);
		navbarLayout.addComponent(navbar);
		
		addComponent(navbarLayout);
	}
	
	private void addContent() {
		addComponent(contentLayout);
	}
}
