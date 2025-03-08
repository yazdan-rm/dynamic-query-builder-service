package com.querybuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DynamicQueryRequest {
    private List<SearchCriteria> filters;
    private List<String> groupBy;
    private List<SearchCriteria> having;
    private List<OrderCriteria> orderBy;
    private int page;
    private int size;
}
