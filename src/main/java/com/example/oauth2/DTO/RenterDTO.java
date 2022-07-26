package com.example.oauth2.DTO;

import com.example.oauth2.Entity.RenterEntity;
import com.example.oauth2.Entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RenterDTO {
    private String timeStart;
    private String timeEnd;
    private String nameCustomer;
    private Integer ageCustomer;
    private String addressCustomer;
    private Long roomId;
    private String nameRoom;
    private String codeRoom;
    private Double priceRoom;

    public RenterDTO(RoomEntity room){
        this.roomId = room.getId();
        this.nameRoom = room.getName();
        this.codeRoom = room.getCode();
        this.priceRoom = room.getPrice();
    }

    public RenterDTO(RenterEntity renter, RoomEntity room){
        this.roomId = room.getId();
        this.nameRoom = room.getName();
        this.codeRoom = room.getCode();
        this.priceRoom = room.getPrice();
        this.timeEnd = renter.getTimeEnd().toString();
        this.timeStart = renter.getTimeStart().toString();
        this.nameCustomer = renter.getNameCustomer();
        this.ageCustomer = renter.getAgeCustomer();
        this.addressCustomer = renter.getAddressCustomer();
    }
}
