package com.ub.core.file.store;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;

@Document
public class FileInfo extends GridFSDBFile{
    private String fileTmpUploadPath;
    private String fileName;
    private String fileMeta;
    private InputStream inputStream;

    public DBObject getMetaData(){
        DBObject metaData = new BasicDBObject();
        metaData.put("fileTmpUploadPath", fileTmpUploadPath);
        return metaData;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileMeta() {
        return fileMeta;
    }

    public void setFileMeta(String fileMeta) {
        this.fileMeta = fileMeta;
    }

    public String getFileTmpUploadPath() {
        return fileTmpUploadPath;
    }

    public void setFileTmpUploadPath(String fileTmpUploadPath) {
        this.fileTmpUploadPath = fileTmpUploadPath;
    }
}
