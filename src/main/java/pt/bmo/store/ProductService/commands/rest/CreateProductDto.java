package pt.bmo.store.ProductService.commands.rest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductDto(
        @NotBlank(message = "Product title is mandatory")
        String title,

        @Positive(message = "Price cannot be negative or less than zero")
        BigDecimal price,

        @Min(value = 1, message = "Quantity must be at least 1")
        @Max(value = 5, message = "Max quantity is 5")
        Integer quantity) { }
