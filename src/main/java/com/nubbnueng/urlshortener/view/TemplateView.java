package com.nubbnueng.urlshortener.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.nubbnueng.urlshortener.service.UserService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class TemplateView extends VerticalLayout implements View {

	protected UserService userService;
	protected View contentLayout;
	protected Navigator navigator;
	
	protected Button loginNav;
	protected Button logoutNav;
	protected Button statisticNav;

	public TemplateView(View contentLayout, Navigator navigator, UserService userService) {
		this.contentLayout = contentLayout;
		this.navigator = navigator;
		this.userService = userService;

		setMargin(false);
		setDefaultComponentAlignment(Alignment.TOP_CENTER);

		addNavigatorBar();
		addContent();
	}
	
    @Override
    public void enter(ViewChangeEvent event) {
        refreshNavbar();
        contentLayout.enter(event);
    }

	private void refreshNavbar() {
		loginNav.setVisible(!userService.isLogin());
		logoutNav.setVisible(userService.isLogin());
		statisticNav.setVisible(userService.isLogin());
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
		
		statisticNav = new Button("Statitics");
		statisticNav.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		statisticNav.setVisible(userService.isLogin());
		statisticNav.addClickListener(click -> navigator.navigateTo("statistic"));

		loginNav = new Button("Login");
		loginNav.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		loginNav.setVisible(!userService.isLogin());
		loginNav.addClickListener(click -> navigator.navigateTo("login"));

		logoutNav = new Button("Logout");
		logoutNav.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		logoutNav.setVisible(userService.isLogin());
		logoutNav.addClickListener(click -> {
			userService.logout();
			navigator.navigateTo("");
		});

		navbar.addComponents(homeNav, statisticNav, loginNav, logoutNav);
		navbarLayout.addComponent(navbar);

		addComponent(navbarLayout);
	}

	private void addContent() {
		addComponent((VerticalLayout)contentLayout);
	}
}
