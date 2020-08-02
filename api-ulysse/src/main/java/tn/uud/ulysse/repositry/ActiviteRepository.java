package tn.uud.ulysse.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.uud.ulysse.models.Activite;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
	public Activite getActiviteById( Long id );
}
