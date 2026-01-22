package com.VotingApp.VotingApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequestDTO {
    @Size(min=2, message="Candidate name must have at least 2 characters")
    @NotBlank(message="Candidate name cannot be blank")
    private String candidateName;

    @NotBlank(message="Party name cannot be blank")
    private String party;
}
