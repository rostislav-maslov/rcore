package com.ub.core.picture.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.imgscalr.Scalr.Rotation;

public class PictureUtils {

    public static InputStream getRotatedTo360PictureIn(MultipartFile multipartFile) {

        try {
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            return pictureIn(
                    bi,
                    FilenameUtils.getExtension(multipartFile.getOriginalFilename()),
                    new AffineTransformOp(getAffineTransform(), AffineTransformOp.TYPE_BILINEAR)
            );
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static boolean needRotate(MultipartFile multipartFile) {

        return getPictureOrientation(multipartFile) != null;
    }

    public static Rotation getPictureOrientation(MultipartFile multipartFile) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(multipartFile.getInputStream());
            ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (exifIFD0Directory == null) {
                return null;
            }
            int orientation = exifIFD0Directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
            switch (orientation) {
                case 1: // [Exif IFD0] Orientation - Top, left side (Horizontal / normal)
                    return null;
                case 6: // [Exif IFD0] Orientation - Right side, top (Rotate 90 CW)
                    return Rotation.CW_90;
                case 3: // [Exif IFD0] Orientation - Bottom, right side (Rotate 180)
                    return Rotation.CW_180;
                case 8: // [Exif IFD0] Orientation - Left side, bottom (Rotate 270 CW)
                    return Rotation.CW_270;
            }
        } catch (ImageProcessingException | IOException | MetadataException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static InputStream pictureIn(BufferedImage image, String fileType, AffineTransformOp ops) {
        try {
            BufferedImage destImage = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
            destImage = ops.filter(image, destImage);

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            ImageIO.write(destImage, fileType, os);

            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static AffineTransform getAffineTransform() {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(2 * Math.PI);

        return affineTransform;
    }

    public static InputStream fromBufferedImageToInputStream(BufferedImage image, String fileType) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(dropAlphaChannel(image), fileType, os);

            return new ByteArrayInputStream(os.toByteArray());
        }
    }

    private static BufferedImage dropAlphaChannel(BufferedImage src) {
        BufferedImage convertedImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        convertedImg.getGraphics().drawImage(src, 0, 0, null);

        return convertedImg;
    }
}
