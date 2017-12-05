package com.nubbnueng.urlshortener.view;

import java.util.List;

import com.nubbnueng.urlshortener.model.URL;
import com.nubbnueng.urlshortener.service.URLService;
import com.nubbnueng.urlshortener.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class StatisticsView extends VerticalLayout implements View {
	
	private URLService urlService;
	private UserService userService;
	
	private Grid<URL> resultGrid;
	private Label errorMessage;
	
	public StatisticsView(URLService urlService, UserService userService) {
		this.urlService = urlService;
		this.userService = userService;
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setSpacing(true);
		addHeader();
		addErrorMessage();
		addResult();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if (userService.isLogin()) {
			errorMessage.setVisible(false);
			resultGrid.setVisible(true);
			resultGrid.setItems(urlService.findAll());
		} else {
			errorMessage.setVisible(true);
			resultGrid.setVisible(false);
		}
	}
	
	private void addHeader() {
		Label headerLabel = new Label("Statistics of urls");
		headerLabel.setStyleName(ValoTheme.LABEL_H1);
		addComponent(headerLabel);
	}
	
	private void addErrorMessage() {
		errorMessage = new Label("Please login first !");
		errorMessage.setStyleName(ValoTheme.LABEL_FAILURE);
		errorMessage.setVisible(false);
		
		addComponent(errorMessage);
	}
	
	private void addResult() {
		resultGrid = new Grid<>();
		
		List<URL> allUrl = urlService.findAll();
		
		resultGrid.setWidth("100%");
		
		resultGrid.setItems(allUrl);
		resultGrid.addColumn(URL::getId).setCaption("#");
		resultGrid.addColumn(URL::getOriginalUrl).setCaption("Original URL");
		resultGrid.addColumn(URL::getShortUrlSuffix).setCaption("Short URL Suffix");
		resultGrid.addColumn(URL::getCount).setCaption("Click count");
		
		addComponent(resultGrid);
	}
}
