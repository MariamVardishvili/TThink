package com.example.demo.repositories;

import com.example.demo.models.GameRoom;
import com.example.demo.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    public List<Player> findPlayerByRoomAndActiveIsTrue(GameRoom room);

}
