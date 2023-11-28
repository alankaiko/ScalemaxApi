package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.Bank;
import br.com.scalemax.model.dto.BankDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankService extends AbstractService<Bank, BankDTO> {
    Page<Bank> filtrando(BankDTO bankDTO, Pageable pageable);
}
