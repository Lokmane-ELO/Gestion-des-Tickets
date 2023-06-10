package com.example.gestion_de_ticket.services;

public class CloseInterventionDTO {
    private int duration;
    private String closeDate;


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }
}
