package com.second.common.aop.filter;

import com.second.common.bean.reponse.Result;
import com.second.common.bean.StatusEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 对 FilterRestTemplate 进行包装扩展
 * {@code @author}  chouchou
 * {@code @date}    2023/5/4 15:05
 */
public class ExtractRestTemplate extends FilterRestTemplate{

    private RestTemplate restTemplate;

    public ExtractRestTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public <T> Result<T> postForEntityWithNoException(String url, Object request, Class<T> responseType, Object... uriVariables) {
        Result<T> restResponseDTO = Result.success();
        ResponseEntity<T> tResponseEntity;
        try {
            tResponseEntity = restTemplate.postForEntity(url, request, responseType, uriVariables);
            restResponseDTO.setData(tResponseEntity.getBody());
//            restResponseDTO.setMessage(tResponseEntity.getStatusCode());
            restResponseDTO.setCode(tResponseEntity.getStatusCode().value());
        }catch (Exception e){
            restResponseDTO.setCode(StatusEnum.UNKNOWN_ERROR.getCode());
            restResponseDTO.setMsg(e.getMessage());
            restResponseDTO.setData(null);
        }
        return restResponseDTO;
    }

}
