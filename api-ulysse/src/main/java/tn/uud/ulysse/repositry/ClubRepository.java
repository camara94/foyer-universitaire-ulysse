package tn.uud.ulysse.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.uud.ulysse.models.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
	public Club getClubById( Long id );
}
