package com.querybuilder.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class SearchCriteria {
    private String field;
    private String operation;
    private Object value;
}
