package com.jdkgroup.model.callapi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDetails {

    @SerializedName("main_line")
    @Expose
    private String mainLine;
    @SerializedName("have_other_outlets")
    @Expose
    private Boolean haveOtherOutlets;
    @SerializedName("avg_rating")
    @Expose
    private Object avgRating;
    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;
    @SerializedName("review_count")
    @Expose
    private Integer reviewCount;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("features")
    @Expose
    private List<String> features = null;
    @SerializedName("termsandconditions")
    @Expose
    private List<String> termsandconditions = null;
    @SerializedName("have_more_deals")
    @Expose
    private Boolean haveMoreDeals;
    @SerializedName("more_deals")
    @Expose
    private List<MoreDeal> moreDeals = null;

    public String getMainLine() {
        return mainLine;
    }

    public void setMainLine(String mainLine) {
        this.mainLine = mainLine;
    }

    public Boolean getHaveOtherOutlets() {
        return haveOtherOutlets;
    }

    public void setHaveOtherOutlets(Boolean haveOtherOutlets) {
        this.haveOtherOutlets = haveOtherOutlets;
    }

    public Object getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Object avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<String> getTermsandconditions() {
        return termsandconditions;
    }

    public void setTermsandconditions(List<String> termsandconditions) {
        this.termsandconditions = termsandconditions;
    }

    public Boolean getHaveMoreDeals() {
        return haveMoreDeals;
    }

    public void setHaveMoreDeals(Boolean haveMoreDeals) {
        this.haveMoreDeals = haveMoreDeals;
    }

    public List<MoreDeal> getMoreDeals() {
        return moreDeals;
    }

    public void setMoreDeals(List<MoreDeal> moreDeals) {
        this.moreDeals = moreDeals;
    }

}