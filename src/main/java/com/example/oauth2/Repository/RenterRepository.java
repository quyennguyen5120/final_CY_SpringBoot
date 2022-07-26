package com.example.oauth2.Repository;

import com.example.oauth2.Entity.RenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepository extends JpaRepository<RenterEntity, Long> {

    @Query("select r from RenterEntity r where r.room.id = ?1")
    RenterEntity getByRoomId(Long id);
}
