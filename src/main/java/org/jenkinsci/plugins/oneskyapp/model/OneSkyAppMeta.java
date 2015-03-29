package org.jenkinsci.plugins.oneskyapp.model;

import com.google.gson.annotations.SerializedName;

public class OneSkyAppMeta {
    private int status;
    @SerializedName("record_count")
    private int recordCount;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
}
