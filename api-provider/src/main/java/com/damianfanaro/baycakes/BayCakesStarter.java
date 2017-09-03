package com.damianfanaro.baycakes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan
public class BayCakesStarter {

    public static void main(String[] args) {
        SpringApplication.run(BayCakesStarter.class, args);
    }

}
