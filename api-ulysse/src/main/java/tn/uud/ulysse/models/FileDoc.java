package tn.uud.ulysse.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class FileDoc implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	private String name;
    private String uri;
    private String type;
    private long size;
    
    @JsonBackReference
	@ManyToOne
	private Chambre chambre;
    
    public FileDoc() {
    	super();
    }

	public FileDoc(String name, String uri, String type, long size) {
		super();
		this.name = name;
		this.uri = uri;
		this.type = type;
		this.size = size;
	}

	
	public FileDoc(Long id, String name, String uri, String type, long size, Chambre chambre) {
		super();
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.type = type;
		this.size = size;
		this.chambre = chambre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
    
}