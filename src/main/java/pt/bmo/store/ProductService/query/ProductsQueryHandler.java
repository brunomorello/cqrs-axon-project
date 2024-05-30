package pt.bmo.store.ProductService.query;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pt.bmo.store.ProductService.core.data.ProductEntity;
import pt.bmo.store.ProductService.core.data.ProductRepository;
import pt.bmo.store.ProductService.query.rest.ProductRestModel;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductsQueryHandler {

    private final ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery findProductsQuery) {
        return productRepository.findAll()
                .stream()
                .map(entityToRestModel)
                .collect(Collectors.toList());
    }
    private Function<ProductEntity, ProductRestModel> entityToRestModel = productEntity -> {
        ProductRestModel productRestModel = new ProductRestModel();
        BeanUtils.copyProperties(productEntity, productRestModel);
        return productRestModel;
    };
}
