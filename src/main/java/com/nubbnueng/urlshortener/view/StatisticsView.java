package com.nubbnueng.urlshortener.view;

import java.util.List;

import com.nubbnueng.urlshortener.model.URL;
import com.nubbnueng.urlshortener.service.URLService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class StatisticsView extends VerticalLayout implements View {
	
	private URLService urlService;
	private Grid<URL> resultGrid;
	
	public StatisticsView(URLService urlService) {
		this.urlService = urlService;
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setSpacing(true);
		addHeader();
		addResult();		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		resultGrid.setItems(urlService.findAll());
	}
	
	private void addHeader() {
		Label headerLabel = new Label("Statistics of urls");
		headerLabel.setStyleName(ValoTheme.LABEL_H1);
		addComponent(headerLabel);
	}
	
	private void addResult() {
		resultGrid = new Grid<>();
		
		List<URL> allUrl = urlService.findAll();
		
		resultGrid.setWidth("100%");
		
		resultGrid.setItems(allUrl);
		resultGrid.addColumn(URL::getId).setCaption("#");
		resultGrid.addColumn(URL::getOriginalUrl).setCaption("Original URL");
		resultGrid.addColumn(URL::getShortUrlSuffix).setCaption("Short URL Suffix");
		resultGrid.addColumn(URL::getCount).setCaption("#Click");
		
		addComponent(resultGrid);
	}
}
