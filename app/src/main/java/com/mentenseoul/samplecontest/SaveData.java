package com.mentenseoul.samplecontest;

public class SaveData {
    private String modelName;
    private String name;
    private String rank;
    private String time;

    public SaveData(String modelName, String name, String rank, String time) {
        this.modelName = modelName;
        this.name = name;
        this.rank = rank;
        this.time = time;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
