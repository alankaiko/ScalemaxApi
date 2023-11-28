package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.Afiliate;
import br.com.scalemax.model.dto.AfiliateDTO;
import br.com.scalemax.service.AfiliateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = AfiliateController.PATH)
public class AfiliateController extends AbstractController<Afiliate, AfiliateDTO> {
    static final String PATH = "afiliates";
    private final AfiliateService afiliateService;

    public AfiliateController(AfiliateService afiliateService) {
        super(afiliateService);
        this.afiliateService = afiliateService;
    }

    @GetMapping(params = "resumo")
    public Page<Afiliate> Resumir(AfiliateDTO afiliateDTO, Pageable page) {
        return this.afiliateService.filtrando(afiliateDTO, page);
    }


}
