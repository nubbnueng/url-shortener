package com.nubbnueng.urlshortener.view;

import com.nubbnueng.urlshortener.service.UserService;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginView extends VerticalLayout implements View {
	
	private UserService userService;
	
	private TextField usernameTextField;
	private PasswordField passwordField;
	private Label errorTextField;
	
	public LoginView(UserService userService) {
		this.userService = userService;
		
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setSpacing(true);
		addHeader();
		addLoginForm();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		usernameTextField.clear();
		passwordField.clear();
		errorTextField.setVisible(false);
	}
	
	private void addHeader() {
		Label headerLabel = new Label("Login");
		headerLabel.setStyleName(ValoTheme.LABEL_H1);
		addComponent(headerLabel);
	}
	
	private void addLoginForm() {
		VerticalLayout loginFormLayout = new VerticalLayout();
		loginFormLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		loginFormLayout.setWidth("30%");
		loginFormLayout.setStyleName(ValoTheme.LAYOUT_CARD);
		
		errorTextField = new Label("Login failed, Please try again.");
		errorTextField.setVisible(false);
		errorTextField.setStyleName(ValoTheme.LABEL_FAILURE);
		
		usernameTextField = new TextField("Username");
		usernameTextField.setWidth("100%");
		
		passwordField = new PasswordField("Password");
		passwordField.setWidth("100%");
		
		Button loginButton = new Button("Login");
		loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		loginButton.addClickListener(click -> {
			if(userService.doAuthenticate(usernameTextField.getValue(), passwordField.getValue())) {
				errorTextField.setVisible(false);
				getUI().getCurrent().getNavigator().navigateTo("statistics");
			} else {
				errorTextField.setVisible(true);
			}
			
		});
		
		loginFormLayout.addComponents(errorTextField, usernameTextField, passwordField, loginButton);
		addComponent(loginFormLayout);		
	}
}
