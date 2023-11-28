package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.Client;
import br.com.scalemax.model.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ClientRepository extends AbstractRepository<Client, ClientDTO, Long> {
    Page<Client> filtrando(ClientDTO clientDTO, Pageable pageable);
}
