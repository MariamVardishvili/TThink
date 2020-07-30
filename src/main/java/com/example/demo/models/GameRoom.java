package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;



@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class GameRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    int roomNumber;
    Boolean active;
    Boolean started;
    int round;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy =  "room")
    List<Player> players;

    public GameRoom() {   }
    public GameRoom(int room){
        roomNumber = room;
        active = true;
        started = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "GameRoom{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", active=" + active +
                '}';
    }
}
