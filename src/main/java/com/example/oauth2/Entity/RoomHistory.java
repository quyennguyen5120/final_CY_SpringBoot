package com.example.oauth2.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "room_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date timeStart;
    private Date timeEnd;
    private Date timeCheckout;
    private String nameCustomer;
    private Integer ageCustomer;
    private String addressCustomer;
    private Double totalPrice;
    private String RoomCode;
    @ManyToOne(cascade = CascadeType.PERSIST)
    UserEntity user;
}
