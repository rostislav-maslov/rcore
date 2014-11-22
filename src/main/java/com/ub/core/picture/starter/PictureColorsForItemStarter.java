package com.ub.core.picture.starter;

import com.ub.core.base.starter.ACoreStarter;
import com.ub.core.picture.models.PictureColorDoc;
import com.ub.core.picture.services.IPictureColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PictureColorsForItemStarter extends ACoreStarter  {
    @Autowired private IPictureColorService iPictureColorService;


    @Override
    protected void onStart() {
        if(iPictureColorService.count() > 0)
            return;
        create("Голубой","#00c1ff");
        create("Розовый","#ff00c6");
        create("Коричневый","#9e3204");
        create("Черный","#000000");
        create("Серый","#777777");
        create("Зеленый","#22c407");
        create("Синий","#0066ff");
        create("Красный","#ff0000");
        create("Желтый","#f9ff00");
        create("Серебро","#c1c1c1");
        create("Золото","#dbc60f");
        create("Белый","#ffffff");
    }

    protected void create(String title, String hex){
        PictureColorDoc pictureColorDoc = new PictureColorDoc();
        pictureColorDoc.setHex(hex);
        pictureColorDoc.setTitle(title);
        iPictureColorService.save(pictureColorDoc);
    }
}
