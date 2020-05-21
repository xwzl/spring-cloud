package com.spring.mongo.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2020/05/20 16:26
 */
@Slf4j
@Data
@Component
public class TopicCache {

    private static final String TOPIC_STATUS = "topicStatus";

    @Resource
    private MongoTemplate mongoTemplate;

    //@Resource
    //private MQSender mqSender;

    //@Value("${spring.application.name}")
    //private String topic;

    private final Map<String, Object> cache = new HashMap<>();

    private final Object lockObject = new Object();

    private volatile long expireTime;

    /**
     * 創建 bean 初始化緩存
     */
    @PostConstruct
    public void postConstruct() {
        //List<TopicDO> topicStatus =
        //    mongoTemplate.find(Query.query(QueryHelper.criteria().and(TOPIC_STATUS).is(1)), TopicDO.class);
        //for (TopicDO status : topicStatus) {
        //    cache.put(status.getId().toString(),
        //        new TopicContentVO(status.getId().toString(), "#" + status.getTopicName()));
        //}
        // 设置缓存过期时间，每次当前时间戳是否大于过期时间
        this.expireTime = System.currentTimeMillis() + 2 * 60 * 60 * 1000L;
    }

    public Map<String, Object> getMemoryCache() {
        long current = System.currentTimeMillis();
        // 緩存過期，通知其它節點更新緩存，这里直接返回查库数据
        if (current > this.expireTime) {
            log.debug("缓存击穿");
            Map<String, Object> map = new HashMap<>();
            List<Object> topicStatus =
                    mongoTemplate.find(Query.query(Criteria.where("_id").is("")), Object.class);
            //for (TopicDO status : topicStatus) {
            //    map.put(status.getId().toString(),
            //        new TopicContentVO(status.getId().toString(), "#" + status.getTopicName()));
            //}
            clear();
            return map;
        }
        return cache;
    }

    /**
     * 异步更新其它节点缓存
     */
    public void clear() {
        // 异步更新其它节点缓存
        //mqSender.sendAsync(topic, "topicCacheUpdate", "更新 topic 緩存", new SendCallback() {
        //    @Override
        //    public void onSuccess(SendResult sendResult) {
        //
        //    }
        //
        //    @Override
        //    public void onException(Throwable throwable) {
        //
        //    }
        //});
    }

    /**
     * 异步更新缓存实现类
     */
    @Component
    //@RocketMQMessageListener(consumerGroup = "topicCacheUpdate", topic = "${spring.application.name}",
    //        messageModel = MessageModel.BROADCASTING)
    public static class ClearCache /*implements RocketMQListener<String>*/ {

        @Resource
        private TopicCache topicCache;

        //@Override
        public void onMessage(String message) {
            //    log.info(message);
            synchronized (topicCache.lockObject) {
                if (System.currentTimeMillis() > topicCache.expireTime) {
                    //List<TopicDO> topicStatus = topicCache.mongoTemplate
                    //        .find(Query.query(QueryHelper.criteria().and("topicStatus").is(1)), TopicDO.class);
                    //topicCache.cache.clear();
                    //for (TopicDO status : topicStatus) {
                    //    topicCache.cache.put(status.getId().toString(),
                    //            new TopicContentVO(status.getId().toString(), "#" + status.getTopicName()));
                    //}
                    topicCache.expireTime = System.currentTimeMillis() + 2 * 60 * 60 * 1000L;
                }
            }
        }
    }
}
