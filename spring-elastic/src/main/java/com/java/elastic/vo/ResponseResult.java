package com.java.elastic.vo;


import com.java.elastic.entity.EsProduct;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author xuweizhi
 */
@Data
public class ResponseResult {

    /**
     * 查询到的所有商品信息
     */
    private List<EsProduct> products;

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页码
     */
    private Integer totalPages;

    private List<Integer> pageNavs;

    /**
     * 当前查询到的结果，所有涉及到的品牌
     */
    private List<BrandVo> brands;

    /**
     * 当前查询到的结果，所有涉及到的所有属性
     */
    private List<AttrVo> attrs;

    /**
     * 当前查询到的结果，所有涉及到的所有分类
     */
    private List<categoryVo> categorys;

    //===========================以上是返回给页面的所有信息============================//

    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;

        public String getNavName() {
            return navName;
        }

        public void setNavName(String navName) {
            this.navName = navName;
        }

        public String getNavValue() {
            return navValue;
        }

        public void setNavValue(String navValue) {
            this.navValue = navValue;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }


    public static class BrandVo {

        private Long brandId;

        private String brandName;

        private String brandImg;

        public Long getBrandId() {
            return brandId;
        }

        public void setBrandId(Long brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandImg() {
            return brandImg;
        }

        public void setBrandImg(String brandImg) {
            this.brandImg = brandImg;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            BrandVo brandVo = (BrandVo) o;
            return Objects.equals(brandId, brandVo.brandId) &&
                    Objects.equals(brandName, brandVo.brandName) &&
                    Objects.equals(brandImg, brandVo.brandImg);
        }

        @Override
        public int hashCode() {
            return Objects.hash(brandId, brandName, brandImg);
        }
    }


    public static class AttrVo {

        private Long attrId;

        private String attrName;

        private List<String> attrValue;

        public Long getAttrId() {
            return attrId;
        }

        public void setAttrId(Long attrId) {
            this.attrId = attrId;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public List<String> getAttrValue() {
            return attrValue;
        }

        public void setAttrValue(List<String> attrValue) {
            this.attrValue = attrValue;
        }
    }


    public static class categoryVo {

        private Long categoryId;

        private String categoryName;

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }
}
