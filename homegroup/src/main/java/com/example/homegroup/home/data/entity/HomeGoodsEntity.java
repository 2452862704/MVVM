package com.example.homegroup.home.data.entity;


import com.example.networkmoudle.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class HomeGoodsEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * category_id : 0
         * goodsSku : [{"goods_id":0,"goods_sku_content":"string","goods_sku_title":"string","id":0,"sku_content":["string"],"sku_title":"string"}]
         * goods_banner : string
         * goods_code : string
         * goods_default_icon : string
         * goods_default_price : string
         * goods_default_sku : string
         * goods_desc : string
         * goods_detail_one : string
         * goods_detail_two : string
         * goods_sales_count : 0
         * goods_stock_count : 0
         * id : 0
         * maxPage : 0
         */

        private int category_id;
        private String goods_banner;
        private String goods_code;
        private String goods_default_icon;
        private String goods_default_price;
        private String goods_default_sku;
        private String goods_desc;
        private String goods_detail_one;
        private String goods_detail_two;
        private int goods_sales_count;
        private int goods_stock_count;
        private int id;
        private int maxPage;
        private List<GoodsSkuBean> goodsSku;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getGoods_banner() {
            return goods_banner;
        }

        public void setGoods_banner(String goods_banner) {
            this.goods_banner = goods_banner;
        }

        public String getGoods_code() {
            return goods_code;
        }

        public void setGoods_code(String goods_code) {
            this.goods_code = goods_code;
        }

        public String getGoods_default_icon() {
            return goods_default_icon;
        }

        public void setGoods_default_icon(String goods_default_icon) {
            this.goods_default_icon = goods_default_icon;
        }

        public String getGoods_default_price() {
            return goods_default_price;
        }

        public void setGoods_default_price(String goods_default_price) {
            this.goods_default_price = goods_default_price;
        }

        public String getGoods_default_sku() {
            return goods_default_sku;
        }

        public void setGoods_default_sku(String goods_default_sku) {
            this.goods_default_sku = goods_default_sku;
        }

        public String getGoods_desc() {
            return goods_desc;
        }

        public void setGoods_desc(String goods_desc) {
            this.goods_desc = goods_desc;
        }

        public String getGoods_detail_one() {
            return goods_detail_one;
        }

        public void setGoods_detail_one(String goods_detail_one) {
            this.goods_detail_one = goods_detail_one;
        }

        public String getGoods_detail_two() {
            return goods_detail_two;
        }

        public void setGoods_detail_two(String goods_detail_two) {
            this.goods_detail_two = goods_detail_two;
        }

        public int getGoods_sales_count() {
            return goods_sales_count;
        }

        public void setGoods_sales_count(int goods_sales_count) {
            this.goods_sales_count = goods_sales_count;
        }

        public int getGoods_stock_count() {
            return goods_stock_count;
        }

        public void setGoods_stock_count(int goods_stock_count) {
            this.goods_stock_count = goods_stock_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMaxPage() {
            return maxPage;
        }

        public void setMaxPage(int maxPage) {
            this.maxPage = maxPage;
        }

        public List<GoodsSkuBean> getGoodsSku() {
            return goodsSku;
        }

        public void setGoodsSku(List<GoodsSkuBean> goodsSku) {
            this.goodsSku = goodsSku;
        }

        public static class GoodsSkuBean {
            /**
             * goods_id : 0
             * goods_sku_content : string
             * goods_sku_title : string
             * id : 0
             * sku_content : ["string"]
             * sku_title : string
             */

            private int goods_id;
            private String goods_sku_content;
            private String goods_sku_title;
            private int id;
            private String sku_title;
            private List<String> sku_content;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_sku_content() {
                return goods_sku_content;
            }

            public void setGoods_sku_content(String goods_sku_content) {
                this.goods_sku_content = goods_sku_content;
            }

            public String getGoods_sku_title() {
                return goods_sku_title;
            }

            public void setGoods_sku_title(String goods_sku_title) {
                this.goods_sku_title = goods_sku_title;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSku_title() {
                return sku_title;
            }

            public void setSku_title(String sku_title) {
                this.sku_title = sku_title;
            }

            public List<String> getSku_content() {
                return sku_content;
            }

            public void setSku_content(List<String> sku_content) {
                this.sku_content = sku_content;
            }
        }
    }
}
