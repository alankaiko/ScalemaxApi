package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.Client;
import br.com.scalemax.model.dto.ClientDTO;
import br.com.scalemax.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = ClientController.PATH)
public class ClientController extends AbstractController<Client, ClientDTO> {
    static final String PATH = "clients";
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        super(clientService);
        this.clientService = clientService;
    }

    @GetMapping(params = "resumo")
    public Page<Client> Resumir(ClientDTO clientDTO, Pageable page) {
        return this.clientService.filtrando(clientDTO, page);
    }
}
