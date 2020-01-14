package com.changgou.controller;


import com.changgou.service.BrandService;

import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品品牌管理")
@RestController
@RequestMapping(value = "/brand")
@CrossOrigin // 跨域
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 条件 + 分页搜索查询品牌列表数据
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "条件分页搜索")
    @PostMapping("/serarch/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand,@PathVariable(value = "page") Integer page,
                                            @PathVariable(value = "size") Integer size){
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "", pageInfo);
    }



    /**
     * 分页搜索查询品牌列表数据
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "分页搜索查询品牌列表")
    @GetMapping("/serarch/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page") Integer page,
                                            @PathVariable(value = "size") Integer size){

        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "", pageInfo);
    }
    /**
     * 获取商品列表
     * @return brnadList
     */
    @GetMapping
    @ApiOperation(value = "获取所有的品牌商品列表")
    public Result<List<Brand>> selectAll(){

       return new Result<List<Brand>>(true, StatusCode.OK, "", this.brandService.findAll());
    }

    /**
     * 添加一条商品信息
     * @param brand Brand 对象
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加一条商品信息")
    public Result<String> addBrand(@RequestBody Brand brand){
        this.brandService.addBrand(brand);
        return new Result<String>(true, StatusCode.OK , "添加成功", "");
    }

    /**
     * 更新一条商品信息
     * @param brand
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新一条商品信息", notes = "根据id进行更新信息")
    public Result<String> updateBrandById(@RequestBody Brand brand){
        this.brandService.updateBrand(brand);
        return new Result<String>(true, StatusCode.OK, "更新成功", "");
    }
    /**
     * 删除一条商品
     *
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除一条商品信息", notes = "根据id进行删除")
    public Result<String> deleteBrand(@RequestBody Brand brand){
        this.brandService.delBrand(brand);
        return new Result<String>(true, StatusCode.OK, "删除成功","");
    }
    /**
     * 条件模糊查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "模糊搜索", notes = "根据品牌名称和品牌首字母查询")
    public  Result<List<Brand>> findList(@RequestBody Brand brand){
        List<Brand>  list = this.brandService.findList(brand);
        return new Result<>(true, StatusCode.OK, "", list);
    }
}
