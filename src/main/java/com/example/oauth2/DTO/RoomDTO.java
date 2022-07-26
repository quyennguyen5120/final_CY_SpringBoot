package com.example.oauth2.DTO;

import com.example.oauth2.Entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private String name;
    private String code;
    private Double price;
    private boolean isAvailabel;
    public RoomDTO(RoomEntity room, boolean isAvailabel){
        this.id = room.getId();
        this.name = room.getName();
        this.code = room.getCode();
        this.price = room.getPrice();
        this.isAvailabel = isAvailabel;
    }
}
