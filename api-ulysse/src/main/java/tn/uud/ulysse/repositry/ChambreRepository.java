package tn.uud.ulysse.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.uud.ulysse.models.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
	public Chambre getChambreById( Long id );
}
