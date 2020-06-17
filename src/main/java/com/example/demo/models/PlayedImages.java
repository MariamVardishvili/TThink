package com.example.demo.models;

import javax.persistence.*;


@Entity
public class PlayedImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    long playerId;
    int imageNumber;
    Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "PlayedImages{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", imageNumber=" + imageNumber +
                ", active=" + active +
                '}';
    }
}
