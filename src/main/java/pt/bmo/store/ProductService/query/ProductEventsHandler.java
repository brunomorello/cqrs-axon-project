package pt.bmo.store.ProductService.query;

import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pt.bmo.store.ProductService.core.data.ProductEntity;
import pt.bmo.store.ProductService.core.data.ProductRepository;
import pt.bmo.store.ProductService.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        productRepository.save(productEntity);

//        if (true) throw new Exception("Error to persist product - testing random exception");
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception ex) {

    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException ex) {

    }
}
