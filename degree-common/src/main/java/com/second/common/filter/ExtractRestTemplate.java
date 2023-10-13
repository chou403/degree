package com.second.common.filter;

import com.second.common.bean.reponse.ResponseBean;
import com.second.common.bean.reponse.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 对 FilterRestTemplate 进行包装扩展
 * {@code @author}  JSY
 * {@code @date}    2023/5/4 15:05
 */
public class ExtractRestTemplate extends FilterRestTemplate{

    private RestTemplate restTemplate;

    public ExtractRestTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public <T> ResponseBean<T> postForEntityWithNoException(String url, Object request, Class<T> responseType, Object... uriVariables) {
        ResponseBean<T> restResponseDTO = new ResponseBean<T>();
        ResponseEntity<T> tResponseEntity;
        try {
            tResponseEntity = restTemplate.postForEntity(url, request, responseType, uriVariables);
            restResponseDTO.setData(tResponseEntity.getBody());
//            restResponseDTO.setMessage(tResponseEntity.getStatusCode());
            restResponseDTO.setCode(tResponseEntity.getStatusCodeValue());
        }catch (Exception e){
            restResponseDTO.setCode(ResponseCode.UNKNOWN_ERROR.getCode());
            restResponseDTO.setMessage(e.getMessage());
            restResponseDTO.setData(null);
        }
        return restResponseDTO;
    }

}
