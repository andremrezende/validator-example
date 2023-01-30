package br.com.rezende.validator;

import domain.Item;
import domain.Product;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class ValidatorStrategy {
    private static Set<IValidator> validators = new HashSet<>();
    public ValidatorStrategy() {
        validators.addAll(findAllClassesUsingReflectionsLibrary("br.com.rezende.validator"));
    }

    public boolean applyChanges(Product product, Item item) {
        AtomicBoolean updated = new AtomicBoolean(false);
        validators.forEach(validator -> {
            if(validator.applyChanges(product, item)) {
                updated.set(true);
            }
        });
        return updated.get();
    }

    private Set<IValidator> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Set<Class> classesUsingReflections = reflections.getSubTypesOf(IValidator.class)
                .stream()
                .collect(Collectors.toSet());
        return classesUsingReflections.stream().map(clazz -> {
            IValidator obj = null;
            try {
                obj = (IValidator) Class.forName(clazz.getName()).getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException
                     | ClassNotFoundException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return obj;
        }).collect(Collectors.toSet());
    }
}
