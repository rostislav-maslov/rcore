package com.rcore.restapi.infrastructure.picture;

import com.rcore.adapter.domain.picture.PictureAdapter;
import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.domain.picture.exception.PictureAccessException;
import com.rcore.domain.picture.exception.PictureNotFoundException;
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
public class PictureSender {

    private final PictureAdapter pictureAdapter;
    private final ServletContext servletContext;

    public void send(String pictureId, Integer width, HttpServletResponse response) throws IOException, PictureAccessException, PictureNotFoundException {
        InputStream inputStream = pictureAdapter.getAll().getInputStream(pictureId).orElseThrow(PictureNotFoundException::new);
        InputStreamResource inputStreamResource = new InputStreamResource(pictureAdapter.getAll().getInputStream(pictureId).orElseThrow(PictureNotFoundException::new));
        PictureDTO picture = pictureAdapter.getAll().findById(pictureId).orElseThrow(PictureNotFoundException::new);

        if (width != null && width > 0 && picture.getSize(width).isPresent()) {
            inputStream = pictureAdapter.getAll().getInputStreamByWidth(pictureId, width).get();
            inputStreamResource = new InputStreamResource(pictureAdapter.getAll().getInputStreamByWidth(pictureId, width).get());
        }

        if (inputStream == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(MediaTypeUtils.getMediaTypeForFileName(servletContext, picture.getFileName()).toString());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + picture.getFileName());
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
