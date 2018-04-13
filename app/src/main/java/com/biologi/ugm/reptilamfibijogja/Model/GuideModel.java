package com.biologi.ugm.reptilamfibijogja.Model;

/**
 * Created by Fauziw97 on 09/12/17.
 */

public class GuideModel {
    private int guideImage;
    private String guideText;

    public GuideModel(int guideImage, String guideText) {
        this.guideImage = guideImage;
        this.guideText = guideText;
    }

    public int getGuideImage() {
        return guideImage;
    }

    public void setGuideImage(int guideImage) {
        this.guideImage = guideImage;
    }

    public String getGuideText() {
        return guideText;
    }

    public void setGuideText(String guideText) {
        this.guideText = guideText;
    }
}
