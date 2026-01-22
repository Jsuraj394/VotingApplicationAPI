package com.VotingApp.VotingApp.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {

    @NotNull(message = "Election name cannot be null")
    private String electionName;

}
