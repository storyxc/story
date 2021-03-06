package com.storyxc.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Article;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/18 15:25
 */
@RestController
@RequestMapping("/story/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public Result loadArticle(@PathVariable String id){
        articleService.updateViewCount(id);
        return new Result(true,StatusCode.OK,"查询文章成功",articleService.queryArticleById(id));
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageInfo<Article> result = articleService.findPage(queryPageBean);
        return new Result(true,StatusCode.OK, "查询文章列表成功", result);
    }

    @PostMapping("/findPage/manage")
    public Result findPageManage(@RequestBody QueryPageBean queryPageBean) {
        PageInfo<Article> result = articleService.findPageManage(queryPageBean);
        return new Result(true,StatusCode.OK, "查询文章列表成功", result);
    }

    @PreAuthorize("hasAnyAuthority('ARTICLE_ADD')")
    @PostMapping
    public Result addArticle(@RequestBody Article article){
        articleService.addArticle(article,"add");
        return new Result(true, StatusCode.OK,"文章发布成功");
    }

    @PostMapping("/save")
    public Result saveArticle(@RequestBody Article article){
        articleService.updateArticle(article);
        return new Result(true,StatusCode.OK,"草稿保存成功");
    }

    @PreAuthorize("hasAnyAuthority('ARTICLE_EDIT')")
    @GetMapping("/edit/{id}")
    public Result editArticle(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询文章成功",articleService.queryArticleById(id));
    }

    @GetMapping("/blogStat")
    public Result queryBlogStat(){
        Map<String,Integer> map = articleService.queryBlogStat();
        return new Result(true,StatusCode.OK,"查询博客状态成功",map);
    }

    @GetMapping("/hot")
    public Result queryHotArticle(){
        List<Article> articleList = articleService.queryHotArticle();
        return new Result(true,StatusCode.OK,"查询热门文章成功",articleList);
    }

    @DeleteMapping
    public Result deleteArticle(Integer id){
        articleService.deleteArticle(id);
        return new Result(true,StatusCode.OK,"删除文章成功");
    }

    @GetMapping
    public Result getArticleById(Integer id){
        return new Result(true,StatusCode.OK,"查询文章成功",articleService.getArticleById(id));
    }

    @PutMapping
    public Result editArticle(@RequestBody Article article){
        articleService.editArticleInfo(article);
        return new Result(true,StatusCode.OK,"修改文章成功");
    }

}
