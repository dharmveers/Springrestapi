package com.springrestapi.utils;

import com.springrestapi.dtos.FileUploadDto;

public class Validation {
    public static String addRegisterCheck(){
        return "";
    }
    public static String fileUploadCheck(FileUploadDto dto){
        if(dto.getUsername()==null || dto.getUsername().isEmpty()){
            return "username is required";
        } else if (dto.getMobileNo()==null || dto.getMobileNo().isEmpty()) {
            return "mobile number must not be null";
        } else if (dto.getAddress()==null || dto.getAddress().isEmpty()) {
            return "address must not be null";
        }
        return "success";
    }
}
