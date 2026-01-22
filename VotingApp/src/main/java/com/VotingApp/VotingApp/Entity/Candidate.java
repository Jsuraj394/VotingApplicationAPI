package com.VotingApp.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateId;
    @Size(min=2, message="Candidate name must have at least 2 characters")
    @NotBlank(message="Candidate name cannot be blank")
    private String candidateName;

    @NotBlank(message="Party name cannot be blank")
    private String party;
    @JsonIgnore
    private int voteCount=0;

    @OneToMany( mappedBy = "candidate",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vote> vote;
}
