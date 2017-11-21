package com.example.marketing;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketingApplication.class,properties = {
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.config.enabled=false",
        "spring.cloud.config.discovery.enabled=false",
        "ribbon.eureka.enabled=false"
})
public abstract class MarketingApplicationTestsBase {

    @Autowired
    WebApplicationContext applicationContext;

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(applicationContext);
    }
}
