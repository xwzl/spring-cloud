package com.spring.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author xuweizhi
 * @since 2020/05/21 19:29
 */
@Data
@Document("local")
@NoArgsConstructor
@AllArgsConstructor
public class LocalDO {

    @Id
    private ObjectId id;

    private String locationName;

    /**
     * Latitude and longitude
     * <p>
     * Latitude: 经度 -> y
     * <p>
     * longitude: 维度 -> x
     */
    @GeoSpatialIndexed(useGeneratedName = true, type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

}
