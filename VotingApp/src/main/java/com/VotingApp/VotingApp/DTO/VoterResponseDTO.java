package com.VotingApp.VotingApp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoterResponseDTO {
    private Long voterId;

    @NotBlank(message="Voter name cannot be blank")
    private String voterName;

    @NotNull(message="Email cannot be null")
    @Email(message="Email should be valid")
    private String voterEmail;

    private Boolean hasVoted=false;
}
