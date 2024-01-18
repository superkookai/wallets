package com.example.wallets;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record WalletRequest(
        @NotNull
        @Size(min = 3, max = 20, message = "Wallet name should be between 3 to 20 characters")
        String walletName,
//        @NotNull
        @Email(message = "Email should be valid")
        String email) {
}
