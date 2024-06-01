package pt.bmo.store.ProductService;

import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import pt.bmo.store.ProductService.commands.CreateProductCommandInterceptor;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void registerInterceptor(ApplicationContext applicationContext, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(applicationContext.getBean(CreateProductCommandInterceptor.class));
	}

}
