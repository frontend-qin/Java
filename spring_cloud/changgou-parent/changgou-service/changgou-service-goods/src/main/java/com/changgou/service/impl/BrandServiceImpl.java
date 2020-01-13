package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.service.BrandService;
import com.goods.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return this.brandMapper.selectAll();
    }
}
