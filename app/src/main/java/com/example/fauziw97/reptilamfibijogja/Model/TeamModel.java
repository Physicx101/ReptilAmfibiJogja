package com.example.fauziw97.reptilamfibijogja.Model;

/**
 * Created by Fauziw97 on 15/12/17.
 */

public class TeamModel {
    private String teamImage;
    private String teamRole;
    private String teamName;
    private String teamMail;
    private String teamFb;

    public TeamModel (String teamImage, String teamName,String teamRole, String teamMail, String teamFb) {
        this.teamImage = teamImage;
        this.teamName = teamName;
        this.teamRole = teamRole;
        this.teamMail = teamMail;
        this.teamFb = teamFb;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String teamImage) {
        this.teamImage = teamImage;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamMail() {
        return teamMail;
    }

    public void setTeamMail(String teamMail) {
        this.teamMail = teamMail;
    }

    public String getTeamFb() {
        return teamFb;
    }

    public void setTeamFb(String teamFb) {
        this.teamFb = teamFb;
    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }
}
