package tn.uud.ulysse.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.uud.ulysse.models.Utilisateur;
import tn.uud.ulysse.repositry.UtilisateurRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/utilisateurs")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Utilisateur>> getUtisateurs() {
		List<Utilisateur> utilisateurs = this.utilisateurRepository.findAll();
		
		if (utilisateurs == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.created(null).body(utilisateurs);
	}
	
	@PostMapping(value = "/")
	public Utilisateur createUtilisateur( @RequestBody Utilisateur utilisateur ) {
		return this.utilisateurRepository.save(utilisateur);
	}
	
	@DeleteMapping (value = "/{id}")
    public void supprimerUtilisateur(@PathVariable Long id) {
    	
    	Utilisateur utilisateur = this.utilisateurRepository.getUtilisateurById(id);
    	this.utilisateurRepository.delete(utilisateur);
    }
	
	
	 @GetMapping(value = "/{id}")
	    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
	    	Utilisateur utilisateur = this.utilisateurRepository.getUtilisateurById(id);
	    	 if (utilisateur == null)
		          return ResponseEntity.noContent().build();


	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(utilisateur.getId())
	                .toUri();
	        return ResponseEntity.created(location).body(utilisateur);
	    }
	 
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
	    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
		 Utilisateur utilisateur1 =  this.utilisateurRepository.getUtilisateurById(id);
	        if (utilisateur1 == null)
	            return ResponseEntity.noContent().build();
	        
	        if(utilisateur.getNom() != null)
	        	utilisateur1.setNom( utilisateur.getNom() );
	        if(utilisateur.getPrenom() != null)
	        	utilisateur1.setPrenom( utilisateur.getPrenom() );
	        if( utilisateur.getCin() != null )
	        	utilisateur1.setCin( utilisateur.getCin() );
	        if( utilisateur.getTelephone() != null )
	        	utilisateur1.setTelephone( utilisateur.getTelephone() );
	        if( utilisateur.getEmail() != null )
	        	utilisateur1.setEmail( utilisateur.getEmail() );
	        if( utilisateur.getDepartement() != null )
	        	utilisateur1.setDepartement( utilisateur.getDepartement() );
	        if( utilisateur.getNiveau() != null )
	        	utilisateur1.setNiveau( utilisateur.getNiveau() );
	        
	        this.utilisateurRepository.save( utilisateur1 );
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(utilisateur1.getId())
	                .toUri();
	        return ResponseEntity.created(location).body(utilisateur1);
	    }

}
