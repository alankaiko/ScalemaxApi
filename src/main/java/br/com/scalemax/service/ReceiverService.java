package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.Receiver;
import br.com.scalemax.model.dto.ReceiverDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceiverService extends AbstractService<Receiver, ReceiverDTO> {
    Page<Receiver> filtrando(ReceiverDTO receiverDTO, Pageable pageable);
}
