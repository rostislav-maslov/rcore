package com.rcore.adapter.domain.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileDTO {
    protected String id;
    protected String fileName;
    protected String filePath;
    protected Boolean isPrivate;
    protected Date createdAt;
    protected Date updatedAt;
    protected String timeZone;
}
