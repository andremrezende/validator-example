package br.com.rezende.validator;

import domain.Product;

public interface IValidator <T> {
    boolean applyChanges(Product product, T item);

}
