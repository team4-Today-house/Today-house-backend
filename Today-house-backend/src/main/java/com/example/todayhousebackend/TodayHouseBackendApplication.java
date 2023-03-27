package com.example.todayhousebackend;

import com.example.todayhousebackend.crawling.seleniumCrawling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class TodayHouseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodayHouseBackendApplication.class, args);


	}

}
