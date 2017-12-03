package com.nubbnueng.urlshortener.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nubbnueng.urlshortener.service.URLService;

@Controller
public class URLController {

	@Autowired
	URLService urlService;
	private String originalUrl;

	@RequestMapping(value = "/shorturl/{suffix}", method = RequestMethod.GET)
	public void redirectToUrl(@PathVariable String suffix, HttpServletResponse resp) throws Exception {
		originalUrl = urlService.getOriginalUrl(suffix);

		if (originalUrl != null || !originalUrl.isEmpty()) {
			urlService.increaseClickCount(suffix);
			resp.addHeader("Location", originalUrl);
			resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
