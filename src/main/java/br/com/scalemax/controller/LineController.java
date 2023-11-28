package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.Line;
import br.com.scalemax.model.dto.LineDTO;
import br.com.scalemax.service.LineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = LineController.PATH)
public class LineController extends AbstractController<Line, LineDTO> {
    static final String PATH = "lines";
    private final LineService lineService;

    public LineController(LineService lineService) {
        super(lineService);
        this.lineService = lineService;
    }

    @GetMapping(params = "resumo")
    public Page<Line> Resumir(LineDTO lineDTO, Pageable page) {
        return this.lineService.filtrando(lineDTO, page);
    }
}
