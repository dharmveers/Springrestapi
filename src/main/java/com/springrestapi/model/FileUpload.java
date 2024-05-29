package com.springrestapi.model;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FileUpload {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String username;
	private String mobileNo;
	private String address;
	private String urls;

	public int getId() {
		return id;
	}
	public String getUrls() {
		return urls;
	}
	public String getUsername() {
		return username;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}

}
