package ram.Banking.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ram.Banking.Application.entity.Account;
public interface Account_repository extends JpaRepository<Account, Long> {
}
