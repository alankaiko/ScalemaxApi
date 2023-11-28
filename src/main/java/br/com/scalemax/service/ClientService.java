package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.Client;
import br.com.scalemax.model.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService extends AbstractService<Client, ClientDTO> {
    Page<Client> filtrando(ClientDTO clientDTO, Pageable pageable);
}
