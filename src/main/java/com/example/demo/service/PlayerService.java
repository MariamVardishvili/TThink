package com.example.demo.service;


import com.example.demo.models.GameRoom;
import com.example.demo.models.Player;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoomRepository roomRepo;

    public Player connectPlayerAndRoom(String name, int roomNumber, Boolean owner){
        Optional<GameRoom> room = roomRepo.findByRoomNumber(roomNumber);
        try{
            Player pl = new Player();
            pl.setActive(true);
            pl.setRoom(room.get());
            pl.setRoomOwner(owner);
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



    public void setOrder(GameRoom room) {
        List<Player> players = getActivePlayersFromRoom(room.getRoomNumber());
        Collections.shuffle(players);
        int i = 1;
        for (Player player: players) {
            player.setPlayerOrder(i);
            i++;
            playerRepo.save(player);
        }
    }

    public List<Player> getPlayersInOrder(GameRoom room) {
       return playerRepo.findAllByRoomAndActiveIsTrueOrderByPlayerOrder(room);
    }

    public Player getPlayerForRound(GameRoom room) {
        List<Player> players = getActivePlayersFromRoom(room.getRoomNumber());
        int numberofplayers = players.size();
        int rn = room.getRound() % numberofplayers +1;
        return players.get(rn % numberofplayers);
    }

    public Player getCurrentPlayer(GameRoom room) {
        int round = room.getRound();
        List<Player> players = getActivePlayersFromRoom(room.getRoomNumber());
        int numberofPlayers =players.size();
        int currentPlayerNumber =round - (round/numberofPlayers *  numberofPlayers)+1;
        Player pl =  playerRepo.findPlayerByRoomAndPlayerOrderAndActiveIsTrue(room, currentPlayerNumber);
        System.out.println("numberofPlayers " + numberofPlayers + " currentPlayerNumber " + currentPlayerNumber + " Object: " + pl);
        return pl;
    }
}
