package com.second.main.workbook.blog.config;//package com.second.project.other.blog.config;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * elasticsearch配置类
// */
//@Configuration
//public class EsConfiguration {
//    /**
//     * 协议
//     */
//    @Value("${elasticsearch.schema:http}")
//    private String schema;
//
//    /**
//     * 集群地址，如果有多个用“,”隔开
//     */
//    @Value("${elasticsearch.address}")
//    private String address;
//
//    /**
//     * 连接超时时间
//     */
//    @Value("${elasticsearch.connectTimeout}")
//    private int connectTimeout;
//
//    /**
//     * Socket 连接超时时间
//     */
//    @Value("${elasticsearch.socketTimeout}")
//    private int socketTimeout;
//
//    /**
//     * 获取连接的超时时间
//     */
//    @Value("${elasticsearch.connectionRequestTimeout}")
//    private int connectionRequestTimeout;
//
//    /**
//     * 最大连接数
//     */
//    @Value("${elasticsearch.maxConnectNum}")
//    private int maxConnectNum;
//
//    /**
//     * 最大路由连接数
//     */
//    @Value("${elasticsearch.maxConnectPerRoute}")
//    private int maxConnectPerRoute;
//
//    @Bean("elasticsearchClient")
//    public ElasticsearchClient elasticsearchClient() {
//        // 拆分地址
//        List<HttpHost> hostLists = new ArrayList<>();
//        String[] hostList = address.split(",");
//        for (String addr : hostList) {
//            String host = addr.split(":")[0];
//            String port = addr.split(":")[1];
//            hostLists.add(new HttpHost(host, Integer.parseInt(port), schema));
//        }
//        // 转换成 HttpHost 数组
//        HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});
//        // 构建连接对象
//        RestClientBuilder builder = RestClient.builder(httpHost);
//        // 异步连接延时配置
//        builder.setRequestConfigCallback(requestConfigBuilder -> {
//            requestConfigBuilder.setConnectTimeout(connectTimeout);
//            requestConfigBuilder.setSocketTimeout(socketTimeout);
//            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
//            return requestConfigBuilder;
//        });
//        // 异步连接数配置
//        builder.setHttpClientConfigCallback(httpClientBuilder -> {
//            httpClientBuilder.setMaxConnTotal(maxConnectNum);
//            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
//            return httpClientBuilder;
//        });
//
//        ElasticsearchTransport transport = new RestClientTransport(builder.build(), new JacksonJsonpMapper());
//
//        return new ElasticsearchClient(transport);
//    }
//}
