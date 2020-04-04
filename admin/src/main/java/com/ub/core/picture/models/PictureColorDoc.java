package com.ub.core.picture.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.awt.*;

@Document
public class PictureColorDoc {
    @Id
    private ObjectId id;
    private String title;
    private String hex;

    /**
     * Конвертация hex в RGB
     * @return
     */
    public Integer getRGB() {
        String hexString = hex;
        if (hex.charAt(0) == '#')
            hexString = hexString.substring(1,7);
        int r = Integer.valueOf(hexString.substring(0, 2), 16);
        int g = Integer.valueOf(hexString.substring(2, 4), 16);
        int b = Integer.valueOf(hexString.substring(4, 6), 16);
        Color color = new Color(r, g, b);
        return color.getRGB();
    }

    public ObjectId getId(){
        return id;
    }
    public void setId(ObjectId id){
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }


}
