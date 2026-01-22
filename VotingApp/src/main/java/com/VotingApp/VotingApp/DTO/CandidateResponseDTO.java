package com.VotingApp.VotingApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDTO {

    private Long candidateId;
    @NotBlank(message="Candidate name cannot be blank")
    private String candidateName;
    @NotBlank(message="Party name cannot be blank")
    private String party;
}
