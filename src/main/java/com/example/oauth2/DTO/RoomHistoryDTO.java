package com.example.oauth2.DTO;

import com.example.oauth2.Entity.RoomHistory;
import com.example.oauth2.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomHistoryDTO {
    private Long id;
    private Date timeStart;
    private Date timeEnd;
    private Date timeCheckout;
    private String nameCustomer;
    private Integer ageCustomer;
    private String addressCustomer;
    private Long totalPrice;
    private String roomCode;
    private long dayThue;

    public RoomHistoryDTO(RoomHistory roomHistory){
        this.id = roomHistory.getId();
        this.timeCheckout = roomHistory.getTimeCheckout();
        this.timeEnd = roomHistory.getTimeEnd();
        this.timeStart = roomHistory.getTimeStart();
        this.nameCustomer = roomHistory.getNameCustomer();
        this.ageCustomer = roomHistory.getAgeCustomer();
        this.addressCustomer = roomHistory.getAddressCustomer();
        this.totalPrice = roomHistory.getTotalPrice().longValue();
        this.roomCode = roomHistory.getRoomCode();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate end = roomHistory.getTimeEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate start = roomHistory.getTimeStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.dayThue =  ChronoUnit.DAYS.between(start,end);




    }
}
