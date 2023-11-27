package com.second.main.workbook.blog.controller;//package com.second.project.other.blog.controller;
//
//
//import com.second.common.domain.Result;
//import com.second.project.other.blog.domain.Pagination;
//import com.second.project.other.blog.domain.dto.BlogPaperDTO;
//import com.second.project.other.blog.service.MdBlogService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
///**
// * md文档控制器类
// */
//@RestController
//@RequestMapping("/api/blog")
//public class BlogController {
//
//    private final MdBlogService mdBlogService;
//
//    public BlogController(MdBlogService mdBlogService) {
//        this.mdBlogService = mdBlogService;
//    }
//
//    /**
//     * 获取文档
//     *
//     * @return
//     */
//    @ApiOperation(value = "获取文档", notes = "获取文档", produces = "application/json")
//    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"),
//            @ApiResponse(code = 204, message = "没有内容")})
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> get(@PathVariable("id") String id) {
//        return new ResponseEntity<>(mdBlogService.getBlogById(id), HttpStatus.OK);
//    }
//
//    /**
//     * 分页查询
//     *
//     * @param searchValue
//     * @param pagination
//     * @return
//     */
//    @ApiOperation(value = "分页查询", notes = "分页查询", produces = "application/json")
//    @ApiResponses({@ApiResponse(code = 200, message = "查询成功")})
//    @RequestMapping(value = "/query", method = RequestMethod.GET)
//    public ResponseEntity<Result> query(@Valid String searchValue, @Valid Pagination pagination) {
//        return ResponseEntity.ok(mdBlogService.query(searchValue, pagination));
//    }
//
//    /**
//     * 创建文档
//     *
//     * @return
//     */
//    @ApiOperation(value = "创建文档", notes = "创建文档", produces = "application/json")
//    @ApiResponses({@ApiResponse(code = 200, message = "新增成功"),
//            @ApiResponse(code = 204, message = "没有内容")})
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Result> create(@RequestBody BlogPaperDTO blogPaper) {
//        return new ResponseEntity<>(mdBlogService.createMdBlog(blogPaper), HttpStatus.OK);
//    }
//
//    /**
//     * 删除文档
//     *
//     * @return
//     */
//    @ApiOperation(value = "删除文档", notes = "删除文档", produces = "application/json")
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
//        mdBlogService.deleteBlog(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//}
