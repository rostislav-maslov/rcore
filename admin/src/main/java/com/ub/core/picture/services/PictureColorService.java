package com.ub.core.picture.services;

import com.ub.core.picture.models.PictureColorDoc;
import com.ub.core.picture.view.colors.SearchAdminRequest;
import com.ub.core.picture.view.colors.SearchAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@Component
public class PictureColorService {

    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private IPictureColorService iPictureColorService;

    public SearchAdminResponse findAll(SearchAdminRequest searchItemColorAdminRequest) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(
                searchItemColorAdminRequest.getCurrentPage(),
                searchItemColorAdminRequest.getPageSize(),
                sort);

        Criteria criteria = new Criteria();

        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, PictureColorDoc.class);
        query = query.with(pageable);

        List<PictureColorDoc> result = mongoTemplate.find(query, PictureColorDoc.class);
        SearchAdminResponse searchItemColorAdminResponse = new SearchAdminResponse(
                searchItemColorAdminRequest.getCurrentPage(),
                searchItemColorAdminRequest.getPageSize(),
                result);
        searchItemColorAdminResponse.setAll(count);
        searchItemColorAdminResponse.setQuery(searchItemColorAdminRequest.getQuery());
        return searchItemColorAdminResponse;
    }

    /**
     * Определяем доминирующий цвет в картинке
     * @param inputStream
     * @return
     * @throws Exception
     */
    public String getDominantColor(InputStream inputStream) throws Exception{
        ImageInputStream is = ImageIO.createImageInputStream(inputStream);
        Iterator iter = ImageIO.getImageReaders(is);

        if (!iter.hasNext()) {
            return "";
        }
        ImageReader imageReader = (ImageReader)iter.next();
        imageReader.setInput(is);

        BufferedImage image = imageReader.read(0);

        int height = image.getHeight();
        int width = image.getWidth();

        Map m = new HashMap();
        for(int i=0; i < width ; i++) {
            for(int j=0; j < height ; j++) {
                int rgb = image.getRGB(i, j);
                int[] rgbArr = getRGBArr(rgb);
                // Filter out grays....
//                if (!isGray(rgbArr)) {
                Integer counter = (Integer) m.get(rgb);
                if (counter == null)
                    counter = 0;
                counter++;
                m.put(rgb, counter);
//                }
            }
        }
        Integer currentColor = getMostCommonColour(m);
        PictureColorDoc closeColor = null;
        Integer diff = null;

        Iterator<PictureColorDoc> colorIterator = iPictureColorService.findAll().iterator();
        while (colorIterator.hasNext()) {
            PictureColorDoc color = colorIterator.next();
            if (diff == null) {
                diff = Math.abs(currentColor-color.getRGB());
                closeColor = color;
            } else if (Math.abs(currentColor-color.getRGB()) < diff) {
                diff = Math.abs(currentColor-color.getRGB());
                closeColor = color;
            }
        }

        return closeColor.getHex();
    }

    /**
     * Получаем массив RGB значения пикселя
     * @param pixel
     * @return
     */
    private int[] getRGBArr(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return new int[]{red,green,blue};

    }

    /**
     * Проверка является ли пиксель белым, серым или черным цветом
     * @param rgbArr
     * @return
     */
    private boolean isGray(int[] rgbArr) {
        int rgDiff = rgbArr[0] - rgbArr[1];
        int rbDiff = rgbArr[0] - rgbArr[2];
        // Filter out black, white and grays...... (tolerance within 10 pixels)
        int tolerance = 10;
        if (rgDiff > tolerance || rgDiff < -tolerance)
            if (rbDiff > tolerance || rbDiff < -tolerance) {
                return false;
            }
        return true;
    }

    /**
     * Определяем наиболее часто встречающийся цвет
     * @param map
     * @return
     */
    private Integer getMostCommonColour(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        Map.Entry me = (Map.Entry )list.get(list.size()-1);
        int[] rgb= getRGBArr((Integer)me.getKey());
        Color color = new Color(rgb[0], rgb[1], rgb[2]);
        return color.getRGB();
    }

    public PictureColorDoc findByHex(String hex) {
        PictureColorDoc itemColorDoc = mongoTemplate.findOne(new Query(Criteria.where("hex").is(hex)), PictureColorDoc.class);
        return itemColorDoc;
    }
}
