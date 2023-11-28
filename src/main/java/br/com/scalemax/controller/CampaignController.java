package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.Campaign;
import br.com.scalemax.model.dto.CampaignDTO;
import br.com.scalemax.service.CampaignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = CampaignController.PATH)
public class CampaignController extends AbstractController<Campaign, CampaignDTO> {
    static final String PATH = "campaigns";
    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        super(campaignService);
        this.campaignService = campaignService;
    }

    @GetMapping(params = "resumo")
    public Page<Campaign> Resumir(CampaignDTO campaignDTO, Pageable page) {
        return this.campaignService.filtrando(campaignDTO, page);
    }
}
