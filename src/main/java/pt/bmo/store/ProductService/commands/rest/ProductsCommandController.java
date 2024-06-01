package pt.bmo.store.ProductService.commands.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.bmo.store.ProductService.commands.CreateProductCommand;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateProductDto createProductDto) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductDto.price())
                .title(createProductDto.title())
                .quantity(createProductDto.quantity())
                .productId(UUID.randomUUID().toString())
                .build();

        String returnVal;
        try {
             returnVal = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception ex) {
            returnVal = ex.getLocalizedMessage();
        }

        return ResponseEntity.ok().body("ok " + env.getProperty("local.server.port") + " result: " + returnVal);
    }

//    @GetMapping
//    public ResponseEntity getAll() {
//        return ResponseEntity.ok().body("ok " + env.getProperty("local.server.port"));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity getById(@PathVariable("id") long id) {
//        return ResponseEntity.ok().body("ok " + env.getProperty("local.server.port"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity update(@PathVariable("id") long id) {
//        return ResponseEntity.ok().body("ok " + env.getProperty("local.server.port"));
//    }
//
//    @DeleteMapping
//    public ResponseEntity delete() {
//        return ResponseEntity.ok().body("ok " + env.getProperty("local.server.port"));
//    }
}
