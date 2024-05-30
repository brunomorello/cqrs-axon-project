package pt.bmo.store.ProductService.query.rest;

import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.bmo.store.ProductService.query.FindProductsQuery;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsQueryController {

    private final QueryGateway queryGateway;

    @GetMapping
    public ResponseEntity<List<ProductRestModel>> getProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();
        List<ProductRestModel> products = queryGateway.query(findProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
        return ResponseEntity.ok(products);
    }
}
