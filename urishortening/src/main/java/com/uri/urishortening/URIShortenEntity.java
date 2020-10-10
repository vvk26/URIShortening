package com.uri.urishortening;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="URI_SHORTEN")
@TableGenerator(name="seq", initialValue=1000)
public class URIShortenEntity implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "uri_id")
	private long uri_id;
	
	@Column(name="longuri",length = 2000)
	private byte[] longuri;

	public long getUri_id() {
		return uri_id;
	}

	public void setUri_id(long uri_id) {
		this.uri_id = uri_id;
	}

	public byte[] getLonguri() {
		return longuri;
	}

	public void setLonguri(byte[] longuri) {
		this.longuri = longuri;
	}

	
}