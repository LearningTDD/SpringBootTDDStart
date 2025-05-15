package test.commerce.api.seller.products;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;
import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import test.commerce.api.CommerceApiTest;
import test.commerce.api.TestFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static test.commerce.RegisterProductCommandGenerator.generateRegisterProductCommand;
import static test.commerce.RegisterProductCommandGenerator.generateRegisterProductCommandWithImageUri;
import static test.commerce.RegisterProductCommandGenerator.generateRegisterProductCommandWithPriceAmount;

@CommerceApiTest
@DisplayName("POST /seller/products")
public class POST_specs {

    @Test
    void 올바르게_요청하면_201_Created_상태코드를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();

        // Act
        ResponseEntity<Void> response = fixture.client().postForEntity(
            "/seller/products",
            generateRegisterProductCommand(),
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    void 판매자가_아닌_사용자의_접근_토큰을_사용하면_403_Forbidden_상태코드를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createShopperThenSetAsDefaultUser();

        // Act
        ResponseEntity<Void> response = fixture.client().postForEntity(
            "/seller/products",
            generateRegisterProductCommand(),
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(403);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "invalid-uri",
        "http://",
        "://missing.scheme.com"
    })
    void imageUri_속성이_URI_형식을_따르지_않으면_400_Bad_Request_상태코드를_반환한다(
        String imageUri,
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();

        // Act
        ResponseEntity<Void> response = fixture.client().postForEntity(
            "/seller/products",
            generateRegisterProductCommandWithImageUri(imageUri),
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    void 올바르게_요청하면_등록된_상품_정보에_접근하는_Location_헤더를_반환한다(
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();

        // Act
        ResponseEntity<Void> response = fixture.client().postForEntity(
            "/seller/products",
            generateRegisterProductCommand(),
            Void.class
        );

        // Assert
        URI actual = response.getHeaders().getLocation();
        assertThat(actual).isNotNull();
        assertThat(actual.isAbsolute()).isFalse();
        assertThat(actual.getPath())
            .startsWith("/seller/products/")
            .matches(endsWithUUID());
    }

    private Predicate<? super String> endsWithUUID() {
        return path -> {
            String[] segments = path.split("/");
            String lastSegment = segments[segments.length - 1];
            try {
                UUID.fromString(lastSegment);
                return true;
            } catch (IllegalArgumentException exception) {
                return false;
            }
        };
    }

    @ParameterizedTest
    @ValueSource(doubles = { -1, -0.1 })
    void priceAmount_속성이_0보다_작으면_400_Bad_Request_상태코드를_반환한다(
        double priceAmountValue,
        @Autowired TestFixture fixture
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser();
        BigDecimal priceAmount = new BigDecimal(priceAmountValue);

        // Act
        ResponseEntity<Void> response = fixture.client().postForEntity(
            "/seller/products",
            generateRegisterProductCommandWithPriceAmount(priceAmount),
            Void.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }
}
