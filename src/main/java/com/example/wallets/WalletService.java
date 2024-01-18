package com.example.wallets;

import com.example.wallets.exception.DuplicationException;
import com.example.wallets.exception.InternalServiceException;
import com.example.wallets.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    List<Wallet> wallets = new ArrayList<>(
            List.of(
                    new Wallet(1,"Amazon", "amz@email.com"),
                    new Wallet(2,"Alibaba", "ali@email.com"),
                    new Wallet(3, "Facebook", "fb@gmail.com")
            )
    );

    public WalletService(){ }

    public List<Wallet> getWallets(){
        try{
//            callNormalService();
        }catch (Exception e){
            throw new InternalServiceException("Internal service exception with normal service");
        }
        return wallets;
    }

    public Wallet createWallet(WalletRequest request){
        wallets.stream().filter(w -> w.getEmail().equals(request.email()))
                .findFirst()
                .ifPresent(w -> {
                    throw new DuplicationException("Wallet with " + request.email() + " already existed");
                });
        Optional<Integer> maxId = wallets.stream()
                .map(Wallet::getId)
                .max(Integer::compareTo);
        int nextId = maxId.orElse(0) + 1;
        Wallet wallet = new Wallet(nextId, request.walletName(), request.email());
        wallets.add(wallet);
        return wallet;
    }

    public Wallet getWalletById(Integer id){
        return wallets.stream().filter(w -> w.getId() == id)
                .findFirst()
                .orElseThrow(()->new NotFoundException("Wallet not found by id: " + id));
    }

    private void callNormalService(){
        throw new RuntimeException();
    }

    public void editWallet(int id,WalletRequest request){
        Optional<Wallet> wallet = wallets.stream().filter(w -> w.getId() == id).findFirst();
        if (wallet.isPresent()){
            Wallet w = wallet.get();
            w.setName(request.walletName());
        }else{
            throw new NotFoundException("Wallet not found by id: " + id);
        }
    }

    public void deleteWallet(int id){
        Optional<Wallet> wallet = wallets.stream().filter(w -> w.getId() == id).findFirst();
        if (wallet.isPresent()) {
            wallets.removeIf(w -> w.getId() == id);
        }else{
            throw new NotFoundException("Wallet not found by id: " + id);
        }
    }
}
