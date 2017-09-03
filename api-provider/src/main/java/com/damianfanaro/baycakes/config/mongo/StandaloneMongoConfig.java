package com.damianfanaro.baycakes.config.mongo;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.io.IOException;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Configuration
@Profile({"docker", "develop"})
public class StandaloneMongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean(name = "mongoClient")
    public MongoClient mongoClient() throws IOException {
        return new MongoClient(mongoHost, Integer.parseInt(mongoPort));
    }

    @Autowired
    @Bean(name = "mongoDbFactory")
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoDbFactory(mongoClient, mongoDatabase);
    }

    @Autowired
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, mongoDatabase);
    }

}