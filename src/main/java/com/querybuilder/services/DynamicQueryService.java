package com.querybuilder.services;

import com.querybuilder.dto.OrderCriteria;
import com.querybuilder.dto.SearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DynamicQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to return List<T>
    public <T> List<T> executeDynamicQueryList(
            String baseQuery,
            List<SearchCriteria> filters,
            List<String> groupByFields,
            List<SearchCriteria> havingConditions,
            List<OrderCriteria> orderByFields,
            Class<T> resultClass) {

        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        List<Object> params = new ArrayList<>();
        AtomicInteger paramIndex = new AtomicInteger(1);

        // Add WHERE conditions
        addFiltersToQuery(queryBuilder, filters, params, paramIndex);

        // Add GROUP BY conditions
        addGroupByToQuery(queryBuilder, groupByFields);

        // Add HAVING conditions
        addHavingToQuery(queryBuilder, havingConditions, params, paramIndex);

        // Add ORDER BY conditions
        addOrderByToQuery(queryBuilder, orderByFields);

        // Create the query
        Query query = entityManager.createNativeQuery(queryBuilder.toString(), resultClass);
        setQueryParameters(query, params);

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> Page<T> executeDynamicQueryPage(
            String baseQuery,
            List<SearchCriteria> filters,
            List<String> groupByFields,
            List<SearchCriteria> havingConditions,
            List<OrderCriteria> orderByFields,
            Pageable pageable, Class<T> resultClass) {

        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        List<Object> params = new ArrayList<>();
        AtomicInteger paramIndex = new AtomicInteger(1);

        // Add WHERE conditions
        addFiltersToQuery(queryBuilder, filters, params, paramIndex);

        // Add GROUP BY conditions
        addGroupByToQuery(queryBuilder, groupByFields);

        // Add HAVING conditions
        addHavingToQuery(queryBuilder, havingConditions, params, paramIndex);

        // Add ORDER BY conditions
        addOrderByToQuery(queryBuilder, orderByFields);

        // Add pagination (LIMIT and OFFSET)
        addPaginationToQuery(queryBuilder, pageable.getPageNumber(), pageable.getPageSize());

        // Create the query
        Query query = entityManager.createNativeQuery(queryBuilder.toString(), resultClass);
        setQueryParameters(query, params);

        // Execute the query and get the results
        List<T> results = query.getResultList();

        // Count total records (for pagination)
        long totalRecords = results.size();

        // Return a Page object
        return PageableExecutionUtils.getPage(results, pageable, () -> totalRecords);
    }

    private void addFiltersToQuery(StringBuilder queryBuilder, List<SearchCriteria> filters,
                                   List<Object> params, AtomicInteger paramIndex) {
        if (!filters.isEmpty()) {
            queryBuilder.append(" WHERE ");
            List<String> conditions = new ArrayList<>();
            for (SearchCriteria filter : filters) {
                conditions.add(filter.getField() + " " + filter.getOperation() + " ?" + paramIndex);
                params.add(filter.getValue());
                paramIndex.incrementAndGet();
            }
            queryBuilder.append(String.join(" AND ", conditions));
        }
    }

    private void addGroupByToQuery(StringBuilder queryBuilder, List<String> groupByFields) {
        if (!groupByFields.isEmpty()) {
            queryBuilder.append(" GROUP BY ").append(String.join(", ", groupByFields));
        }
    }

    private void addHavingToQuery(StringBuilder queryBuilder, List<SearchCriteria> havingConditions,
                                  List<Object> params, AtomicInteger paramIndex) {
        if (!havingConditions.isEmpty()) {
            queryBuilder.append(" HAVING ");
            List<String> havingClauses = new ArrayList<>();
            for (SearchCriteria having : havingConditions) {
                havingClauses.add(having.getField() + " " + having.getOperation() + " ?" + paramIndex);
                params.add(having.getValue());
                paramIndex.incrementAndGet();
            }
            queryBuilder.append(String.join(" AND ", havingClauses));
        }
    }

    private void addOrderByToQuery(StringBuilder queryBuilder, List<OrderCriteria> orderByFields) {
        if (!orderByFields.isEmpty()) {
            List<String> orderClauses = new ArrayList<>();
            for (OrderCriteria order : orderByFields) {
                orderClauses.add(order.getField() + " " + order.getDirection());
            }
            queryBuilder.append(" ORDER BY ").append(String.join(", ", orderClauses));
        }
    }

    private void addPaginationToQuery(StringBuilder queryBuilder, int page, int size) {
        queryBuilder.append(" LIMIT ").append(size).append(" OFFSET ").append(page * size);
    }

    private void setQueryParameters(Query query, List<Object> params) {
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }
    }
}

