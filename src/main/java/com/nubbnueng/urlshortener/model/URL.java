package com.nubbnueng.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class URL {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String shortUrlSuffix;
	
	@Lob
	private String originalUrl;
	
	private int count;

	public URL() {
		// TODO Auto-generated constructor stub
	}
	
	public URL( String shortUrlSuffix, String originalUrl) {
		this.shortUrlSuffix = shortUrlSuffix;
		this.originalUrl = originalUrl;
		this.count = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShortUrlSuffix() {
		return shortUrlSuffix;
	}

	public void setShortUrlSuffix(String shortUrlSuffix) {
		this.shortUrlSuffix = shortUrlSuffix;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increaseCount() {
		this.count++;
	}

}
