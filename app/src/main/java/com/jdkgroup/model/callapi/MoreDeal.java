package com.jdkgroup.model.callapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoreDeal {

    @SerializedName("deal_id")
    @Expose
    private Integer dealId;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("main_line")
    @Expose
    private String mainLine;
    @SerializedName("deal_type")
    @Expose
    private Integer dealType;
    @SerializedName("liked_by_user")
    @Expose
    private Boolean likedByUser;
    @SerializedName("total_bought")
    @Expose
    private Integer totalBought;
    @SerializedName("price_flag")
    @Expose
    private Boolean priceFlag;
    @SerializedName("actual_price")
    @Expose
    private Object actualPrice;
    @SerializedName("discounted_price")
    @Expose
    private Object discountedPrice;
    @SerializedName("deal_photo")
    @Expose
    private String dealPhoto;
    @SerializedName("cashback_flag")
    @Expose
    private Boolean cashbackFlag;
    @SerializedName("steps")
    @Expose
    private Integer steps;

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainLine() {
        return mainLine;
    }

    public void setMainLine(String mainLine) {
        this.mainLine = mainLine;
    }

    public Integer getDealType() {
        return dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public Integer getTotalBought() {
        return totalBought;
    }

    public void setTotalBought(Integer totalBought) {
        this.totalBought = totalBought;
    }

    public Boolean getPriceFlag() {
        return priceFlag;
    }

    public void setPriceFlag(Boolean priceFlag) {
        this.priceFlag = priceFlag;
    }

    public Object getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Object actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Object getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Object discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getDealPhoto() {
        return dealPhoto;
    }

    public void setDealPhoto(String dealPhoto) {
        this.dealPhoto = dealPhoto;
    }

    public Boolean getCashbackFlag() {
        return cashbackFlag;
    }

    public void setCashbackFlag(Boolean cashbackFlag) {
        this.cashbackFlag = cashbackFlag;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

}