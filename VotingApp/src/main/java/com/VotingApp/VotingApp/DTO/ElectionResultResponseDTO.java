package com.VotingApp.VotingApp.DTO;

import lombok.Data;

@Data
public class ElectionResultResponseDTO {
    private String electionName;
    private Long totalVotes;
    private Long winnerId;
    private Long totalWinnerVotes;
}
