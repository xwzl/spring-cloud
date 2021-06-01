package com.java.elastic.controller;

import com.java.elastic.entity.Item;
import com.java.elastic.repository.ItemRepository;
import io.swagger.annotations.Api;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * must 表示两个条件必须满足，should 表示两个条件之一满足就行
 *
 * @author xuweizhi
 * @since 2019/07/16 15:55
 */
@Api
@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Resource
    private ItemRepository itemRepository;


    @GetMapping("/save")
    public void insert() {
        Item item = new Item(1L, "小米手机7", " 手机", "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.save(item);
    }

    @GetMapping("/saves")
    public void insertList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.baidu.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    /**
     * elasticsearch中本没有修改，它的修改原理是该是先删除在新增
     * 修改和新增是同一个接口，区分的依据就是id。
     */
    @GetMapping("/update")
    public void update() {
        Item item = new Item(1L, "苹果XSMax", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.save(item);
    }

    /**
     * @Description:定义查询方法,含对价格的降序、升序查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @GetMapping("/final")
    public void testQueryAll() {
        // 查找所有
        //Iterable<Item> list = this.itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> list = this.itemRepository.findAll(Sort.by("price").ascending());

        for (Item item : list) {
            System.out.println(item);
        }
    }

    @GetMapping("/final1")
    public void testMatchQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "手机"));
        //// 搜索，获取结果
        //Page<Item> items = this.itemRepository.search(queryBuilder.build());
        //// 总条数
        //long total = items.getTotalElements();
        //System.out.println("total = " + total);
        //for (Item item : items) {
        //    System.out.println(item);
        //}
    }

    /**
     * termQuery:功能更强大，除了匹配字符串以外，还可以匹配
     * int/long/double/float/....
     */
    @GetMapping("/final2")
    public void testTermQuery() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.termQuery("price", 3299.00));
        //// 查找
        //Page<Item> page = this.itemRepository.search(builder.build());
        //
        //for (Item item : page) {
        //    System.out.println(item);
        //}
    }

    /**
     * 布尔查询
     */
    @GetMapping("/final3")
    public void testBooleanQuery() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        builder.withQuery(
                QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title", "华为"))
                        .must(QueryBuilders.matchQuery("brand", "华为"))
        );

        // 查找
        //Page<Item> page = this.itemRepository.search(builder.build());
        //for (Item item : page) {
        //    System.out.println(item);
        //}
    }

    /**
     * 模糊查询
     */
    @GetMapping("/final4")
    public void testFuzzyQuery() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.fuzzyQuery("title", "手机*"));
        //Page<Item> page = this.itemRepository.search(builder.build());
        //for (Item item : page) {
        //    System.out.println(item);
        //}

    }

    /**
     * 分页查询
     */
    @GetMapping("/final5")
    public void searchByPage() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
        // 分页：
        int page = 0;
        int size = 2;
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 搜索，获取结果
        //Page<Item> items = this.itemRepository.search(queryBuilder.build());
        //// 总条数
        //long total = items.getTotalElements();
        //System.out.println("总条数 = " + total);
        //// 总页数
        //System.out.println("总页数 = " + items.getTotalPages());
        //// 当前页
        //System.out.println("当前页：" + items.getNumber());
        //// 每页大小
        //System.out.println("每页大小：" + items.getSize());
        //
        //for (Item item : items) {
        //    System.out.println(item);
        //}
    }

    /**
     * 排序查询
     */
    @GetMapping("/final6")
    public void searchAndSort() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));

        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));

        //// 搜索，获取结果
        //Page<Item> items = this.itemRepository.search(queryBuilder.build());
        //// 总条数
        //long total = items.getTotalElements();
        //System.out.println("总条数 = " + total);
        //
        //for (Item item : items) {
        //    System.out.println(item);
        //}
    }

    /**
     * 按照品牌brand进行分组
     */
    @GetMapping("/final7")
    public void testAgg() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("brands").field("brand"));
        // 2、查询,需要把结果强转为AggregatedPage类型
        //AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        //// 3、解析
        //// 3.1、从结果中取出名为brands的那个聚合，
        //// 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        //StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        //// 3.2、获取桶
        //List<StringTerms.Bucket> buckets = agg.getBuckets();
        //// 3.3、遍历
        //for (StringTerms.Bucket bucket : buckets) {
        //    // 3.4、获取桶中的key，即品牌名称
        //    System.out.println(bucket.getKeyAsString());
        //    // 3.5、获取桶中的文档数量
        //    System.out.println(bucket.getDocCount());
        //}

    }

    /**
     * 嵌套聚合，求平均值
     */
    @GetMapping("/final8")
    public void testSubAgg() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("brands").field("brand")
                        // 在品牌聚合桶内进行嵌套聚合，求平均值
                        .subAggregation(AggregationBuilders.avg("priceAvg").field("price"))
        );
        // 2、查询,需要把结果强转为AggregatedPage类型
        //AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        //// 3、解析
        //// 3.1、从结果中取出名为brands的那个聚合，
        //// 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        //StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        //// 3.2、获取桶
        //List<StringTerms.Bucket> buckets = agg.getBuckets();
        //// 3.3、遍历
        //for (StringTerms.Bucket bucket : buckets) {
        //    // 3.4、获取桶中的key，即品牌名称  3.5、获取桶中的文档数量
        //    System.out.println(bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "台");
        //
        //    // 3.6.获取子聚合结果：
        //    InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
        //    System.out.println("平均售价：" + avg.getValue());
        //}

    }


    @GetMapping("/create")
    public void createIndex() {
        //elasticsearchTemplate.createIndex(Item.class);
    }

    @GetMapping("/delete")
    public void deleteIndex() {
        //elasticsearchTemplate.deleteIndex(Item.class);
    }
}
