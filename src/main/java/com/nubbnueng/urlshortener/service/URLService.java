package com.nubbnueng.urlshortener.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.nubbnueng.urlshortener.model.URL;
import com.nubbnueng.urlshortener.repository.URLRepository;

@Service
public class URLService {
	@Autowired
	private URLRepository urlRepository;
	
	private final String hostUrl = "http://localhost:8080/";

	public String getOriginalUrl(String suffix) {
		try {
			URL item = urlRepository.findByshortUrlSuffix(suffix);
			return item.getOriginalUrl();
		} catch (NullPointerException e) {
			return "";
		}

	}

	public void saveUrl(String shortUrl, String originalUrl) {
		
		String shortUrlSuffix = shortUrl.replaceFirst("^"+hostUrl, "");
		
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
	
	public String makeShortUrl(String originalUrl) {
		return hostUrl + Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).toString();
	}
	
	public String getHostUrl() {
		return hostUrl;
	}
}
