package com.example.wallets;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("")
    public List<Wallet> getWallets(){
        return walletService.getWallets();
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable Integer id){
        return walletService.getWalletById(id);
    }

    @PostMapping("")
    public Wallet createWallet(@Validated @RequestBody WalletRequest request) throws Exception{
        return walletService.createWallet(request);
    }

    @PutMapping("/{id}")
    public void editWallet(@PathVariable int id, @Validated @RequestBody WalletRequest request) throws Exception{
        walletService.editWallet(id,request);
    }

    @DeleteMapping("/{id}")
    public String deleteWallet(@PathVariable int id) throws Exception{
        walletService.deleteWallet(id);
        return "Wallet id: " + id + " deleted!";
    }
}

