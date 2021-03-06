package com.storyxc.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Image;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/14 18:34
 */
@RestController
@RequestMapping("/story/image")
public class ImageController {

    @Autowired
    private ImageService imageService;


    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageInfo<Image> result = imageService.findPage(queryPageBean);
        return new Result(true, StatusCode.OK,"查询壁纸列表成功",result);
    }

    @GetMapping
    public Result getImageByName(String fullName){
        Image image = imageService.getImageByName(fullName);
        return new Result(true,StatusCode.OK,"查询壁纸成功",image);
    }

    @GetMapping("/like")
    public Result likeImage(String date, HttpServletRequest request, HttpServletResponse response){
        Integer count = imageService.updateLikeCount(date,request,response);
        if(count == null){
            return new Result(false,"不能重复点赞");
        }
        return new Result(true,StatusCode.OK,"点赞成功",count);
    }

}
