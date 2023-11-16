package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity
@Table (name="Especialidades")
public class Especialidades implements Serializable{

	@Id
	@Column(name="idespecialidades")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idespecialidades;
	
	@Column(name="especialidad")
	private String especialidad;

	public Integer getIdespecialidades() {
		return idespecialidades;
	}

	public void setIdespecialidades(Integer idespecialidades) {
		this.idespecialidades = idespecialidades;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Especialidades [idespecialidades=" + idespecialidades + ", especialidad=" + especialidad + "]";
	}
	
	public Especialidades() {}
	
	public Especialidades(int id, String especialidades) {
		this.idespecialidades=id;
		this.especialidad=especialidades;
	}
	public Especialidades( String especialidades) {
		this.especialidad=especialidades;
	}
	
	 @ManyToMany(mappedBy = "especialidades")
	 
	    private List<Doctor> doctores = new ArrayList<Doctor>();

	    public List<Doctor> getDoctores() {
	        return doctores;
	    }

	    public void setDoctores(List<Doctor> doctores) {
	        this.doctores = doctores;
	    }
	    
	    public void addCliente(Doctor c)
	    {
	        this.doctores.add(c);
	        c.getEspecialidades().add(this);
	    }

}
