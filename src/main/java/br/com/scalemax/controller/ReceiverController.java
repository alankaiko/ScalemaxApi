package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.Receiver;
import br.com.scalemax.model.dto.ReceiverDTO;
import br.com.scalemax.service.ReceiverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = ReceiverController.PATH)
public class ReceiverController extends AbstractController<Receiver, ReceiverDTO> {
    static final String PATH = "receivers";
    private final ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {
        super(receiverService);
        this.receiverService = receiverService;
    }

    @GetMapping(params = "resumo")
    public Page<Receiver> Resumir(ReceiverDTO receiverDTO, Pageable page) {
        return this.receiverService.filtrando(receiverDTO, page);
    }
}
