package com.changgou.service;


import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 条件分页查询数据列表
     * @param brand 品牌对象
     * @param page 当前页
     * @param size 每页几条数据
     * @return
     */

    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);


    /**
     * 分页查询数据
     * @param page: 当前页
     * @param size: 每页几条数据
     *
     */
    PageInfo<Brand> findPage(Integer page, Integer size);


    /**
     * 返回全部商品列表
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据条件搜索数据列表
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 添加一条数据
     * @param brand
     * @return
     */
    Integer addBrand(Brand brand);

    /**
     * 删除一体数据
     * @param brand
     * @return
     */
    Integer delBrand(Brand brand);

    /**
     * 更新一条商品信息
     * @param brand
     * @return
     */
    Integer updateBrand(Brand brand);
}
