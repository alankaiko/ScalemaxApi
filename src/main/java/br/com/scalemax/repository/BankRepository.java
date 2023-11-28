package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.Bank;
import br.com.scalemax.model.dto.BankDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BankRepository extends AbstractRepository<Bank, BankDTO, Long> {
    Page<Bank> filtrando(BankDTO bankDTO, Pageable pageable);
}
