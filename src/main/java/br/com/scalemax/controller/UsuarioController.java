package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.Usuario;
import br.com.scalemax.model.dto.UsuarioDTO;
import br.com.scalemax.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = UsuarioController.PATH)
public class UsuarioController extends AbstractController<Usuario, UsuarioDTO> {
    static final String PATH = "usuarios";

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
        this.usuarioService = usuarioService;
    }

    @GetMapping(params = "resumo")
    public Page<Usuario> Resumir(UsuarioDTO usuarioDTO, Pageable page) {
        return this.usuarioService.filtrando(usuarioDTO, page);
    }
}
