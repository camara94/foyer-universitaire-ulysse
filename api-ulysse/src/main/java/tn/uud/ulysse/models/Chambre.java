package tn.uud.ulysse.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Chambre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private long numbero;
	private String description;
	private boolean disponible=true;
	
	@JsonManagedReference
	@OneToMany
	private List<Utilisateur> utilisateurs;
	
	@JsonManagedReference
	@OneToMany
	private List<FileDoc> fileDocs;
	


	public Chambre() {
		super();
	}

	public Chambre(Long id, long numbero, String description, List<Utilisateur> utilisateurs, List<FileDoc> fileDocs ) {
		super();
		this.setDisponible();
		this.id = id;
		this.numbero = numbero;
		this.description = description;
		this.utilisateurs = utilisateurs;
		this.fileDocs = fileDocs;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getNumbero() {
		return numbero;
	}

	public void setNumbero(long numbero) {
		this.numbero = numbero;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible() {
		if ( this.utilisateurs.size() < 2 ) {
			this.disponible = true;
		} else {
			this.disponible = false;
		}
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateurs.add(utilisateur);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public List<FileDoc> getFileDocs() {
		return fileDocs;
	}

	public void setFileDocs(List<FileDoc> fileDocs) {
		this.fileDocs = fileDocs;
	}
	
	public void setFileDoc( FileDoc fileDoc) {
		this.fileDocs.add(fileDoc);
	}
	
	public void removeFileDocs() {
		
		for(int i = 0; i < this.fileDocs.size(); i++) {
			this.fileDocs.remove( this.fileDocs.get(i) );
		}
	}
	
	public void removeFileDoc(Long id) {
		
		for(int i = 0; i < this.fileDocs.size(); i++) {
			if ( this.fileDocs.get(i).getId() == id  ) {
				this.fileDocs.remove( this.fileDocs.get(i) );
			}
		}
	}
	
	
}
