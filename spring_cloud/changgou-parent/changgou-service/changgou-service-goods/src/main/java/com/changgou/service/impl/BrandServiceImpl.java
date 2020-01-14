package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页 + 条件 查询
     * @param brand 品牌对象
     * @param page 当前页
     * @param size 每页几条数据
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = createExample(brand);
        List<Brand> brands = this.brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brands);
    }

    /**
     * 分页查询
     * @param page: 当前页
     * @param size: 每页几条数据
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        // 分页实现
        PageHelper.startPage(page, size);
        // 查询集合
        List<Brand> brands = brandMapper.selectAll();
        // 封装一个分页
        return new PageInfo<>(brands);
    }

    @Override
    public List<Brand> findAll() {
        return this.brandMapper.selectAll();
    }

    /**
     * 模糊搜索
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {

        return this.brandMapper.selectByExample(createExample(brand));
    }

    /**
     * 条件构造
     * @param brand
     * @return
     */
    public  Example createExample(Brand brand){
        // 自定义搜索对象
        Example example = new Example(Brand.class);
        // 条件构造器
        Example.Criteria criteria = example.createCriteria();

        if (brand != null) {
            // 判断传入的名字不为空
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            // 判断传入的品牌首字母 名字不为空
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }
    @Override
    public Integer addBrand(Brand brand) {
        return this.brandMapper.insert(brand);
    }

    @Override
    public Integer delBrand(Brand brand) {
        return this.brandMapper.deleteByPrimaryKey(brand);
    }

    @Override
    public Integer updateBrand(Brand brand) {
        return this.brandMapper.updateByPrimaryKeySelective(brand);
    }
}
