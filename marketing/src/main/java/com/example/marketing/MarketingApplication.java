package com.example.marketing;

import com.example.marketing.special.Special;
import com.example.marketing.special.SpecialRepository;
import com.example.marketing.special.select.BestSpecialSelector;
import com.example.marketing.special.select.SpecialSelector;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties(MathProperties.class)
public class MarketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketingApplication.class, args);
	}

	@Bean
	public SpecialSelector specialSelector(MathProperties math) {
		return new BestSpecialSelector(math);
	}

	@Bean
	public ApplicationRunner init(SpecialRepository specials) {
		return args -> {
			if (specials.count() < 1) {
				specials.save(new Special(null, 1, 3, BigDecimal.valueOf(70)));
				specials.save(new Special(null, 2, 2, BigDecimal.valueOf(15)));
				specials.save(new Special(null, 3, 4, BigDecimal.valueOf(60)));
				specials.save(new Special(null, 4, 2, BigDecimal.valueOf(40)));
			}
		};
	}
}
