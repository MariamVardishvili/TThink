package com.example.demo.repositories;

import com.example.demo.models.PlayedImages;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerCardsRepository extends JpaRepository<PlayedImages, Integer> {
    List<PlayedImages> findAllByPlayerIdAndActiveIsTrue(long playerId, Pageable pageable);
    List<PlayedImages> findAllByPlayerIdAndActiveIsTrue(long playerId);
}
