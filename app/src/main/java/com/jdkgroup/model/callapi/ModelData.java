package com.jdkgroup.model.callapi;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("store_logo")
    @Expose
    private String storeLogo;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("outlet_id")
    @Expose
    private Object outletId;
    @SerializedName("deal_detail_images")
    @Expose
    private List<String> dealDetailImages = null;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("store_locality")
    @Expose
    private String storeLocality;
    @SerializedName("is_following_store")
    @Expose
    private Boolean isFollowingStore;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("deal_start_date")
    @Expose
    private String dealStartDate;
    @SerializedName("liked_by_user")
    @Expose
    private Boolean likedByUser;
    @SerializedName("unused_coupon_present")
    @Expose
    private Boolean unusedCouponPresent;
    @SerializedName("is_expired")
    @Expose
    private Boolean isExpired;
    @SerializedName("is_club_member")
    @Expose
    private Boolean isClubMember;
    @SerializedName("price_flag")
    @Expose
    private Boolean priceFlag;
    @SerializedName("actual_price")
    @Expose
    private String actualPrice;
    @SerializedName("discounted_price")
    @Expose
    private String discountedPrice;
    @SerializedName("share_text")
    @Expose
    private String shareText;
    @SerializedName("deal_type")
    @Expose
    private Integer dealType;
    @SerializedName("approx_date_present")
    @Expose
    private Boolean approxDatePresent;
    @SerializedName("total_bought")
    @Expose
    private Integer totalBought;
    @SerializedName("total_left")
    @Expose
    private Integer totalLeft;
    @SerializedName("buyable_quantity_per_transaction")
    @Expose
    private Integer buyableQuantityPerTransaction;
    @SerializedName("internet_charges")
    @Expose
    private Double internetCharges;
    @SerializedName("valid_days")
    @Expose
    private List<String> validDays = null;
    @SerializedName("deal_category_id")
    @Expose
    private Integer dealCategoryId;
    @SerializedName("timings_flag")
    @Expose
    private Boolean timingsFlag;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("valid_for_people")
    @Expose
    private Object validForPeople;
    @SerializedName("appointment_text")
    @Expose
    private Object appointmentText;
    @SerializedName("points_restricted")
    @Expose
    private Boolean pointsRestricted;
    @SerializedName("cashback_flag")
    @Expose
    private Boolean cashbackFlag;
    @SerializedName("details")
    @Expose
    private ModelDetails details;
    @SerializedName("contact")
    @Expose
    private ModelContact contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Object getOutletId() {
        return outletId;
    }

    public void setOutletId(Object outletId) {
        this.outletId = outletId;
    }

    public List<String> getDealDetailImages() {
        return dealDetailImages;
    }

    public void setDealDetailImages(List<String> dealDetailImages) {
        this.dealDetailImages = dealDetailImages;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLocality() {
        return storeLocality;
    }

    public void setStoreLocality(String storeLocality) {
        this.storeLocality = storeLocality;
    }

    public Boolean getIsFollowingStore() {
        return isFollowingStore;
    }

    public void setIsFollowingStore(Boolean isFollowingStore) {
        this.isFollowingStore = isFollowingStore;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDealStartDate() {
        return dealStartDate;
    }

    public void setDealStartDate(String dealStartDate) {
        this.dealStartDate = dealStartDate;
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public Boolean getUnusedCouponPresent() {
        return unusedCouponPresent;
    }

    public void setUnusedCouponPresent(Boolean unusedCouponPresent) {
        this.unusedCouponPresent = unusedCouponPresent;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Boolean getIsClubMember() {
        return isClubMember;
    }

    public void setIsClubMember(Boolean isClubMember) {
        this.isClubMember = isClubMember;
    }

    public Boolean getPriceFlag() {
        return priceFlag;
    }

    public void setPriceFlag(Boolean priceFlag) {
        this.priceFlag = priceFlag;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public Integer getDealType() {
        return dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    public Boolean getApproxDatePresent() {
        return approxDatePresent;
    }

    public void setApproxDatePresent(Boolean approxDatePresent) {
        this.approxDatePresent = approxDatePresent;
    }

    public Integer getTotalBought() {
        return totalBought;
    }

    public void setTotalBought(Integer totalBought) {
        this.totalBought = totalBought;
    }

    public Integer getTotalLeft() {
        return totalLeft;
    }

    public void setTotalLeft(Integer totalLeft) {
        this.totalLeft = totalLeft;
    }

    public Integer getBuyableQuantityPerTransaction() {
        return buyableQuantityPerTransaction;
    }

    public void setBuyableQuantityPerTransaction(Integer buyableQuantityPerTransaction) {
        this.buyableQuantityPerTransaction = buyableQuantityPerTransaction;
    }

    public Double getInternetCharges() {
        return internetCharges;
    }

    public void setInternetCharges(Double internetCharges) {
        this.internetCharges = internetCharges;
    }

    public List<String> getValidDays() {
        return validDays;
    }

    public void setValidDays(List<String> validDays) {
        this.validDays = validDays;
    }

    public Integer getDealCategoryId() {
        return dealCategoryId;
    }

    public void setDealCategoryId(Integer dealCategoryId) {
        this.dealCategoryId = dealCategoryId;
    }

    public Boolean getTimingsFlag() {
        return timingsFlag;
    }

    public void setTimingsFlag(Boolean timingsFlag) {
        this.timingsFlag = timingsFlag;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Object getValidForPeople() {
        return validForPeople;
    }

    public void setValidForPeople(Object validForPeople) {
        this.validForPeople = validForPeople;
    }

    public Object getAppointmentText() {
        return appointmentText;
    }

    public void setAppointmentText(Object appointmentText) {
        this.appointmentText = appointmentText;
    }

    public Boolean getPointsRestricted() {
        return pointsRestricted;
    }

    public void setPointsRestricted(Boolean pointsRestricted) {
        this.pointsRestricted = pointsRestricted;
    }

    public Boolean getCashbackFlag() {
        return cashbackFlag;
    }

    public void setCashbackFlag(Boolean cashbackFlag) {
        this.cashbackFlag = cashbackFlag;
    }

    public ModelDetails getDetails() {
        return details;
    }

    public void setDetails(ModelDetails details) {
        this.details = details;
    }

    public ModelContact getContact() {
        return contact;
    }

    public void setContact(ModelContact contact) {
        this.contact = contact;
    }

}
