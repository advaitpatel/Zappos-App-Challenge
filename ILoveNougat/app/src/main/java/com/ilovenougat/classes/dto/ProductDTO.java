package com.ilovenougat.classes.dto;

/**
 * Created by Advait on 9/11/2016.
 */
public class ProductDTO {
    private String brandName;
    private String thumbnailImageUrl;
    private String productId;
    private String originalPrice;
    private String styleId;
    private String colorId;
    private String price;
    private String percentOff;
    private String productUrl;
    private String productName;

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }
    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }
    public void setColorId(String colorId) {
        this.colorId = colorId;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return this.brandName;
    }
    public String getThumbnailImageUrl() {
        return this.thumbnailImageUrl;
    }
    public String getProductId() {
        return this.productId;
    }
    public String getOriginalPrice() {
        return this.originalPrice;
    }
    public String getStyleId() {
        return this.styleId;
    }
    public String getColorId() {
        return this.colorId;
    }
    public String getPrice() {
        return this.price;
    }
    public String getPercentOff() {
        return this.percentOff;
    }
    public String getProductUrl() {
        return this.productUrl;
    }
    public String getProductName() {
        return this.productName;
    }


}
