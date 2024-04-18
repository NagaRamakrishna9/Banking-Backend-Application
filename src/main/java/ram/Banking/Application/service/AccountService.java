package ram.Banking.Application.service;
import ram.Banking.Application.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto account);
    AccountDto getAccountById(Long Id);
    AccountDto deposite(Long id, double amount);
    AccountDto Withdraw(Long id, double amount);
    List<AccountDto> getAllAccounts();
    AccountDto deleteAccount(Long id);
}

