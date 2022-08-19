package com.reclebooks.recle.dto.postdto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseSalesList {

    private List<ResponseSalesDto> responseSalesDtoList = new ArrayList<>();

    private int count;

    public ResponseSalesList(List<ResponseSalesDto> responseSalesDtoList, int count) {
        this.responseSalesDtoList = responseSalesDtoList;
        this.count = count;
    }
}
