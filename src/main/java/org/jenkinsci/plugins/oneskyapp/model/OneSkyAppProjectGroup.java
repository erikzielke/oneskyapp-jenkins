package org.jenkinsci.plugins.oneskyapp.model;

import org.jenkinsci.plugins.oneskyapp.OneSkyAppClient;


public class OneSkyAppProjectGroup  {
    private int id;
    private String name;
    private OneSkyAppClient oneSkyAppClient;

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



    @Override
    public String toString() {
        return "OneSkyAppProjectGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
