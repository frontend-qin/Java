package com.changgou.controller;


import com.changgou.service.BrandService;

import com.goods.pojo.Brand;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin // 跨域
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<List<Brand>> selectAll(){
       return new Result<List<Brand>>(true, StatusCode.OK, "",this.brandService.findAll());
    }
}
