package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.Receiver;
import br.com.scalemax.model.dto.ReceiverDTO;
import br.com.scalemax.repository.ReceiverRepository;
import br.com.scalemax.service.ReceiverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReceiverServiceImpl extends AbstractServiceImpl<Receiver, ReceiverDTO> implements ReceiverService {
    private final ReceiverRepository receiverRepository;

    public ReceiverServiceImpl(ReceiverRepository receiverRepository) {
        super(receiverRepository);
        this.receiverRepository = receiverRepository;
    }

    @Override
    public Page<Receiver> filtrando(ReceiverDTO receiverDTO, Pageable pageable) {
        return this.receiverRepository.filtrando(receiverDTO, pageable);
    }
}
