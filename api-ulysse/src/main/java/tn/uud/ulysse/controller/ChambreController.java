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

import tn.uud.ulysse.models.Chambre;
import tn.uud.ulysse.models.FileDoc;
import tn.uud.ulysse.models.Utilisateur;
import tn.uud.ulysse.repositry.ChambreRepository;
import tn.uud.ulysse.repositry.FileDocRepository;
import tn.uud.ulysse.repositry.UtilisateurRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/chambres")
public class ChambreController {
	@Autowired
	private FileDocRepository fileDocRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private ChambreRepository chambreRepository;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Chambre>> getChambres() {
		List<Chambre> chambres = this.chambreRepository.findAll();
		
		for( Chambre c: chambres ) {
			c.setDisponible();
			this.chambreRepository.save(c);
		}
		
		if (chambres == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.created(null).body(chambres);
	}
	
	@PostMapping(value = "/")
	public Chambre createChambre( @RequestBody Chambre chambre ) {
		return this.chambreRepository.save(chambre);
	}
	
	@DeleteMapping (value = "/{id}")
    public void supprimerChambre(@PathVariable Long id) {
    	
		Chambre chambre = this.chambreRepository.getChambreById(id);
		chambre.removeUtilisateurs();
		this.chambreRepository.save(chambre);
    	this.chambreRepository.delete(chambre);
    }
	
	
	 @GetMapping(value = "/{id}")
	    public ResponseEntity<Chambre> getChambre(@PathVariable Long id) {
		 Chambre chambre = this.chambreRepository.getChambreById(id);
	    	 if (chambre == null)
		          return ResponseEntity.noContent().build();


	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(chambre.getId())
	                .toUri();
	        return ResponseEntity.created(location).body(chambre);
	    }
	 
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
	    public ResponseEntity<Chambre> updateUtilisateur(@PathVariable Long id, @RequestBody Chambre chambre) {
		 Chambre chambre1 =  this.chambreRepository.getChambreById(id);
	        if (chambre1 == null)
	            return ResponseEntity.noContent().build();
	        
	        if( chambre.getNumbero() != 0 )
	        	chambre1.setNumbero( chambre.getNumbero() );
	        if( chambre.getDescription() != null )
	        	chambre1.setDescription( chambre.getDescription() );;
	        
	        
	        
	        this.chambreRepository.save( chambre1 );
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path( "/{id}" )
	                .buildAndExpand( chambre1.getId() )
	                .toUri();
	        return ResponseEntity.created(location).body( chambre1 );
	    }
	 
	 
	 @SuppressWarnings("unused")
	@PatchMapping( value = "/{idChambre}/{idUtilisateur}" )
	 public ResponseEntity<Chambre> reserverUneChambre(@PathVariable Long idChambre, @PathVariable Long idUtilisateur) { 	
		 	
		 	Chambre chambre = this.chambreRepository.getChambreById(idChambre);
	    	Utilisateur utilisateur = this.utilisateurRepository.getUtilisateurById(idUtilisateur);
	    	
	    	chambre.setUtilisateur( utilisateur );
	    	utilisateur.setChambre(chambre);
	    	
	        if ( chambre == null )
	            return ResponseEntity.noContent().build();
	        
	        
	       this.chambreRepository.save( chambre );
	       this.utilisateurRepository.save( utilisateur );
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand( chambre.getId() )
	                .toUri();
	        return ResponseEntity.created(location).body( chambre );
	    }
	 
	    @DeleteMapping (value = "/one/{idChambre}/{idUtilisateur}")
	    public void supprimerOneUtilisateur(@PathVariable Long idChambre, @PathVariable Long idUtilisateur) {
	    	
			Chambre chambre = this.chambreRepository.getChambreById(idChambre);
			chambre.removeUtilisateur(idUtilisateur);
			this.chambreRepository.save(chambre);
	    }
	    
	    @DeleteMapping (value = "/one-doc/{idChambre}/{idDoc}")
	    public void supprimerOneFileDoc(@PathVariable Long idChambre, @PathVariable Long idDoc) {
	    	
			Chambre chambre = this.chambreRepository.getChambreById(idChambre);
			FileDoc doc = this.fileDocRepository.getFileDocById(idDoc);
			chambre.getFileDocs().remove(  doc );
			this.chambreRepository.save(chambre);
			this.fileDocRepository.delete( doc );
			
	
	    }

}
