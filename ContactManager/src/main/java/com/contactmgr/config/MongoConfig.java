package com.contactmgr.config;
//package com.myjobappmgr.config;
//
//import java.io.IOException;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import com.mongodb.MongoClient;
//
//import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
//
//public class MongoConfig {
//
//    private static final String MONGO_DB_URL = "localhost";
//    private static final String MONGO_DB_NAME = "embeded_db";
//    @Bean
//    public MongoTemplate mongoTemplate() throws IOException {
//        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
//        mongo.setBindIp(MONGO_DB_URL);
//        MongoClient mongoClient = mongo.getObject();
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
//        return mongoTemplate;
//    }
//}
