package com.android.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.android.api.api.AccountAPI;
import com.android.api.entity.Account;

public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>>{

    @Override
    public EntityModel<Account> toModel(Account entity) {
        return EntityModel.of(entity,
            linkTo(methodOn(AccountAPI.class).findById(entity.getAccountId())).withSelfRel(),
            linkTo(methodOn(AccountAPI.class).findAllAccount()).withSelfRel()
        );
    }
} 