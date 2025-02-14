package commerce.api.controller;

import commerce.command.CreateSellerCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record SellerSignUpController() {

    @PostMapping("/seller/signUp")
    ResponseEntity<?> signUp(@RequestBody CreateSellerCommand command) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String usernameRegex = "^[a-zA-Z0-9_-]*$";
        if (command.email() == null) {
            return ResponseEntity.badRequest().build();
        } else if (command.email().contains("@") == false) {
            return ResponseEntity.badRequest().build();
        } else if (command.email().endsWith("@")) {
            return ResponseEntity.badRequest().build();
        } else if (command.email().matches(emailRegex) == false) {
            return ResponseEntity.badRequest().build();
        } else if (command.username() == null) {
            return ResponseEntity.badRequest().build();
        } else if (command.username().isBlank()) {
            return ResponseEntity.badRequest().build();
        } else if (command.username().length() < 3) {
            return ResponseEntity.badRequest().build();
        } else if (command.username().matches(usernameRegex) == false) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
