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

import tn.uud.ulysse.models.Activite;
import tn.uud.ulysse.models.Club;
import tn.uud.ulysse.models.FileDoc;
import tn.uud.ulysse.repositry.ActiviteRepository;
import tn.uud.ulysse.repositry.ClubRepository;
import tn.uud.ulysse.repositry.FileDocRepository;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/activites")
public class ActiviteController {
	@Autowired
	private FileDocRepository fileDocRepository;
	
	@Autowired
	private ActiviteRepository activiteRepository;
	
	@Autowired
	private ClubRepository clubRepository;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Activite>> getActivites() {
		List<Activite> activites = this.activiteRepository.findAll();
		
		if ( activites == null )
            return ResponseEntity.noContent().build();

        return ResponseEntity.created(null).body(activites);
	}
	
	@PostMapping(value = "/{id}")
	public Activite createActivite(@PathVariable Long id, @RequestBody Activite activite ) {
		Activite activite1 = this.activiteRepository.save( activite );
		Club club = this.clubRepository.getClubById(id);
		club.setActivite(activite1);
		this.clubRepository.save( club );
		return activite1;
	}
	
	@DeleteMapping (value = "/{id}")
    public void supprimerActivite(@PathVariable Long id) {
    	
		Activite activite = this.activiteRepository.getActiviteById(id);
		activite.removeFileDocs();
		this.activiteRepository.save( activite );
		this.activiteRepository.delete( activite );
    }
	
	
	 @GetMapping(value = "/{id}")
	    public ResponseEntity<Activite> getActivite(@PathVariable Long id) {
		 Activite activite = this.activiteRepository.getActiviteById(id);
	    	 if (activite == null)
		          return ResponseEntity.noContent().build();


	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(activite.getId())
	                .toUri();
	        return ResponseEntity.created(location).body(activite);
	    }
	 
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
	    public ResponseEntity<Activite> updateActivite(@PathVariable Long id, @RequestBody Activite activite) {
		 Activite activite1 =  this.activiteRepository.getActiviteById(id);
	        if (activite1 == null)
	            return ResponseEntity.noContent().build();
	        
	        if( activite.getTitre() != null )
	        	activite1.setTitre( activite.getTitre() );
	        if( activite.getDescription() != null )
	        	activite1.setDescription( activite.getDescription() );
	        if( activite.getObjectif() != null )
	        	activite1.setObjectif( activite.getObjectif() );
	        if( activite.getDate() != null )
	        	activite1.setDate( activite.getDate() );
	        
	        
	        
	        this.activiteRepository.save( activite1 );
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path( "/{id}" )
	                .buildAndExpand( activite1.getId() )
	                .toUri();
	        return ResponseEntity.created(location).body( activite1 );
	    }
	 
	 
	 @SuppressWarnings("unused")
	@PatchMapping( value = "/{idActivite}/{idDoc}" )
	 public ResponseEntity<Activite> reserverUneDoc(@PathVariable Long idActivite, @PathVariable Long idDoc) { 	
		 	
		 	Activite activite = this.activiteRepository.getActiviteById(idActivite);
	    	FileDoc doc = this.fileDocRepository.getFileDocById(idDoc);
	    	
	    	activite.setFileDoc( doc );
	    	
	        if ( activite == null )
	            return ResponseEntity.noContent().build();
	        
	        
	       this.activiteRepository.save( activite );
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand( activite.getId() )
	                .toUri();
	        return ResponseEntity.created(location).body( activite );
	    }
	 
	    @DeleteMapping (value = "/file-doc/{idActivite}/{idDoc}")
	    public void supprimerOneFileDoc2(@PathVariable Long idActivite, @PathVariable Long idDoc) {
	    	
			Activite activite = this.activiteRepository.getActiviteById(idActivite);
			FileDoc doc = this.fileDocRepository.getFileDocById(idDoc);
			activite.getFileDocs().remove( doc );
			this.activiteRepository.save( activite );
			this.fileDocRepository.delete( doc );
	    }
	    
	    
}
