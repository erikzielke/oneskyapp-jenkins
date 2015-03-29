package org.jenkinsci.plugins.oneskyapp.model;

import com.google.gson.annotations.SerializedName;

public class OneSkyAppProject {
    public int id;
    private String name;
    private String description;
    @SerializedName("project_type")
    private OneSkyAppProjectType oneSkyAppProjectType;
    @SerializedName("string_count")
    private String stringCount;
    @SerializedName("word_count")
    private String wordCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OneSkyAppProjectType getOneSkyAppProjectType() {
        return oneSkyAppProjectType;
    }

    public void setOneSkyAppProjectType(OneSkyAppProjectType oneSkyAppProjectType) {
        this.oneSkyAppProjectType = oneSkyAppProjectType;
    }

    public String getStringCount() {
        return stringCount;
    }

    public void setStringCount(String stringCount) {
        this.stringCount = stringCount;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    @Override
    public String toString() {
        return "OneSkyAppProject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", oneSkyAppProjectType=" + oneSkyAppProjectType +
                ", stringCount='" + stringCount + '\'' +
                ", wordCount='" + wordCount + '\'' +
                '}';
    }

    public static class OneSkyAppProjectType {
        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "OneSkyAppProjectType{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
