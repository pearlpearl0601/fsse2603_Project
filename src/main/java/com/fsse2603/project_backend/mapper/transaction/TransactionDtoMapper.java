package com.fsse2603.project_backend.mapper.transaction;

import com.fsse2603.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2603.project_backend.data.transaction.dto.response.TransactionResponseDto;
import com.fsse2603.project_backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2603.project_backend.data.transactionProduct.dto.response.TransactionItemResponseDto;
import com.fsse2603.project_backend.mapper.transactionProduct.TransactionProductDtoMapper;
import com.fsse2603.project_backend.mapper.user.UserDataMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionDtoMapper {

    private final UserDataMapper userDataMapper;
    private final TransactionProductDtoMapper transactionProductDtoMapper;

    public TransactionDtoMapper(UserDataMapper userDataMapper, TransactionProductDtoMapper transactionProductDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.transactionProductDtoMapper = transactionProductDtoMapper;
    }

    public TransactionResponseDto toTransactionResponseDto(
            TransactionResponseData responseData) {

        TransactionResponseDto dto = new TransactionResponseDto();

        dto.setTid(responseData.getTid());
        dto.setBuyerUid(
                responseData.getBuyer().getUid()
        );
        dto.setDatetime(responseData.getDateTime().toString());
        dto.setStatus(responseData.getStatus().toString());
        dto.setTotal(responseData.getTotal());
        dto.setItems(transactionProductDtoMapper.toTransactionItemResponseDtoList(responseData.getTransactionProductList()));


        List<TransactionProductResponseData> productDataList =
                responseData.getTransactionProductList();


        List<TransactionItemResponseDto> itemDtoList =
                new ArrayList<>();

        if (productDataList != null) {
            for (TransactionProductResponseData productData : productDataList) {
                TransactionItemResponseDto itemDto = transactionProductDtoMapper.toTransactionItemResponseDto(productData);
                itemDtoList.add(itemDto);
            }
        }
        dto.setItems(itemDtoList);
        return dto;

    }
}


