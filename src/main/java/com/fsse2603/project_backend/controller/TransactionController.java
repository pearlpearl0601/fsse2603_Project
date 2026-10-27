package com.fsse2603.project_backend.controller;

import com.fsse2603.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2603.project_backend.data.transaction.dto.response.TransactionResponseDto;
import com.fsse2603.project_backend.mapper.transaction.TransactionDtoMapper;
import com.fsse2603.project_backend.mapper.user.UserDataMapper;
import com.fsse2603.project_backend.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final UserDataMapper userDataMapper;
    private final TransactionDtoMapper transactionDtoMapper;

    public TransactionController(TransactionService transactionService, UserDataMapper userDataMapper, TransactionDtoMapper transactionDtoMapper) {
        this.transactionService = transactionService;
        this.userDataMapper = userDataMapper;
        this.transactionDtoMapper = transactionDtoMapper;
    }

    @PostMapping
    public TransactionResponseDto prepareTransaction(@AuthenticationPrincipal Jwt jwt) {
        TransactionResponseData transactionResponseData = transactionService.prepareTransaction(userDataMapper.toFirebaseUserData(jwt));
        return transactionDtoMapper.toTransactionResponseDto(transactionResponseData);
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionTid(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        TransactionResponseData transactionResponseData = transactionService.getTransactionByTid(
                userDataMapper.toFirebaseUserData(jwt), tid
        );
        return transactionDtoMapper.toTransactionResponseDto(transactionResponseData);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{tid}/payment")
    public void processTransactionByTid(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        transactionService.processTransaction(
                userDataMapper.toFirebaseUserData(jwt), tid
        );
    }

    @PatchMapping("/{tid}/success")
    public TransactionResponseDto successTransactionByTid(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Integer tid) {

        TransactionResponseData transactionResponseData =
                transactionService.successTransactionByTid(
                        userDataMapper.toFirebaseUserData(jwt),
                        tid
                );

        return transactionDtoMapper.toTransactionResponseDto(
                transactionResponseData
        );
    }
}









