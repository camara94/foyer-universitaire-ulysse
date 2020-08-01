package tn.uud.ulysse.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.uud.ulysse.models.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	public Utilisateur getUtilisateurById( Long id );
}
