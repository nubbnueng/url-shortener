package com.nubbnueng.urlshortener.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginView extends VerticalLayout implements View {
	
	public LoginView() {
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setSpacing(true);
		addHeader();
		addLoginForm();
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
		loginFormLayout.setStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		
		TextField usernameTextField = new TextField("Username");
		usernameTextField.setWidth("100%");
		PasswordField passwordField = new PasswordField("Password");
		passwordField.setWidth("100%");
		Button loginButton = new Button("Login");
		
		loginFormLayout.addComponents(usernameTextField, passwordField, loginButton);
		addComponent(loginFormLayout);		
	}
	
	
}
