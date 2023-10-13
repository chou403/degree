package com.second.project.other.blog.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SearchBody {
    private String index;

//    private SearchSourceBuilder sourceBuilder;

    private String[] includeFields;

    private String[] excludeFields;

//    FieldSortBuilder sortBuilder;

    private List<String> highlightFields;
}
