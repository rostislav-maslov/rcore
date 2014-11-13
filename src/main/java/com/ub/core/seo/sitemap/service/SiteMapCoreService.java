package com.ub.core.seo.sitemap.service;

import com.ub.core.seo.sitemap.xml.UrlsetRootElement;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
public class SiteMapCoreService {

    public String getSitemap(UrlsetRootElement urlsetRootElement){
        String s = "";
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(UrlsetRootElement.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter stringWriter = new StringWriter();

            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.sitemaps.org/schemas/sitemap/0.9\n" +
                    "            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");

            jaxbMarshaller.marshal(urlsetRootElement, stringWriter);

            s = stringWriter.getBuffer().toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return s;
    }

}
