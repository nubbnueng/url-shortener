package com.nubbnueng.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nubbnueng.urlshortener.model.URL;

@Transactional
@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

	public URL findByshortUrlSuffix(String suffix);

}
