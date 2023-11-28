package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.AccountBank;
import br.com.scalemax.model.dto.AccountBankDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountBankService extends AbstractService<AccountBank, AccountBankDTO> {
    Page<AccountBank> filtrando(AccountBankDTO accountBankDTO, Pageable pageable);
}
