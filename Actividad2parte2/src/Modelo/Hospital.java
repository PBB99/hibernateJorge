package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Hospital")
public class Hospital implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idhospital")
	private Integer idhospital;
	@Column(name = "nombre")
	private String nombre;

	public Integer getIdhospital() {
		return idhospital;
	}

	public void setIdhospital(Integer idhospital) {
		this.idhospital = idhospital;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Hospital [idhospital=" + idhospital + ", nombre=" + nombre + "]";
	}

	public Hospital() {
	}

	public Hospital( String nombre) {
	
		this.nombre = nombre;
	}
	
	public Hospital( int id,String nombre) {
		this.idhospital=id;
		this.nombre = nombre;
	}
	
	 @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	 
	 private List<Doctor> doctores;
     
     public List<Doctor> getDoctores(){
         return doctores;
     }
     
     public void addDoctor(Doctor d){
         if (doctores == null) doctores=new ArrayList<>();
         doctores.add(d);
         d.setHospiral(this);
     }
}
