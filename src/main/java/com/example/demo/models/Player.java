package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.awt.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String Name;
    Boolean active;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    GameRoom room;
    public Player() { active=true;}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public GameRoom getRoom() {
        return room;
    }

    public void setRoom(GameRoom room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", active=" + active +
                ", room=" + room +
                '}';
    }
}
