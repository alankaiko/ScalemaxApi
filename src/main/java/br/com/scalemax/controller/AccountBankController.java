package br.com.scalemax.controller;

import br.com.scalemax.acore.controller.AbstractController;
import br.com.scalemax.model.AccountBank;
import br.com.scalemax.model.dto.AccountBankDTO;
import br.com.scalemax.service.AccountBankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = AccountBankController.PATH)
public class AccountBankController extends AbstractController<AccountBank, AccountBankDTO> {
    static final String PATH = "accountbanks";
    private final AccountBankService accountBankService;

    public AccountBankController(AccountBankService accountBankService) {
        super(accountBankService);
        this.accountBankService = accountBankService;
    }

    @GetMapping(params = "resumo")
    public Page<AccountBank> Resumir(AccountBankDTO accountBankDTO, Pageable page) {
        return this.accountBankService.filtrando(accountBankDTO, page);
    }


}
