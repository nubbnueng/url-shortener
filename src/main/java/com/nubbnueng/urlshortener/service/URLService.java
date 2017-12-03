package com.nubbnueng.urlshortener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nubbnueng.urlshortener.model.URL;
import com.nubbnueng.urlshortener.repository.URLRepository;

@Service
public class URLService {
	@Autowired
	private URLRepository urlRepository;
	
	public String getOriginalUrl(String suffix) {
		URL item = urlRepository.findByshortUrlSuffix(suffix);
		return item.getOriginalUrl();
	}
	
	public void saveUrl(URL url) {
		urlRepository.save(url);
	}
	
	public List<URL> findAll() {
		return urlRepository.findAll();
	}
}
