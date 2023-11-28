package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.AccountBank;
import br.com.scalemax.model.dto.AccountBankDTO;
import br.com.scalemax.repository.AccountBankRepository;
import br.com.scalemax.service.AccountBankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountBankServiceImpl extends AbstractServiceImpl<AccountBank, AccountBankDTO> implements AccountBankService {
    private final AccountBankRepository accountBankRepository;

    public AccountBankServiceImpl(AccountBankRepository accountBankRepository) {
        super(accountBankRepository);
        this.accountBankRepository = accountBankRepository;
    }

    @Override
    public Page<AccountBank> filtrando(AccountBankDTO accountBankDTO, Pageable pageable) {
        return this.accountBankRepository.filtrando(accountBankDTO, pageable);
    }
}
