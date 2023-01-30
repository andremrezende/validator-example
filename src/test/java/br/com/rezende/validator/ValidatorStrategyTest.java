package br.com.rezende.validator;

import domain.Item;
import domain.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorStrategyTest {
    private static ValidatorStrategy validatorStrategy = new ValidatorStrategy();

    @Test
    public void noUpdated() {
        //GIVEN
        Product product = new Product();
        product.setId(1);
        product.setPrice(1.1);
        Item item = new Item();
        item.setId(1);
        item.setPrice(1.1);

        //WHEN
        boolean updated = validatorStrategy.applyChanges(product, item);

        //THEN
        assertFalse(updated);
    }

    @Test
    public void updated() {
        //GIVEN
        Product product = new Product();
        product.setId(1);
        product.setPrice(1.1);
        Item item = new Item();
        item.setId(1);
        item.setPrice(1.2);

        //WHEN
        boolean updated = validatorStrategy.applyChanges(product, item);

        //THEN
        assertTrue(updated);
    }
}
