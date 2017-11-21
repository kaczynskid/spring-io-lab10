package com.example.store.basket.item;

import com.example.store.basket.Basket;
import com.example.store.basket.BasketService;
import com.example.store.item.ItemClient;
import com.example.store.item.ItemRepresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:store;MODE=MySQL;IGNORECASE=TRUE;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false;INIT=RUNSCRIPT FROM 'classpath:db/migration/test/init_tests.sql';",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.config.enabled=false",
        "spring.cloud.config.discovery.enabled=false",
        "ribbon.eureka.enabled=false",
        "marketing.ribbon.listOfServers=127.0.0.1:9989"
})
@AutoConfigureStubRunner(
        workOffline = true,
        ids = "com.example:marketing:+:stubs:9989"
)
@Transactional
public class BasketItemServiceTest {

    @MockBean ItemClient items;

    @Autowired BasketService baskets;
    @Autowired BasketItemService basketItems;

    @Test
    public void shouldUpdateBasketWithRegularPriceItem() throws Exception {
        Basket basket = baskets.create();
        long itemId = 1L;
        int count = 2;
        when(items.findOne(itemId))
                .thenReturn(new ItemRepresentation("A", BigDecimal.valueOf(40.0)));

        BasketUpdateDiff diff = baskets.updateItem(basket.getId(), itemId, count);

        assertThat(diff.getCountDiff()).isEqualTo(2);
        assertThat(diff.getPriceDiff()).isEqualTo(BigDecimal.valueOf(80.0));
        assertThat(basketItems.findOneItem(basket.getId(), itemId).getSpecialId()).isNull();
    }

    @Test
    public void shouldUpdateBasketWithSpecialPriceItem() throws Exception {
        Basket basket = baskets.create();
        long itemId = 1L;
        int count = 5;
        when(items.findOne(itemId))
                .thenReturn(new ItemRepresentation("A", BigDecimal.valueOf(40.0)));

        BasketUpdateDiff diff = baskets.updateItem(basket.getId(), itemId, count);

        assertThat(diff.getCountDiff()).isEqualTo(5);
        assertThat(diff.getPriceDiff()).isEqualTo(BigDecimal.valueOf(150.0));
        assertThat(basketItems.findOneItem(basket.getId(), itemId).getSpecialId()).isEqualTo("1");
    }
}
