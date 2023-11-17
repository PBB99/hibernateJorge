package Modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ciudad")
public class Ciudad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idciudad")
	private Integer idciudad;
	@Column(name = "nombre")
	private String nombre;

	public Ciudad() {
	}

	public Ciudad(int id, String nommbre) {
		this.idciudad = idciudad;
		this.nombre = nommbre;
	}
	
	 @OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
     @JoinColumn(name = "idhospital")
     private Hospital hospital;

     public Hospital getHospital() {
         return hospital;
     }

     public void setHospital(Hospital hospital) {
         this.hospital = hospital;
     }
	
     @Override
 	public String toString() {
 		return "Ciudad [idciudad=" + idciudad + ", nombre=" + nombre + "]";
 	}
    
}