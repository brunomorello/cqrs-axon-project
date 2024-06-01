package pt.bmo.store.ProductService.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;
import pt.bmo.store.ProductService.core.data.ProductLookupEntity;
import pt.bmo.store.ProductService.core.data.ProductLookupRepository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
            @Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            log.info("Intercepted Command: {} - {}", command.getCommandName(), command.getPayloadType());

            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                Optional<ProductLookupEntity> productLookupEntityOptional = productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());

                if (productLookupEntityOptional.isPresent())
                    throw new IllegalStateException(String.format(
                       "Product with ID %s or Title %s already exist",
                            createProductCommand.getProductId(),
                            createProductCommand.getTitle()
                    ));
            }

            return command;
        };
    }
}
