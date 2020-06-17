package com.example.demo.service;


import com.example.demo.models.GameRoom;
import com.example.demo.models.Player;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoomRepository roomRepo;

    public Player connectPlayerAndRoom(String name, int roomNumber){
        Optional<GameRoom> room = roomRepo.findByRoomNumber(roomNumber);
        try{
            Player pl = new Player();
            pl.setActive(true);
            pl.setRoom(room.get());

            pl.setName(name);
            playerRepo.save(pl);
            return pl;
        }catch(Exception e){
            System.out.println("FLSAELLASLSADSAD");
            return null;
        }
    }

    public List<Player> getActivePlayersFromRoom(int roomNumber) {
        List<Player> plList = playerRepo.findPlayerByRoomAndActiveIsTrue(roomRepo.findByRoomNumber(roomNumber).get());
        return plList;
    }

    public GameRoom getPlayerRoom(long playerId) {
        Optional<Player> player = playerRepo.findById(playerId);
        return player.get().getRoom();
    }
}
