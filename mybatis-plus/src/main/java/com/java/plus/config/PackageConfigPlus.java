package com.java.plus.config;

import com.baomidou.mybatisplus.generator.config.PackageConfig;
import lombok.Data;

/**
 * @author xuweizhi
 * @since 2019-08-01
 */
@Data
public class PackageConfigPlus extends PackageConfig {

    /**
     * VO 包名
     */
    private String vos = "vos";

    /**
     * DTO 包名
     */
    private String dtos = "dtos";

    /**
     * DO 包名
     */
    private String dos = "dos";


}
