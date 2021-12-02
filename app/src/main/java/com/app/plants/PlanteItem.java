package com.app.plants;

public class PlanteItem {

    private String mPlanteName;
    private int mPlantImage;

    public PlanteItem(String plantName, int plantImage) {
        mPlanteName = plantName;
        mPlantImage = plantImage;
    }

    public String getPlanteName() {
        return mPlanteName;
    }

    public int getPlantImage() {
        return mPlantImage;
    }
}