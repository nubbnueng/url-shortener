package com.nubbnueng.urlshortener.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class TemplateView extends VerticalLayout implements View{
	
	VerticalLayout contentLayout;
	
	public TemplateView(VerticalLayout contentLayout) {
		this.contentLayout = contentLayout;
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
		navbarLayout.setMargin(false);
		navbarLayout.setSpacing(false);
		
		Button loginNav = new Button("Login");
		
		navbarLayout.addComponent(loginNav);
		
		addComponent(navbarLayout);
	}
	
	private void addContent() {
		addComponent(contentLayout);
	}
}
