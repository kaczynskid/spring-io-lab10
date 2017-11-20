package com.example.store.item

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@Slf4j
@TestConfiguration
class ItemTestConfig {

    @Autowired
    ItemRepository items

    @Bean
    ApplicationRunner itemTestData() {
        return {
            items.save(new Item(null, 'A', 100, 40.0))
            items.save(new Item(null, 'B', 100, 10.0))
            items.save(new Item(null, 'C', 100, 30.0))
            items.save(new Item(null, 'D', 100, 25.0))
        } as ApplicationRunner
    }
}
