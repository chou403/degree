package com.second.project.other.blog.service;//package com.second.project.other.blog.service;
//
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch._types.FieldValue;
//import co.elastic.clients.elasticsearch.core.*;
//import co.elastic.clients.elasticsearch.core.search.Hit;
//import com.second.common.domain.Result;
//import com.second.project.other.blog.domain.BlogPaper;
//import com.second.project.other.blog.domain.Pagination;
//import com.second.project.other.blog.domain.SearchBody;
//import com.second.project.other.blog.domain.dto.BlogPaperDTO;
//import com.second.project.other.blog.domain.mapper.BlogPaperMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * 文档业务逻辑类
// */
//@Slf4j
//@Service
//public class DocService {
//    private final ElasticsearchClient elasticsearchClient;
//
//    public DocService(ElasticsearchClient elasticsearchClient) {
//        this.elasticsearchClient = elasticsearchClient;
//    }
//
//    /**
//     * 增加文档信息
//     */
//    public Result addDocument(BlogPaper blog) {
//        try {
//            IndexResponse indexResponse = elasticsearchClient.index(i -> i
//                    .index("blog")
//                    .id(blog.getId())
//                    .document(blog));
//
//            return Result.success(indexResponse);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.error("创建文档失败", e.getMessage());
//        }
//    }
//
//    /**
//     * 获取文档信息
//     */
//    public BlogPaperDTO getDocById(String id) {
//        try {
//            GetResponse<BlogPaper> getResponse = elasticsearchClient.get(g -> g
//                    .index("blog")
//                    .id(id), BlogPaper.class);
//
//            return BlogPaperMapper.toDTO(getResponse.source());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 分页查询
//     */
//    public Result queryDoc(SearchBody searchBody, Pagination pagination) {
//        try {
//            SearchResponse<BlogPaper> searchResponse = elasticsearchClient.search(s -> s
//                            .index(searchBody.getIndex())
//                            .query(q -> q
//                                    .term(t -> t.field("name").value(FieldValue.of("value1"))))
//                            .highlight(h -> h.fields("name", f -> f.preTags("<font color='red'>").postTags("</font>")))
//                            .from(pagination.getPage())
//                            .size(pagination.getPageSize())
//                    , BlogPaper.class);
//
//            log.info("total:{}", searchResponse.hits());
//
//            List<Hit<BlogPaper>> blogList = searchResponse.hits().hits();
//
//            return Result.success(blogList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 使用分词查询,并分页
//     *
//     * @return`
//     */
////    public Result search(String index, SearchSourceBuilder sourceBuilder) throws IOException {
////        SearchRequest searchRequest = new SearchRequest.Builder().index("blog").build();
////
////
////        SearchRequest searchRequest = new SearchRequest();
////        searchRequest.indices(index);
////        searchRequest.source(sourceBuilder);
////
////        return elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
////    }
//
//    /**
//     * 更新文档信息
//     */
//    public void updateDocument(BlogPaper blogPaper) {
//        try {
//            UpdateResponse<BlogPaper> updateResponse = elasticsearchClient.update(u -> u
//                    .index("blog")
//                    .id(blogPaper.getId()), BlogPaper.class);
//
//            log.info("更新状态:{}", updateResponse.result());
//        } catch (Exception e) {
//            log.error("", e);
//        }
//    }
//
//    /**
//     * 删除文档信息
//     */
//    public void deleteDocument(String id) {
//        try {
//            DeleteResponse deleteResponse = elasticsearchClient.delete(d -> d
//                    .index("blog")
//                    .id(id));
//
//            log.info("删除状态:{}", deleteResponse.result());
//        } catch (IOException e) {
//            log.error("删除异常", e);
//        }
//    }
//}
