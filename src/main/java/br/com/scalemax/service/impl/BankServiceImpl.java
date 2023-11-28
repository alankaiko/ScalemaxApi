package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.Bank;
import br.com.scalemax.model.dto.BankDTO;
import br.com.scalemax.repository.BankRepository;
import br.com.scalemax.service.BankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankServiceImpl extends AbstractServiceImpl<Bank, BankDTO> implements BankService {
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        super(bankRepository);
        this.bankRepository = bankRepository;
    }

    @Override
    public Page<Bank> filtrando(BankDTO bankDTO, Pageable pageable) {
        return this.bankRepository.filtrando(bankDTO, pageable);
    }
}
