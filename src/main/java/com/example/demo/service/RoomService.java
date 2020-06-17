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

    public Optional<GameRoom> getActiveRooms(int roomNumber){
        return roomrepo.findByRoomNumber(roomNumber);
    }
    public Optional<GameRoom> getActiveRoomsAndPlayer(int roomNumber){
        return roomrepo.findByRoomNumber(roomNumber);
    }

    public int createNewRoom(String userName){
        GameRoom g = new GameRoom(getRoomNumber());
        roomrepo.save(g);
        playerService.connectPlayerAndRoom(userName, g.getRoomNumber());
        return g.getRoomNumber();
    }
}
