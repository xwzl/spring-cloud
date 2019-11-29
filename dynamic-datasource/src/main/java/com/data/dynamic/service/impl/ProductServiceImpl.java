package com.data.dynamic.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.dynamic.mapper.ProductMapper;
import com.data.dynamic.model.Product;
import com.data.dynamic.service.ProductService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuweizhi
 * @since 2019-09-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    public ProductServiceImpl() {
        super();
    }

    @Override
    public ProductMapper getBaseMapper() {
        return super.getBaseMapper();
    }

    @Override
    protected boolean retBool(Integer result) {
        return super.retBool(result);
    }

    @Override
    protected Class<Product> currentModelClass() {
        return super.currentModelClass();
    }

    @Override
    protected SqlSession sqlSessionBatch() {
        return super.sqlSessionBatch();
    }

    @Override
    protected void closeSqlSession(SqlSession sqlSession) {
        super.closeSqlSession(sqlSession);
    }

    @Override
    protected String sqlStatement(SqlMethod sqlMethod) {
        return super.sqlStatement(sqlMethod);
    }

    @Override
    public boolean save(Product entity) {
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Product> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean saveOrUpdate(Product entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Product> entityList, int batchSize) {
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    public boolean remove(Wrapper<Product> wrapper) {
        return super.remove(wrapper);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(Product entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean update(Product entity, Wrapper<Product> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateBatchById(Collection<Product> entityList, int batchSize) {
        return super.updateBatchById(entityList, batchSize);
    }

    @Override
    @DS("slave_1")
    public Product getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @DS("slave_2")
    public Collection<Product> listByIds(Collection<? extends Serializable> idList) {
        return super.listByIds(idList);
    }

    @Override
    public Collection<Product> listByMap(Map<String, Object> columnMap) {
        return super.listByMap(columnMap);
    }

    @Override
    public Product getOne(Wrapper<Product> queryWrapper, boolean throwEx) {
        return super.getOne(queryWrapper, throwEx);
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Product> queryWrapper) {
        return super.getMap(queryWrapper);
    }

    @Override
    public int count(Wrapper<Product> queryWrapper) {
        return super.count(queryWrapper);
    }

    @Override
    public List<Product> list(Wrapper<Product> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    public IPage<Product> page(IPage<Product> page, Wrapper<Product> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<Product> queryWrapper) {
        return super.listMaps(queryWrapper);
    }

    @Override
    public <V> List<V> listObjs(Wrapper<Product> queryWrapper, Function<? super Object, V> mapper) {
        return super.listObjs(queryWrapper, mapper);
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<Product> page, Wrapper<Product> queryWrapper) {
        return super.pageMaps(page, queryWrapper);
    }

    @Override
    public <V> V getObj(Wrapper<Product> queryWrapper, Function<? super Object, V> mapper) {
        return super.getObj(queryWrapper, mapper);
    }

    @Override
    public boolean saveBatch(Collection<Product> entityList) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Product> entityList) {
        return false;
    }

    @Override
    public boolean update(Wrapper<Product> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Product> entityList) {
        return false;
    }

    @Override
    public Product getOne(Wrapper<Product> queryWrapper) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<Product> list() {
        return null;
    }

    @Override
    public IPage<Product> page(IPage<Product> page) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return null;
    }

    @Override
    public List<Object> listObjs() {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public List<Object> listObjs(Wrapper<Product> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<Product> page) {
        return null;
    }

    @Override
    public QueryChainWrapper<Product> query() {
        return null;
    }

    @Override
    public LambdaQueryChainWrapper<Product> lambdaQuery() {
        return null;
    }

    @Override
    public UpdateChainWrapper<Product> update() {
        return null;
    }

    @Override
    public LambdaUpdateChainWrapper<Product> lambdaUpdate() {
        return null;
    }

    @Override
    public boolean saveOrUpdate(Product entity, Wrapper<Product> updateWrapper) {
        return false;
    }
}
