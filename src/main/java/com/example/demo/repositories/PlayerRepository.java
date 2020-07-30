package com.example.demo.repositories;

import com.example.demo.models.GameRoom;
import com.example.demo.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    public List<Player> findPlayerByRoomAndActiveIsTrue(GameRoom room);
    public Player findFirstByRoomAndRound(GameRoom room, Integer round);
    public List<Player> findAllByRoomAndActiveIsTrueOrderByPlayerOrder(GameRoom room);
    public Player findPlayerByRoomAndPlayerOrderAndActiveIsTrue(GameRoom room, int currentPlayerNumber);
}
