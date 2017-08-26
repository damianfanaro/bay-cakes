package com.damianfanaro.baycakes.config;

import com.damianfanaro.baycakes.recipe.Recipe;
import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Configuration
@Profile("default")
public class InMemoryMongo {

    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "bay_cakes_embedded_db";

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        initializeMongoDatabase(mongoTemplate);
        return mongoTemplate;
    }

    private void initializeMongoDatabase(MongoTemplate mongoTemplate) {
        Recipe recipe = Recipe.of("cake", Lists.newArrayList("egg", "milk"));
        recipe.setId("59881440c0d275035ac8817f");
        mongoTemplate.insert(recipe);
    }

}
