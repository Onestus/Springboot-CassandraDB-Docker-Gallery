package com.example.cassandra.springbootcassandragallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootCassandraGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCassandraGalleryApplication.class, args);
	}

}
