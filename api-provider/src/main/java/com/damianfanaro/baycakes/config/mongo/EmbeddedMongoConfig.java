package com.damianfanaro.baycakes.config.mongo;

import com.damianfanaro.baycakes.recipe.Recipe;
import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Import({MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@Configuration
@Profile("default")
public class EmbeddedMongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(mongoHost);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoDatabase);
        initializeMongoDatabase(mongoTemplate);
        return mongoTemplate;
    }

    private void initializeMongoDatabase(MongoTemplate mongoTemplate) {
        Recipe recipe = Recipe.of("cake", Lists.newArrayList("egg", "milk"));
        recipe.setId("59881440c0d275035ac8817f");
        mongoTemplate.insert(recipe);
    }

}
