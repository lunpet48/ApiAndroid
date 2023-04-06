package com.android.api.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.assembler.AccountModelAssembler;
import com.android.api.entity.Account;
import com.android.api.exception.AccountNotFoundException;
import com.android.api.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountAPI {

    @Autowired
    AccountService accountService;

    AccountModelAssembler accountModelAssembler = new AccountModelAssembler();

    @GetMapping
    public ResponseEntity<?> findAllAccount() {
       List<EntityModel<Account>> result = accountService.findAll().stream()
        .map(accountModelAssembler::toModel).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(result, linkTo(methodOn(AccountAPI.class).findAllAccount()).withSelfRel()));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> findById(@PathVariable("accountId") Long id) {
        Account account = accountService.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        return ResponseEntity.ok(accountModelAssembler.toModel(account));
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody Account accountNew){
        accountNew.setHashCode(new BCryptPasswordEncoder().encode(accountNew.getHashCode()));
        EntityModel<Account> account = accountModelAssembler.toModel(accountService.save(accountNew));

        return ResponseEntity.created(account.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(account);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<?> updateAccount(@RequestBody Account accountUpdate, @PathVariable("accountId") Long id) {
        Account updatedAccount = accountService.findById(id)
            .map(account -> {
                account.setSalt(accountUpdate.getSalt());
                account.setHashCode(accountUpdate.getHashCode());
                account.setUpdateAt(new Date());
                account.setCustomers(accountUpdate.getCustomers());
                return accountService.save(account);
            }).orElseThrow(() -> new AccountNotFoundException(id));
        
        EntityModel<Account> entityModel = accountModelAssembler.toModel(updatedAccount);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
