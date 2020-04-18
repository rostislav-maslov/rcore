package com.rcore.restapi.infrastructure.file;


import com.rcore.adapter.domain.file.FileAdapter;
import com.rcore.adapter.domain.file.FileAllAdapter;
import com.rcore.adapter.domain.file.dto.FileDTO;
import com.rcore.domain.file.exception.FileAccessException;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.restapi.utils.MediaTypeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class FileSender {

    private final FileAdapter fileAdapter;
    private final ServletContext servletContext;

    public void send(String fileId, HttpServletResponse response) throws IOException, FileNotFoundException, FileAccessException {
        InputStream inputStream = fileAdapter.getAll().getInputStream(fileId).orElseThrow(FileNotFoundException::new);
        InputStreamResource inputStreamResource = new InputStreamResource(fileAdapter.getAll().getInputStream(fileId).orElseThrow(FileNotFoundException::new));
        FileDTO file = fileAdapter.getAll().findById(fileId).orElseThrow(FileNotFoundException::new);

        if (inputStream == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(MediaTypeUtils.getMediaTypeForFileName(servletContext, file.getFileName()).toString());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName());
        response.setContentLength((int) inputStreamResource.contentLength());

        BufferedInputStream inStream = new BufferedInputStream(inputStream);
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }

}
