package com.springrestapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadDto {
    private int id;
    private String username;
    private String mobileNo;
    private String address;
    private String urls;
    @JsonIgnore
    private MultipartFile pdfFile;

    @Override
    public String toString() {
        return "FileUploadDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", address='" + address + '\'' +
                ", urls='" + urls + '\'' +
                '}';
    }

    public MultipartFile getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(MultipartFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
