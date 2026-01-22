package com.VotingApp.VotingApp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {
    private String message;
    private Long voteId;
    private Long candidateId;
    private Boolean success;

}
