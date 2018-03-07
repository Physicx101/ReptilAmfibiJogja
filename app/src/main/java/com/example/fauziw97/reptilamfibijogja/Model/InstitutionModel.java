package com.example.fauziw97.reptilamfibijogja.Model;

/**
 * Created by Fauziw97 on 15/12/17.
 */

public class InstitutionModel {
    private String instName;
    private String instImage;

    public InstitutionModel(String instName, String instImage) {
        this.instName = instName;
        this.instImage = instImage;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getInstImage() {
        return instImage;
    }

    public void setInstImage(String instImage) {
        this.instImage = instImage;
    }
}
