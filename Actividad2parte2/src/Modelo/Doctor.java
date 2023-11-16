package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




@Entity
@Table(name = "Doctor")
public class Doctor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddoctor")
	private Integer iddoctor;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "contraseña")
	private String contraseña;

	public Integer getIddoctor() {
		return iddoctor;
	}

	public void setIddoctor(Integer iddoctor) {
		this.iddoctor = iddoctor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Doctor [iddoctor=" + iddoctor + ", nombre=" + nombre + ", contraseña=" + contraseña + "]";
	}

	public Doctor() {
	}

	public Doctor(int id, String nommbre, String contra) {
		this.iddoctor = id;
		this.nombre = nommbre;
		this.contraseña = contra;
	}
	public Doctor( String nommbre, String contra) {
		
		this.nombre = nommbre;
		this.contraseña = contra;
	}
	public Doctor(int id,  String contra) {
		this.iddoctor = id;
		
		this.contraseña = contra;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "idhospital")
    private Hospital hospital; //Este atributo va a @OneToMany en

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospiral(Hospital hospital) {
        this.hospital = hospital;
    }
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Doctor_has_Especialidades",
        joinColumns = {@JoinColumn(name = "iddoctor")},
        inverseJoinColumns = {@JoinColumn(name = "idespecialidad")}
    )
    private List<Especialidades> especialidades = new ArrayList<Especialidades>();
    
    public List<Especialidades> getEspecialidades() {
    return especialidades;
    }
    public void addEspecialidad(Especialidades p)
    {
        this.especialidades.add(p);
        p.getDoctores().add(this);
    }
    public void setEspecialidad(List<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }
    
}
