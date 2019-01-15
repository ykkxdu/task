package com.xihang.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.hateoas.PagedResources.wrap;

/**
 * 多字段模糊化搜索抽象类
 *
 * @param <T> 需要模糊化搜索的实体类
 */
public abstract class FuzzySearchController<T> {

    // 根据实体类声明其数据库操作接口
    @Autowired
    protected JpaRepository<T, Long> repository;

    // 匹配规则
    protected final ExampleMatcher defaultExampleMatcher =
        ExampleMatcher.matching().
            withIgnoreCase(). // 忽略大小写
            withIgnorePaths("id"). // 忽略 id（因为有 id 会变成单个查询）
            withIgnoreNullValues(). // 忽略 null 值
            withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // 字符串匹配规则为包含

    @GetMapping("/fuzzy")
    public ResponseEntity<PagedResources> fuzzySearch(T x, Pageable pageable) {
        Page<T> data = repository.findAll(Example.of(x, defaultExampleMatcher), pageable);
        PagedResources.PageMetadata pageMetadata =
            new PagedResources.PageMetadata(
                data.getSize(), // 每页大小
                data.getNumber(), // 当前页数（从 0 开始）
                data.getTotalElements(), // 总记录条数
                data.getTotalPages() // 总页数
            );
        return ResponseEntity.ok(wrap(data, pageMetadata));
    }
}