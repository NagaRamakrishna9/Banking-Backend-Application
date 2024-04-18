package ram.Banking.Application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ram.Banking.Application.dto.AccountDto;
import ram.Banking.Application.service.AccountService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

        //Add Account RestApi
    @PostMapping
        public ResponseEntity<AccountDto> addAccount( @RequestBody AccountDto accountDto){
            return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
        }

    //Getn Accpount Rest Api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposite Rest Api
@PutMapping("/{id}/deposite")
    public ResponseEntity<AccountDto> deposite(@PathVariable Long id, @RequestBody Map<String, Double> request){

        double amount=request.get("amount");
       AccountDto accountDto= accountService.deposite(id,request.get("amount"));
       return ResponseEntity.ok(accountDto);
    }

    //Withdraw Rest Api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map< String,Double> request){
        double amount=request.get("amount");
        AccountDto accountDto=accountService.Withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    //Set All Accounts rest Api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts=accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Delete Account Rest Api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Accont is deleted Successfully");
    }
}

