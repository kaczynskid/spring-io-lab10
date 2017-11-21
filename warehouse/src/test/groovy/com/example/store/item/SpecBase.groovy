package com.example.store.item

import com.example.store.WarehouseApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootTest(classes = [WarehouseApplication, ItemTestConfig],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class SpecBase extends Specification {

    @PersistenceContext
    EntityManager jpa

    def flushAndClear() {
        jpa.flush()
        jpa.clear()
    }

    def <T> T clearAndGet(Class<T> type, Object id) {
        flushAndClear()
        return jpa.getReference(type, id)
    }
}
