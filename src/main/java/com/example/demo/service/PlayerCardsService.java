package com.example.demo.service;


import com.example.demo.models.GameRoom;
import com.example.demo.models.Images;
import com.example.demo.models.PlayedImages;
import com.example.demo.models.Player;
import com.example.demo.repositories.PlayerCardsRepository;
import com.example.demo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerCardsService {
    @Autowired
    PlayerCardsRepository plcardRepo;
    @Autowired
    PlayerService playerService;
    @Autowired
    RoomService roomService;
    @Autowired
    ImageService imgServce;

    public List<PlayedImages> getOrCreateImagesForPlayer(long playerId){
             System.out.println("Searching For Images");
            List<PlayedImages> ls=null;
            ls = activeCardsByUser(playerId);
        System.out.println("Size of current Cards: "+ls.size());
            if(ls.size()==0){
                System.out.println("NO IMAGES FOUND");
                //images are not assigned yet to players, so assign it
                genereatecardsForPlayers(playerId);
                ls = activeCardsByUser(playerId);
            }
            return ls;
    }

    List<PlayedImages> activeCardsByUser(long playerId){
       return plcardRepo.findAllByPlayerIdAndActiveIsTrue(playerId);
    }


    void genereatecardsForPlayers(long playerId){
       GameRoom room = playerService.getPlayerRoom(playerId);
        System.out.println("Current room: "+room.toString());
        List<Player> roomPlayers = room.getPlayers();
        System.out.println("Current Players: "+roomPlayers.toString());

        List<Images> images = imgServce.getAllImages();

        System.out.println("All images : "+images.toString());

        Collections.shuffle(images);
        int numberOfImagesEachPlayer = images.size()/roomPlayers.size();

        for(int i = 0; i<roomPlayers.size(); i++){
            for(int j = i*numberOfImagesEachPlayer; j<i*numberOfImagesEachPlayer+numberOfImagesEachPlayer; j++){
                PlayedImages plim = new PlayedImages();
                plim.setActive(true);
                plim.setPlayerId(roomPlayers.get(i).getId());
                plim.setImageNumber(images.get(j).getNumber());
                System.out.println("---------Creating " + plim.toString());
                plcardRepo.save(plim);
            }
        }
    }
}
