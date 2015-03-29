package org.jenkinsci.plugins.oneskyapp.model;

import com.google.gson.annotations.SerializedName;

public class OneSkyAppProjectLanguage {
    String code;
    @SerializedName("english_name")
    String englishName;
    @SerializedName("local_name")
    String localName;
    String locale;
    String region;
    @SerializedName("is_base_language")
    boolean isBaseLanguage;
    @SerializedName("is_ready_to_publish")
    boolean isReadyToPublish;
    @SerializedName("translation_progress")
    String translationProgress;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isBaseLanguage() {
        return isBaseLanguage;
    }

    public void setIsBaseLanguage(boolean isBaseLanguage) {
        this.isBaseLanguage = isBaseLanguage;
    }

    public boolean isReadyToPublish() {
        return isReadyToPublish;
    }

    public void setIsReadyToPublish(boolean isReadyToPublish) {
        this.isReadyToPublish = isReadyToPublish;
    }

    public String getTranslationProgress() {
        return translationProgress;
    }

    public void setTranslationProgress(String translationProgress) {
        this.translationProgress = translationProgress;
    }

    @Override
    public String toString() {
        return "OneSkyAppProjectLanguage{" +
                "code='" + code + '\'' +
                ", englishName='" + englishName + '\'' +
                ", localName='" + localName + '\'' +
                ", locale='" + locale + '\'' +
                ", region='" + region + '\'' +
                ", isBaseLanguage='" + isBaseLanguage + '\'' +
                ", isReadyToPublish='" + isReadyToPublish + '\'' +
                ", translationProgress='" + translationProgress + '\'' +
                '}';
    }
}
