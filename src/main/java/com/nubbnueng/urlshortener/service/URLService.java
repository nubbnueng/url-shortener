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
		try {
			URL item = urlRepository.findByshortUrlSuffix(suffix);
			return item.getOriginalUrl();
		} catch (NullPointerException e) {
			return "";
		}

	}

	public void saveUrl(String shortUrlSuffix, String originalUrl) {
		try {
			// check is duplicate url?
			if(getOriginalUrl(shortUrlSuffix) == null || getOriginalUrl(shortUrlSuffix).isEmpty()) {
				urlRepository.save(new URL(shortUrlSuffix, originalUrl));
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<URL> findAll() {
		return urlRepository.findAll();
	}
	
	public void increaseClickCount(String suffix) {
		URL item = urlRepository.findByshortUrlSuffix(suffix);
		item.setCount(item.getCount()+1);
		urlRepository.save(item);
	}
}
