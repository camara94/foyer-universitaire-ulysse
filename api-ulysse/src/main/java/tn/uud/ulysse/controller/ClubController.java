package tn.uud.ulysse.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import tn.uud.ulysse.models.Club;
import tn.uud.ulysse.models.Utilisateur;
import tn.uud.ulysse.repositry.ClubRepository;
import tn.uud.ulysse.repositry.UtilisateurRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clubs")
public class ClubController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private ClubRepository clubRepository;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Club>> getClubs() {
		List<Club> clubs = this.clubRepository.findAll();
		
		if (clubs == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.created(null).body(clubs);
	}
	
	@PostMapping(value = "/")
	public Club createClub( @RequestBody Club club ) {
		return this.clubRepository.save(club);
	}
	
	@DeleteMapping (value = "/{id}")
    public void supprimerClub(@PathVariable Long id) {
    	
		Club club = this.clubRepository.getClubById(id);
		club.removeUtilisateurs();
		this.clubRepository.save(club);
    	this.clubRepository.delete(club);
    }
	
	
	 @GetMapping(value = "/{id}")
	    public ResponseEntity<Club> getClub(@PathVariable Long id) {
		 Club club = this.clubRepository.getClubById(id);
	    	 if (club == null)
		          return ResponseEntity.noContent().build();


	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(club.getId())
	                .toUri();
	        return ResponseEntity.created(location).body(club);
	    }
	 
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
	    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club club) {
		 Club club1 =  this.clubRepository.getClubById(id);
	        if (club1 == null)
	            return ResponseEntity.noContent().build();
	        
	        if( club.getDescription() != null )
	        	club1.setDescription( club.getDescription() );
	        if( club.getNom() != null )
	        	club1.setNom( club.getNom() );
	        if( club.getLogo() != null )
	        	club1.setLogo( club.getLogo() );
	        
	        
	        
	        this.clubRepository.save( club1 );
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path( "/{id}" )
	                .buildAndExpand( club1.getId() )
	                .toUri();
	        return ResponseEntity.created(location).body( club1 );
	    }
	 
	 
	 @SuppressWarnings("unused")
	@PatchMapping( value = "/{idClub}/{idUtilisateur}" )
	 public ResponseEntity<Club> creerUnClub(@PathVariable Long idClub, @PathVariable Long idUtilisateur) { 	
		 	
		 	Club club = this.clubRepository.getClubById(idClub);
	    	Utilisateur utilisateur = this.utilisateurRepository.getUtilisateurById(idUtilisateur);
	    	
	    	club.setUtilisateur( utilisateur );
	    	
	        if ( club == null )
	            return ResponseEntity.noContent().build();
	        
	        
	       this.clubRepository.save( club );
	       this.utilisateurRepository.save( utilisateur );
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand( club.getId() )
	                .toUri();
	        return ResponseEntity.created(location).body( club );
	    }
	 
	    @DeleteMapping (value = "/one/{idClub}/{idUtilisateur}")
	    public void supprimerOneUtilisateurToClub(@PathVariable Long idClub, @PathVariable Long idUtilisateur) {
	    	
			Club club = this.clubRepository.getClubById( idClub );
			club.removeUtilisateur( idUtilisateur );
			this.clubRepository.save( club );
	    }
	    
}
