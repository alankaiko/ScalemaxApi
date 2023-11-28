package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.OrderCampgain;
import br.com.scalemax.model.dto.OrderCampgainDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrderCampgainRepository extends AbstractRepository<OrderCampgain, OrderCampgainDTO, Long> {
    Page<OrderCampgain> filtrando(OrderCampgainDTO orderCampgainDTO, Pageable pageable);
}
