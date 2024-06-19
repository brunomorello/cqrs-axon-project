package pt.bmo.store.ProductService.core.errorhandling;

import java.time.LocalDateTime;

public record ErrorMsg(LocalDateTime timestamp, String msg) {
}
