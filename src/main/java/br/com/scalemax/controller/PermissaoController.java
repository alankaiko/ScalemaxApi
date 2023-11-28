package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.Permissao;
import br.com.scalemax.model.dto.PermissaoDTO;
import br.com.scalemax.service.PermissaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = PermissaoController.PATH)
public class PermissaoController extends AbstractController<Permissao, PermissaoDTO> {
    static final String PATH = "permissaos";
    private final PermissaoService permissaoService;

    public PermissaoController(PermissaoService permissaoService) {
        super(permissaoService);
        this.permissaoService = permissaoService;
    }

    @GetMapping(params = "resumo")
    public Page<Permissao> Resumir(PermissaoDTO permissaoDTO, Pageable page) {
        return this.permissaoService.filtrando(permissaoDTO, page);
    }
}
