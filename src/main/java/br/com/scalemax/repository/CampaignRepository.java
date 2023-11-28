package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.Campaign;
import br.com.scalemax.model.dto.CampaignDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CampaignRepository extends AbstractRepository<Campaign, CampaignDTO, Long> {
    Page<Campaign> filtrando(CampaignDTO campaignDTO, Pageable pageable);
}
