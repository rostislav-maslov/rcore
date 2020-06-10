package com.rcore.restapi.web.endpoints.api;

import lombok.Data;

@Data
public class UploadFileDTO {

    private String contentType;
    private String fileName;
    private byte[] data;

}
