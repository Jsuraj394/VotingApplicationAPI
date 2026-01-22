package com.VotingApp.VotingApp.VoteController;

import com.VotingApp.VotingApp.DTO.ElectionResultRequestDTO;
import com.VotingApp.VotingApp.DTO.ElectionResultResponseDTO;
import com.VotingApp.VotingApp.Entity.ElectionResult;
import com.VotingApp.VotingApp.Service.ElectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/electionResults/")
@CrossOrigin(origins = "http://localhost:8080")
public class ElectionResultController {
    private ElectionResultService electionResultService;

    @Autowired
    public ElectionResultController(ElectionResultService electionResultService) {
        this.electionResultService = electionResultService;
    }

    @GetMapping("getAllElectionResults")
    public ResponseEntity<List<ElectionResultResponseDTO>> getAllElectionResults() {
        List<ElectionResultResponseDTO> electionResultResponseDTO = new ArrayList<>();
        List<ElectionResult> electionResults = electionResultService.getAllElectionResults();

        for(ElectionResult electionResult : electionResults) {
            ElectionResultResponseDTO electionResultlist = new ElectionResultResponseDTO();
            electionResultlist.setElectionName(electionResult.getElectionName());
            electionResultlist.setWinnerId(electionResult.getWinnerId());
            electionResultlist.setTotalWinnerVotes((long) electionResult.getWinner().getVoteCount());
            electionResultlist.setTotalVotes(electionResult.getTotalVotes());
            electionResultResponseDTO.add(electionResultlist);
        }
        return new ResponseEntity<>(electionResultResponseDTO, HttpStatus.FOUND);
    }

    @PostMapping("declareResult" )
    public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody ElectionResultRequestDTO electionResultRequestDTO) {
        ElectionResult electionResult = electionResultService.declareElectionResult(electionResultRequestDTO.getElectionName());
        ElectionResultResponseDTO electionResultResponseDTO = new ElectionResultResponseDTO();
        electionResultResponseDTO.setElectionName(electionResult.getElectionName());
        electionResultResponseDTO.setWinnerId(electionResult.getWinnerId());
        electionResultResponseDTO.setTotalWinnerVotes((long) electionResult.getWinner().getVoteCount());
        electionResultResponseDTO.setTotalVotes(electionResult.getTotalVotes());
        return new ResponseEntity<>(electionResultResponseDTO, HttpStatus.CREATED);
    }
}
