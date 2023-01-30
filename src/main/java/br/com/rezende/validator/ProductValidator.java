package br.com.rezende.validator;

import domain.Item;
import domain.Product;

public class ProductValidator implements IValidator<Item> {

    @Override
    public boolean applyChanges(Product product, Item item) {
        if(product != null && item != null) {
            if(product.getPrice() != item.getPrice()) {
                return true;
            }
        }
        return false;
    }
}
