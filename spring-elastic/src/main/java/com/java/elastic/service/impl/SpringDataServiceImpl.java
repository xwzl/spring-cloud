//package com.java.elastic.service.impl;
//
//import com.java.elastic.entity.EsProduct;
//import com.java.elastic.repository.EsProductRepository;
//import com.java.elastic.service.SpringDataService;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import jakarta.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * 商品搜索管理Service实现类
// */
//@Service
//public class SpringDataServiceImpl implements SpringDataService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataServiceImpl.class);
//
//    @Resource
//    private RestHighLevelClient client;
//
//    @Resource
//    private EsProductRepository esProductRepository;
//
//
//    @Override
//    public EsProduct create(Long id) {
//        EsProduct esProduct = new EsProduct();
//        esProduct.setId(id);
//        esProduct.setName("测试产品:" + id);
//        esProduct.setBrandId(1L);
//
//        esProductRepository.save(esProduct);
//        return null;
//    }
//
//    @Override
//    public void delete(Long id) {
//        esProductRepository.deleteById(id);
//
//    }
//
//    @Override
//    public void delete(List<Long> ids) {
//        if (ids != null && ids.size() > 0) {
//            List<EsProduct> esProductList = new ArrayList<>();
//            for (Long id : ids) {
//                EsProduct esProduct = new EsProduct();
//                esProduct.setId(id);
//                esProductList.add(esProduct);
//            }
//            esProductRepository.deleteAll(esProductList);
//        }
//
//    }
//
//    @Override
//    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        return esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
//    }
//
//}
