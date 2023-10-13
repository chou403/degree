package com.second.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/7/15
 * {@code @description} WebSocket config
 */
@Component
public class WebSocketConfig {

    /**
     * WebSocket的支持
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
