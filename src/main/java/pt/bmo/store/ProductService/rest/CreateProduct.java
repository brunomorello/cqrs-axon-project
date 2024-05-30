package pt.bmo.store.ProductService.rest;

import java.math.BigDecimal;

public record CreateProduct(String title, BigDecimal price, Integer quantity) {
}
