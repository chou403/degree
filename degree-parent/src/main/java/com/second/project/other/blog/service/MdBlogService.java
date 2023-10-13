package com.second.project.other.blog.service;//package com.second.project.other.blog.service;
//
//import com.second.common.domain.Result;
//import com.second.common.util.UUIDUtil;
//import com.second.project.other.blog.domain.BlogPaper;
//import com.second.project.other.blog.domain.Pagination;
//import com.second.project.other.blog.domain.SearchBody;
//import com.second.project.other.blog.domain.dto.BlogPaperDTO;
//import com.second.project.other.blog.domain.mapper.BlogPaperMapper;
//import org.springframework.stereotype.Service;
//
///**
// * md文档业务逻辑类
// */
//@Service
//public class MdBlogService {
//
//    private final DocService docService;
//
//    public MdBlogService(DocService docService) {
//        this.docService = docService;
//    }
//
//    public Result createMdBlog(BlogPaperDTO dto) {
//        BlogPaper blog = BlogPaperMapper.toEntity(dto);
//        blog.setId(UUIDUtil.creatUUID());
//        blog.setAuthor("admin");
//        blog.setCreateDate(System.currentTimeMillis());
//        return docService.addDocument(blog);
//    }
//
//    public Result getBlogById(String id) {
//        BlogPaperDTO blogPaper = docService.getDocById(id);
//        if (blogPaper == null) {
//            return Result.error("查询失败");
//        }
//
//        return Result.success(blogPaper);
//    }
//
//    public Result query(String searchValue, Pagination pagination) {
////        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
////        if (StringUtils.isNotBlank(searchValue)) {
////            QueryBuilder builder = QueryBuilders.multiMatchQuery(searchValue, "title", "description");
////            sourceBuilder.query(builder);
////        } else {
////            // 默认全部
////            sourceBuilder.query(QueryBuilders.matchAllQuery());
////        }
////
////        //返回字段
////        String[] includeFields = new String[]{"id", "title", "labels", "description", "author", "createDate"};
////        //排除字段
////        String[] excludeFields = new String[]{"content"};
////        // 按创建时间排序
////        FieldSortBuilder sortBuilder = new FieldSortBuilder("createDate").order(SortOrder.DESC);
////        SearchBody searchBody = new SearchBody();
////
////        List<String> highlightFields = new ArrayList<>();
////        highlightFields.add("title");
////        highlightFields.add("description");
////        searchBody.setIndex("blog")
////                .setSourceBuilder(sourceBuilder)
////                .setIncludeFields(includeFields)
////                .setExcludeFields(excludeFields)
////                .setSortBuilder(sortBuilder)
////                .setHighlightFields(highlightFields);
//
//        String[] includeFields = new String[]{"id", "title", "labels", "description", "author", "createDate"};
////        FieldSortBuilder sortBuilder = new FieldSortBuilder("createDate").order(SortOrder.DESC);
//
//        SearchBody searchBody = new SearchBody();
//        searchBody.setIndex("blog")
//                .setIncludeFields(includeFields);
//
//        return docService.queryDoc(searchBody, pagination);
//    }
//
////    private Result formatResponse(SearchResponse response) {
////        if (Objects.isNull(response)) {
////            return null;
////        }
////        List<BlogPaperDTO> data = new ArrayList<>();
////        SearchHits hits = response.getHits();
////        SearchHit[] searchHits = hits.getHits();
////        for (SearchHit hit : searchHits) {
////            BlogPaper responseBlog = JSON.parseObject(hit.getSourceAsString(), BlogPaper.class);
////            //解析高亮字段
////            //获取当前命中的对象的高亮的字段
////            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
////
////            highlightFields.entrySet().forEach(item -> {
////                String key = item.getKey();
////                HighlightField value = highlightFields.get(key);
////                String fieldValue = "";
////                if (value != null) {
////                    //获取该高亮字段的高亮信息
////                    Text[] fragments = value.getFragments();
////                    //将前缀、关键词、后缀进行拼接
////                    for (Text fragment : fragments) {
////                        fieldValue += fragment;
////                    }
////                    switch (key) {
////                        case "title":
////                            responseBlog.setTitle(fieldValue);
////                        case "description":
////                            responseBlog.setDescription(fieldValue);
////                    }
////                }
////            });
////            data.add(BlogPaperMapper.toDTO(responseBlog));
////        }
////        TotalHits totalHits = hits.getTotalHits();
////        long numHits = totalHits.value;
////        Map<String, Object> result = new HashMap<>();
////        result.put("total", numHits);
////        result.put("content", data);
////        return Result.success(result);
////    }
//
//    public void deleteBlog(String id) {
//        docService.deleteDocument(id);
//    }
//}
