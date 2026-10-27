package com.fsse2603.project_backend.mapper.transactionProduct;

import com.fsse2603.project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2603.project_backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2603.project_backend.data.transactionProduct.dto.response.TransactionItemResponseDto;
import com.fsse2603.project_backend.mapper.product.ProductDtoMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDtoMapper {

    private final ProductDtoMapper productDtoMapper;

    public TransactionProductDtoMapper(
            ProductDtoMapper productDtoMapper) {

        this.productDtoMapper = productDtoMapper;
    }

    public TransactionItemResponseDto toTransactionItemResponseDto(
            TransactionProductResponseData transactionProductResponseData) {

        TransactionItemResponseDto dto =
                new TransactionItemResponseDto();

        dto.setTpid(
                transactionProductResponseData.getTpid()
        );

        dto.setQuantity(
                transactionProductResponseData.getQuantity()
        );

        dto.setProduct(
                productDtoMapper.toProductResponseDto(
                        transactionProductResponseData
                )
        );

        BigDecimal price =
                transactionProductResponseData.getPrice();

        Integer quantity =
                transactionProductResponseData.getQuantity();

        if (price != null && quantity != null) {

            dto.setSubtotal(
                    price.multiply(
                            BigDecimal.valueOf(
                                    quantity.longValue()
                            )
                    )
            );
        }

        return dto;
    }

    public List<TransactionItemResponseDto>
    toTransactionItemResponseDtoList(
            List<TransactionProductResponseData> productDataList) {

        List<TransactionItemResponseDto> result =
                new ArrayList<>();

        for (TransactionProductResponseData productData
                : productDataList) {

            result.add(
                    toTransactionItemResponseDto(
                            productData
                    )
            );
        }

        return result;
    }
}