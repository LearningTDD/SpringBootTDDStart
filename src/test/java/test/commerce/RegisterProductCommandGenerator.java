package test.commerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import commerce.command.RegisterProductCommand;

public class RegisterProductCommandGenerator {

    public static RegisterProductCommand generateRegisterProductCommand() {
        return new RegisterProductCommand(
            generateProductName(),
            generateProductImageUri(),
            generateProductDescription(),
            generateProductPriceAmount(),
            generateProductStockQuantity()
        );
    }

    public static RegisterProductCommand generateRegisterProductCommandWithImageUri(
        String imageUri
    ) {
        return new RegisterProductCommand(
            generateProductName(),
            imageUri,
            generateProductDescription(),
            generateProductPriceAmount(),
            generateProductStockQuantity()
        );
    }

    public static RegisterProductCommand generateRegisterProductCommandWithPriceAmount(
        BigDecimal priceAmount
    ) {
        return new RegisterProductCommand(
            generateProductName(),
            generateProductImageUri(),
            generateProductDescription(),
            priceAmount,
            generateProductStockQuantity()
        );
    }

    private static String generateProductName() {
        return "name" + UUID.randomUUID();
    }

    private static String generateProductImageUri() {
        return "https://test.com/images/" + UUID.randomUUID();
    }

    private static String generateProductDescription() {
        return "description" + UUID.randomUUID();
    }

    private static BigDecimal generateProductPriceAmount() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return BigDecimal.valueOf(random.nextInt(10000, 100000));
    }

    private static int generateProductStockQuantity() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(10, 100);
    }
}
