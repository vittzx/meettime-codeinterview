package com.meet.time.interview;

import com.meet.time.interview.application.configuration.HubspotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({HubspotProperties.class})
public class CodeInterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeInterviewApplication.class, args);
	}

}
