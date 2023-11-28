package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.Campaign;
import br.com.scalemax.model.dto.CampaignDTO;
import br.com.scalemax.repository.CampaignRepository;
import br.com.scalemax.service.CampaignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CampaignServiceImpl extends AbstractServiceImpl<Campaign, CampaignDTO> implements CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        super(campaignRepository);
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Page<Campaign> filtrando(CampaignDTO campaignDTO, Pageable pageable) {
        return this.campaignRepository.filtrando(campaignDTO, pageable);
    }
}
