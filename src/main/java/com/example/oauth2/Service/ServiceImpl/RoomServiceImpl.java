package com.example.oauth2.Service.ServiceImpl;

import com.example.oauth2.DTO.RoomDTO;
import com.example.oauth2.Entity.RenterEntity;
import com.example.oauth2.Entity.RoomEntity;
import com.example.oauth2.Repository.RenterRepository;
import com.example.oauth2.Repository.RoomRepository;
import com.example.oauth2.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RenterRepository renterRepository;

    @Override
    public List<RoomDTO> getAllRoom() {
        List<RoomEntity> rooms = roomRepository.findAll();
        List<RoomDTO> result = new ArrayList<>();
        for (RoomEntity item : rooms){
            RenterEntity renter = renterRepository.getByRoomId(item.getId());
            RoomDTO roomDTO = null;
            if(renter != null){
                roomDTO = new RoomDTO(item, true);
                result.add(roomDTO);
            }
            else{
                roomDTO = new RoomDTO(item, false);
                result.add(roomDTO);
            }
        }
        return result;
    }
}
