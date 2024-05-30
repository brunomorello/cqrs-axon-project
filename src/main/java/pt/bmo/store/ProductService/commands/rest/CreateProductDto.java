package pt.bmo.store.ProductService.commands.rest;

import java.math.BigDecimal;

public record CreateProductDto(String title, BigDecimal price, Integer quantity) {
}
