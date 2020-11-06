package com.rcore.database.mongo.config;

import com.mongodb.BasicDBObject;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.io.IOException;
import java.util.UUID;

@Getter
public class DatabaseConfig {

    private static final String DB_NAME = "test";
    private MongoDbFactory mongoDbFactory;
    private MongoTemplate mongoTemplate;

    public DatabaseConfig() throws IOException {
//        MongodForTestsFactory factory = MongodForTestsFactory.with(Version.Main.V3_1);
//        MongoClient client = factory.newMongo();
//        DB db = client.getDB("test-" + UUID.randomUUID());
//        DBCollection col = db.createCollection(DB_NAME, new BasicDBObject());
//        System.out.println("messages collection was created in test database"+col);
//
//        this.mongoDbFactory =  new SimpleMongoDbFactory(client, DB_NAME);
        this.mongoTemplate = new MongoTemplate(this.mongoDbFactory);
    }


}
