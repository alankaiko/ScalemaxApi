package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.Receiver;
import br.com.scalemax.model.dto.ReceiverDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReceiverRepository extends AbstractRepository<Receiver, ReceiverDTO, Long> {
    Page<Receiver> filtrando(ReceiverDTO receiverDTO, Pageable pageable);
}
