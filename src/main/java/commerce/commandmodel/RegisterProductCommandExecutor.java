package commerce.commandmodel;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;

import commerce.Product;
import commerce.command.RegisterProductCommand;

import static java.time.ZoneOffset.UTC;

public class RegisterProductCommandExecutor {

    private final Consumer<Product> saveProduct;

    public RegisterProductCommandExecutor(Consumer<Product> saveProduct) {
        this.saveProduct = saveProduct;
    }

    public void execute(
        UUID productId,
        UUID sellerId,
        RegisterProductCommand command
    ) {
        validateCommand(command);
        Product product = createProduct(productId, sellerId, command);
        saveProduct(product);
    }

    private static void validateCommand(RegisterProductCommand command) {
        if (command.priceAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidCommandException();
        }
    }

    private static boolean isValidUri(String value) {
        try {
            URI uri = URI.create(value);
            return uri.getHost() != null;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private static Product createProduct(
        UUID productId,
        UUID sellerId,
        RegisterProductCommand command
    ) {
        var product = new Product();
        product.setId(productId);
        product.setSellerId(sellerId);
        product.setName(command.name());
        product.setImageUri(command.imageUri());
        product.setDescription(command.description());
        product.setPriceAmount(command.priceAmount());
        product.setStockQuantity(command.stockQuantity());
        product.setRegisteredTimeUtc(LocalDateTime.now(UTC));
        return product;
    }

    private void saveProduct(Product product) {
        saveProduct.accept(product);
    }
}
