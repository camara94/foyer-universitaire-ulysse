package tn.uud.ulysse.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Club implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	private String nom;
	@OneToOne
	private FileDoc logo;
	private String description;
	
	@OneToMany
	private List<Utilisateur> utilisateurs;
	@OneToMany
	private List<Activite> activites;
	
	
	
	
	public Club() {}

	public Club(Long id, String nom, FileDoc logo, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.logo = logo;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public FileDoc getLogo() {
		return logo;
	}

	public void setLogo(FileDoc logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs( List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	public void setUtilisateur( Utilisateur utilisateur ) {
		this.utilisateurs.add( utilisateur );
	}
	
	
	public void removeUtilisateurs() {
		
		for(int i = 0; i < this.utilisateurs.size(); i++) {
			this.utilisateurs.remove( this.utilisateurs.get(i) );
		}
	}
	
	public void removeUtilisateur(Long id) {
		
		for(int i = 0; i < this.utilisateurs.size(); i++) {
			if ( this.utilisateurs.get(i).getId() == id  ) {
				this.utilisateurs.remove( this.utilisateurs.get(i) );
			}
		}
	}
	
	
	public List<Activite> getActivites() {
		return this.activites;
	}

	public void setActivites( List<Activite> activites) {
		this.activites = activites;
	}
	
	public void setActivite( Activite activite ) {
		this.activites.add( activite );
	}
	
	
	public void removeActivites() {
		
		for(int i = 0; i < this.activites.size(); i++) {
			this.activites.remove( this.activites.get(i) );
		}
	}
	
	public void removeActivite(Long id) {
		
		for(int i = 0; i < this.activites.size(); i++) {
			if ( this.activites.get(i).getId() == id  ) {
				this.activites.remove( this.activites.get(i) );
			}
		}
	}
	
	
}
