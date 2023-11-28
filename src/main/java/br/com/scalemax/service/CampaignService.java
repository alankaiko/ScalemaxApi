package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.Campaign;
import br.com.scalemax.model.dto.CampaignDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampaignService extends AbstractService<Campaign, CampaignDTO> {
    Page<Campaign> filtrando(CampaignDTO campaignDTO, Pageable pageable);
}
