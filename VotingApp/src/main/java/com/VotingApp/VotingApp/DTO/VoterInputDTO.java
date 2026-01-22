package com.VotingApp.VotingApp.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoterInputDTO {
    @NotNull(message = "Voter ID cannot be null")
    private Long voterId;
    @NotNull(message = "Candidate ID cannot be null")
    private Long candidateId;
}
