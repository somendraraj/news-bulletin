package com.he.newsb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.he.newsb.manager.NewsManager;

@EnableJpaAuditing
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class NewsBulletinApplication {

	private static final Logger log = LoggerFactory.getLogger(NewsManager.class);
	
	public static void main(String[] args) {
		SpringApplication.run(NewsBulletinApplication.class, args);
		log.info("*****Process start after 5 seconds******");
		try{
			Thread.sleep(5000);
			log.info("***********Process started***********");
			NewsManager newsManager = new NewsManager();
			//uncomment below code to insert data into database
			//newsManager.saveDataIntoDB();
		}catch(Exception e){
			
		}
		log.info("********Service started successfully*******");
		
	}
}
