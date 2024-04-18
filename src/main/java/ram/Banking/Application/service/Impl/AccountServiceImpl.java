package ram.Banking.Application.service.Impl;
import org.springframework.stereotype.Service;
import ram.Banking.Application.dto.AccountDto;
import ram.Banking.Application.entity.Account;
import ram.Banking.Application.mapper.AccountMapper;
import ram.Banking.Application.repository.Account_repository;
import ram.Banking.Application.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{
    private Account_repository accountRepository;

    public AccountServiceImpl(Account_repository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account=AccountMapper.mapToAccount(accountDto);
       Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account= accountRepository
               .findById(id).orElseThrow(() ->new RuntimeException("Account Does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposite(Long id, double amount) {
        Account account= accountRepository
                .findById(id).orElseThrow(() ->new RuntimeException("Account Does not exist"));
       double total= account.getBalance()+amount;
       account.setBalance(total);
       Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto Withdraw(Long id, double amount) {
        Account account= accountRepository
                .findById(id).orElseThrow(() ->new RuntimeException("Account Does not exist"));
        if(account.getBalance()< amount){
            throw new RuntimeException("Insufficient Amount");
        }
        double total = account.getBalance()-amount;
        Account savedAccount = accountRepository.save(account);

        return  AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Account account= accountRepository
                .findById(id).orElseThrow(() ->new RuntimeException("Account Does not exist"));
        accountRepository.deleteById(id);
        return AccountMapper.mapToAccountDto(account);
    }
}
