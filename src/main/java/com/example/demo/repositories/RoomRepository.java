package com.example.demo.repositories;
import com.example.demo.models.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<GameRoom, Long> {
    public Optional<GameRoom> findByRoomNumber(int roomNumber);
    public List<GameRoom> findAllByActiveIsTrue();
    public Optional<GameRoom> findByIdAndActiveIsTrue(Long id);

}
