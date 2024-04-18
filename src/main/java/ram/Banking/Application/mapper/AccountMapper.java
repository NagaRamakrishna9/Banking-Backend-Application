package ram.Banking.Application.mapper;

import ram.Banking.Application.dto.AccountDto;
import ram.Banking.Application.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account=new Account(
                accountDto.getId(), accountDto.getAccountHolderName(), accountDto.getBalance()
        );
        return account;
    }
    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto=new AccountDto(
                account.getId(),account.getAccountHolderName(),account.getBalance()
        );
        return accountDto;
    }

}
