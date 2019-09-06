package com.data.dynamic.controller;


import com.data.dynamic.model.Product;
import com.data.dynamic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuweizhi
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/dynamic/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/121")
    public void test() {
        Product byId = productService.getById(1);
        System.out.println(byId);

        Collection<Product> products = productService.listByIds(Collections.singletonList("1"));
        System.out.println(products);

    }

}
