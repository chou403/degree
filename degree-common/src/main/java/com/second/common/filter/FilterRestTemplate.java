package com.second.common.filter;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * 将 RestTemplate 作为底层包装器
 * {@code @author}  JSY
 * {@code @date}    2023/5/4 15:04
 */
@AllArgsConstructor
public abstract class FilterRestTemplate implements RestOperations {

    @Delegate
    protected volatile RestTemplate restTemplate;
}
