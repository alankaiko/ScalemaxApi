package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.OrderCampgain;
import br.com.scalemax.model.dto.OrderCampgainDTO;
import br.com.scalemax.service.OrderCampgainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = OrderCampgainController.PATH)
public class OrderCampgainController extends AbstractController<OrderCampgain, OrderCampgainDTO> {
    static final String PATH = "ordercampgains";
    private final OrderCampgainService orderCampgainService;

    public OrderCampgainController(OrderCampgainService orderCampgainService) {
        super(orderCampgainService);
        this.orderCampgainService = orderCampgainService;
    }

    @GetMapping(params = "resumo")
    public Page<OrderCampgain> Resumir(OrderCampgainDTO orderCampgainDTO, Pageable page) {
        return this.orderCampgainService.filtrando(orderCampgainDTO, page);
    }
}
