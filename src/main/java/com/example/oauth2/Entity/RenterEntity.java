package com.example.oauth2.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "renter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date timeStart;
    private Date timeEnd;
    private String nameCustomer;
    private Integer ageCustomer;
    private String addressCustomer;
    @ManyToOne(cascade = CascadeType.PERSIST)
    RoomEntity room;

}
