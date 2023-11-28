package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.OrderCampgain;
import br.com.scalemax.model.dto.OrderCampgainDTO;
import br.com.scalemax.repository.OrderCampgainRepository;
import br.com.scalemax.service.OrderCampgainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderCampgainServiceImpl extends AbstractServiceImpl<OrderCampgain, OrderCampgainDTO> implements OrderCampgainService {
    private final OrderCampgainRepository orderCampgainRepository;

    public OrderCampgainServiceImpl(OrderCampgainRepository orderCampgainRepository) {
        super(orderCampgainRepository);
        this.orderCampgainRepository = orderCampgainRepository;
    }

    @Override
    public Page<OrderCampgain> filtrando(OrderCampgainDTO orderCampgainDTO, Pageable pageable) {
        return this.orderCampgainRepository.filtrando(orderCampgainDTO, pageable);
    }
}
