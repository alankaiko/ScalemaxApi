package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.Afiliate;
import br.com.scalemax.model.dto.AfiliateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AfiliateService extends AbstractService<Afiliate, AfiliateDTO> {
    Page<Afiliate> filtrando(AfiliateDTO afiliateDTO, Pageable pageable);
}
