package com.example.demo.service;

import com.example.demo.models.GameRoom;
import com.example.demo.models.Player;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomrepo;
    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerCardsService playerCardsService;

    int getRoomNumber(){
        int roomnumber;
         List<GameRoom> list = roomrepo.findAll();
         if(list.size()>0) {
             roomnumber = list.get(list.size() - 1).getRoomNumber() + 1;
         }
         else{
             roomnumber = 1;
         }
         return roomnumber;
    }

    public List<GameRoom> getActiveRooms(){
        return roomrepo.findAllByActiveIsTrue();
    }

    public Optional<GameRoom> getActiveRoom(int roomNumber){
        return roomrepo.findByRoomNumber(roomNumber);
    }
    public Optional<GameRoom> getActiveRoomsAndPlayer(int roomNumber){
        return roomrepo.findByRoomNumber(roomNumber);
    }

    public Player createNewRoom(String userName){
        GameRoom g = new GameRoom(getRoomNumber());
        roomrepo.save(g);
        Player player = playerService.connectPlayerAndRoom(userName, g.getRoomNumber(), true);
        return player;
    }

    public void startGame(GameRoom room) {
        room.setStarted(true);
        room.setRound(1);
        GameRoom gameroom = roomrepo.save(room);
        List<Player> players = playerService.getActivePlayersFromRoom(gameroom.getRoomNumber());
        playerService.setOrder(room);
        System.out.println("returned Players"+players);
        playerCardsService.genereatecardsForPlayers(players);
    }
}
