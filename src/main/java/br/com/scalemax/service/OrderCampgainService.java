package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.OrderCampgain;
import br.com.scalemax.model.dto.OrderCampgainDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderCampgainService extends AbstractService<OrderCampgain, OrderCampgainDTO> {
    Page<OrderCampgain> filtrando(OrderCampgainDTO orderCampgainDTO, Pageable pageable);
}
