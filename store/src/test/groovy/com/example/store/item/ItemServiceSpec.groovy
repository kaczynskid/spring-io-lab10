package com.example.store.item

import com.example.store.StoreApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = [StoreApplication, ItemTestConfig],
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ItemServiceSpec extends Specification {

    @Autowired ItemRepository repository
    @Autowired ItemService items

    def  "should find all items" () {
        when:
            def items = items.findAll()
        then:
            items.size() >= 4
    }

    def "should not update not existing item"() {
        when:
            items.update(new ItemUpdate(123L, 'test', 23.99))
        then:
            thrown IllegalArgumentException
    }

    def "should update item details"() {
        when:
            items.update(new ItemUpdate(1L, 'test', 23.99))
        then:
            Item item = items.findOne(1L)
            item.name == "test"
            item.price == 23.99
    }

    def "should not check out too many items"() {
        when:
           items.updateStock(new ItemStockUpdate(1L, -120))
        then:
            thrown OutOfStock
    }

    def "should check out some items"() {
        when:
            items.updateStock(new ItemStockUpdate(1, -20))
        then:
            Item item = items.findOne(1L)
            item.count == 80
    }
}
