package com.VotingApp.VotingApp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
public class Voter {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column(name = "voterId")
    private Long voterId;

    @NotBlank(message="Voter name cannot be blank")
    private String voterName;

    @NotNull(message="Email cannot be null")
    @Email(message="Email should be valid")
    private String voterEmail;

    private Boolean hasVoted=false;

    @OneToOne(mappedBy = "voter",cascade = CascadeType.ALL)
    @JsonIgnore
    private Vote vote;

}
