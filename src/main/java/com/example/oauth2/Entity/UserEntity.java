package com.example.oauth2.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;
    private String nameStaff;

    public UserEntity(String username, String password, Set<RoleEntity> roles,String nameStaff) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.nameStaff = nameStaff;
    }
}
