package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.Afiliate;
import br.com.scalemax.model.dto.AfiliateDTO;
import br.com.scalemax.repository.AfiliateRepository;
import br.com.scalemax.service.AfiliateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AfiliateServiceImpl extends AbstractServiceImpl<Afiliate, AfiliateDTO> implements AfiliateService {
    private final AfiliateRepository afiliateRepository;

    public AfiliateServiceImpl(AfiliateRepository afiliateRepository) {
        super(afiliateRepository);
        this.afiliateRepository = afiliateRepository;
    }

    @Override
    public Page<Afiliate> filtrando(AfiliateDTO afiliateDTO, Pageable pageable) {
        return this.afiliateRepository.filtrando(afiliateDTO, pageable);
    }
}
