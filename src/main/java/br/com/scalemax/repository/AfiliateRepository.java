package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.Afiliate;
import br.com.scalemax.model.dto.AfiliateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AfiliateRepository extends AbstractRepository<Afiliate, AfiliateDTO, Long> {
    Page<Afiliate> filtrando(AfiliateDTO afiliateDTO, Pageable pageable);
}
