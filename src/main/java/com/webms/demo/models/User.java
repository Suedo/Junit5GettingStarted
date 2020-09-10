package com.webms.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "UsersTable")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should be at least 2 words")
    private String Name;

    @Past(message = "Birthdate must be a Past date")
    private LocalDate birthDate;
    
    // previously we needed a default NoArgsConstructor for SpringBoot to work properly, now not needed
    // but still, NoArgsConstructor is needed for JPA
    public User(String name, Integer id, LocalDate birthDate) {
        Name = name;
        this.id = id;
        this.birthDate = birthDate;
    }
}
