package com.lcwd.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {
    @Id
    @Column(name = "ID")
    //id will be generated automatically from controller
    private String userId;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(length = 2000)
    private String about;
    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
