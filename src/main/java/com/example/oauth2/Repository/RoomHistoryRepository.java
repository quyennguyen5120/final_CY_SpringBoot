package com.example.oauth2.Repository;

import com.example.oauth2.Entity.RoomHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomHistoryRepository extends JpaRepository<RoomHistory, Long> {
    @Query("select SUM(r.totalPrice) from RoomHistory r")
    Double totalPrice();
}
