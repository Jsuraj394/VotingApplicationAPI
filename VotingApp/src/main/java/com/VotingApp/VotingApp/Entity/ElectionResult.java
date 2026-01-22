package com.VotingApp.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity()
public class ElectionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "Election name cannot be blank")
    private String electionName;

    @OneToOne
    @JoinColumn(name = "WinnerId")
    @JsonIgnore
    private Candidate winner;

    private Long totalVotes;

    @JsonProperty("WinnerId")
    public Long getWinnerId() {
        return winner != null ? winner.getCandidateId() : null;
    }
}
