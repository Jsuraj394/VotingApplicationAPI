package com.VotingApp.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @OneToOne
    @JoinColumn(name = "VoterId",unique = true)
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "CandidateId")
    @JsonIgnore
    private Candidate candidate;

    @JsonProperty
    public Long getVoterId() {
        return voter!=null ? voter.getVoterId() : null;
    }
    @JsonProperty
    public Long getCandidateId() {
        return candidate!=null ? candidate.getCandidateId() : null;
    }
}
