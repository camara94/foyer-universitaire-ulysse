package tn.uud.ulysse.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
public class Activite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	private String titre;
	private Date date;
	@Column
	@Type(type="text")
	private String description;
	@Column
	@Type(type="text")
	private String Objectif;
	
	@OneToMany
	private List<FileDoc> fileDocs;
	
	
	
	public Activite() {}

	public Activite(Long id, String titre, Date date, String description, String objectif) {
		super();
		this.id = id;
		this.titre = titre;
		this.date = date;
		this.description = description;
		Objectif = objectif;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObjectif() {
		return Objectif;
	}

	public void setObjectif(String objectif) {
		Objectif = objectif;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
