package com.nubbnueng.urlshortener.view;

import com.nubbnueng.urlshortener.service.UserService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class TemplateView extends VerticalLayout implements View {

	protected UserService userService;
	protected View contentLayout;
	
	protected Button loginNav;
	protected Button logoutNav;
	protected Button statisticNav;

	public TemplateView(View contentLayout, UserService userService) {
		this.contentLayout = contentLayout;
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
		homeNav.addClickListener(click -> getUI().getCurrent().getNavigator().navigateTo(""));
		
		statisticNav = new Button("Statitics");
		statisticNav.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		statisticNav.setVisible(userService.isLogin());
		statisticNav.addClickListener(click -> getUI().getCurrent().getNavigator().navigateTo("statistics"));

		loginNav = new Button("Login");
		loginNav.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		loginNav.setVisible(!userService.isLogin());
		loginNav.addClickListener(click -> getUI().getCurrent().getNavigator().navigateTo("login"));

		logoutNav = new Button("Logout");
		logoutNav.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		logoutNav.setVisible(userService.isLogin());
		logoutNav.addClickListener(click -> {
			userService.logout();
			getUI().getCurrent().getNavigator().navigateTo("");
		});

		navbar.addComponents(homeNav, statisticNav, loginNav, logoutNav);
		navbarLayout.addComponent(navbar);

		addComponent(navbarLayout);
	}

	private void addContent() {
		addComponent((VerticalLayout)contentLayout);
	}
}
