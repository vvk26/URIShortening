package com.uri.urishortening;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UriShorteningRepo extends JpaRepository<URIShortenEntity, Integer>  {

	
	@Query("SELECT uriEnt FROM URIShortenEntity uriEnt WHERE uriEnt.longuri = :uri")
	public List<URIShortenEntity> checkUri(@Param("uri") byte[] uri);
	
	@Query("SELECT uriEnt FROM URIShortenEntity uriEnt WHERE uriEnt.uri_id = :uriId")
	public List<URIShortenEntity> getLongURI(@Param("uriId") long uriId);
	
}
