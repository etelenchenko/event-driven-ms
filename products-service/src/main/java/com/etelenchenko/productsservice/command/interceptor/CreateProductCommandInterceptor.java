package com.etelenchenko.productsservice.command.interceptor;

import com.etelenchenko.productsservice.command.CreateProductCommand;
import com.etelenchenko.productsservice.model.ProductLookupEntity;
import com.etelenchenko.productsservice.repository.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<CreateProductCommand>> {

    private final ProductLookupRepository productLookupRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<CreateProductCommand>, CommandMessage<CreateProductCommand>> handle(
            @Nonnull List<? extends CommandMessage<CreateProductCommand>> list) {
        return (index, command) -> {

            CreateProductCommand createProductCommand = command.getPayload();
            String productId = createProductCommand.getProductId();
            String title = createProductCommand.getTitle();
            Optional<ProductLookupEntity> productLookupEntity = productLookupRepository.findByProductIdOrTitle(productId, title);
            if (productLookupEntity.isPresent()) {
                throw new IllegalStateException(String.format("Product with id %s or title %s already exists", productId, title));
            }

            return command;
        };
    }
}
