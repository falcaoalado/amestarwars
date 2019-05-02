package com.amedigital.amestarwars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mongodb.MongoClient;

@Configuration
@PropertySource(value = "classpath:config.properties", ignoreResourceNotFound = true)
public class MongoConfig {

	@Autowired
	private Environment environment;

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		String db_host = environment.getProperty("mongodb.host");
		String db_port = environment.getProperty("mongodb.port");
		String db_name = environment.getProperty("mongodb.db");

		MongoClient mongo = new MongoClient(db_host, Integer.valueOf(db_port));
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, db_name);

		return new MongoTemplate(mongoDbFactory);
	}

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
}
