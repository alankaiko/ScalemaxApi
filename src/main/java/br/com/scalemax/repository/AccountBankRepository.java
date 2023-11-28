package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.AccountBank;
import br.com.scalemax.model.dto.AccountBankDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AccountBankRepository extends AbstractRepository<AccountBank, AccountBankDTO, Long> {
    Page<AccountBank> filtrando(AccountBankDTO accountBankDTO, Pageable pageable);
}
