package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.Client;
import br.com.scalemax.model.dto.ClientDTO;
import br.com.scalemax.repository.ClientRepository;
import br.com.scalemax.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl extends AbstractServiceImpl<Client, ClientDTO> implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        super(clientRepository);
        this.clientRepository = clientRepository;
    }

    @Override
    public Page<Client> filtrando(ClientDTO clientDTO, Pageable pageable) {
        return this.clientRepository.filtrando(clientDTO, pageable);
    }
}
