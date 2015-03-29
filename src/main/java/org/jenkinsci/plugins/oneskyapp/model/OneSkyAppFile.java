package org.jenkinsci.plugins.oneskyapp.model;

import com.google.gson.annotations.SerializedName;

public class OneSkyAppFile {
    @SerializedName("file_name")
    public String fileName;
    @SerializedName("string_count")
    private int stringCount;
    @SerializedName("uploaded_at_timestamp")
    private long uploadedAtTimestamp;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getStringCount() {
        return stringCount;
    }

    public void setStringCount(int stringCount) {
        this.stringCount = stringCount;
    }

    public long getUploadedAtTimestamp() {
        return uploadedAtTimestamp;
    }

    public void setUploadedAtTimestamp(long uploadedAtTimestamp) {
        this.uploadedAtTimestamp = uploadedAtTimestamp;
    }

    @Override
    public String toString() {
        return "OneSkyAppFile{" +
                "fileName='" + fileName + '\'' +
                ", stringCount=" + stringCount +
                ", uploadedAtTimestamp=" + uploadedAtTimestamp +
                '}';
    }
}
