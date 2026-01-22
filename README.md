make sure to trauncte the vote table before starting new election 

Query :
	truncate table vote;
	
and then set the vote count to 0 and has_voted as false to avoid the transaprancy while voting.

Query : 

	update candidate set vote_count= 0;
	
	update voter set has_voted = false;
	
 
as we are setting the application to support the 1 election at a time we need to truncate the election_result table everytime we are hitting the declare result API.

Query : 

	truncate table election_result;
