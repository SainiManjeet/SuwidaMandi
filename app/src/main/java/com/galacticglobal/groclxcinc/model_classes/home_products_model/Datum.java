
package com.galacticglobal.groclxcinc.model_classes.home_products_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("home_screen_products")
    @Expose
    private List<HomeScreenProduct> homeScreenProducts = null;
    @SerializedName("home_banner")
    @Expose
    private List<HomeBanner> homeBanner = null;
    @SerializedName("home_banner2")
    @Expose
    private List<HomeBanner2> homeBanner2 = null;
    @SerializedName("home_banner3")
    @Expose
    private List<HomeBanner3> homeBanner3 = null;
    @SerializedName("Daily Essential Store")
    @Expose
    private List<DailyEssentialStore> dailyEssentialStore = null;
    @SerializedName("Personal Care")
    @Expose
    private List<PersonalCare> personalCare = null;
    @SerializedName("Home & Kitchen Store")
    @Expose
    private List<HomeKitchenStore> homeKitchenStore = null;
    @SerializedName("Beauty Store")
    @Expose
    private List<BeautyStore> beautyStore = null;

    public List<HomeScreenProduct> getHomeScreenProducts() {
        return homeScreenProducts;
    }

    public void setHomeScreenProducts(List<HomeScreenProduct> homeScreenProducts) {
        this.homeScreenProducts = homeScreenProducts;
    }

    public List<HomeBanner> getHomeBanner() {
        return homeBanner;
    }

    public void setHomeBanner(List<HomeBanner> homeBanner) {
        this.homeBanner = homeBanner;
    }

    public List<HomeBanner2> getHomeBanner2() {
        return homeBanner2;
    }

    public void setHomeBanner2(List<HomeBanner2> homeBanner2) {
        this.homeBanner2 = homeBanner2;
    }

    public List<HomeBanner3> getHomeBanner3() {
        return homeBanner3;
    }

    public void setHomeBanner3(List<HomeBanner3> homeBanner3) {
        this.homeBanner3 = homeBanner3;
    }

    public List<DailyEssentialStore> getDailyEssentialStore() {
        return dailyEssentialStore;
    }

    public void setDailyEssentialStore(List<DailyEssentialStore> dailyEssentialStore) {
        this.dailyEssentialStore = dailyEssentialStore;
    }

    public List<PersonalCare> getPersonalCare() {
        return personalCare;
    }

    public void setPersonalCare(List<PersonalCare> personalCare) {
        this.personalCare = personalCare;
    }

    public List<HomeKitchenStore> getHomeKitchenStore() {
        return homeKitchenStore;
    }

    public void setHomeKitchenStore(List<HomeKitchenStore> homeKitchenStore) {
        this.homeKitchenStore = homeKitchenStore;
    }

    public List<BeautyStore> getBeautyStore() {
        return beautyStore;
    }

    public void setBeautyStore(List<BeautyStore> beautyStore) {
        this.beautyStore = beautyStore;
    }

}
