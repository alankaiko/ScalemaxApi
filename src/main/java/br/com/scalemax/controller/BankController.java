package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.Bank;
import br.com.scalemax.model.dto.BankDTO;
import br.com.scalemax.service.BankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = BankController.PATH)
public class BankController extends AbstractController<Bank, BankDTO> {
    static final String PATH = "banks";
    private final BankService bankService;

    public BankController(BankService bankService) {
        super(bankService);
        this.bankService = bankService;
    }

    @GetMapping(params = "resumo")
    public Page<Bank> Resumir(BankDTO bankDTO, Pageable page) {
        return this.bankService.filtrando(bankDTO, page);
    }
}
