package com.querybuilder.controller;

import com.querybuilder.dto.DynamicQueryRequest;
import com.querybuilder.dto.ResultDTO;
import com.querybuilder.dto.SearchCriteria;
import com.querybuilder.services.DynamicQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dynamic-query")
public class DynamicQueryController {

    private final DynamicQueryService dynamicQueryService;

    @GetMapping("/filter/list")
    public List<ResultDTO> getFilteredResultsList(
            @RequestParam String name,
            @RequestParam Double minSalary) {

        String baseQuery = "SELECT p.name, j.job_name as jobName, l.city FROM person p " +
                "INNER JOIN job j ON p.id = j.fk_person " +
                "LEFT JOIN location l ON l.fk_job = j.id";

        List<SearchCriteria> filters = new ArrayList<>();
        filters.add(new SearchCriteria("p.name", "LIKE", "%" + name + "%"));
        filters.add(new SearchCriteria("j.salary", ">", minSalary));

        return dynamicQueryService.executeDynamicQueryList(baseQuery, filters,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), ResultDTO.class);
    }


    @GetMapping("/filter/page")
    public Page<ResultDTO> getFilteredResultsPage(
            @RequestParam String name,
            @RequestParam Double minSalary,
            @RequestParam int page,
            @RequestParam int size) {

        String baseQuery = "SELECT p.name, j.job_name, l.city FROM person p " +
                "INNER JOIN job j ON p.id = j.fk_person " +
                "LEFT JOIN location l ON l.fk_job = j.id";

        List<SearchCriteria> filters = new ArrayList<>();
        filters.add(new SearchCriteria("p.name", "LIKE", "%" + name + "%"));
        filters.add(new SearchCriteria("j.salary", ">", minSalary));

        Pageable pageable = PageRequest.of(page, size);

        return dynamicQueryService.executeDynamicQueryPage(baseQuery, filters,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), pageable, ResultDTO.class);
    }

    @PostMapping("/dynamic")
    public Page<ResultDTO> getDynamicQueryResults(@RequestBody DynamicQueryRequest request) {
        String baseQuery = "SELECT p.name, j.job_name as jobName, l.city FROM person p " +
                "INNER JOIN job j ON p.id = j.fk_person " +
                "LEFT JOIN location l ON l.fk_job = j.id";

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        return dynamicQueryService.executeDynamicQueryPage(
                baseQuery,
                request.getFilters(),
                request.getGroupBy(),
                request.getHaving(),
                request.getOrderBy(),
                pageable,
                ResultDTO.class
        );
    }
}
