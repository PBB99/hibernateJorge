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
import javax.persistence.Table;


@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAlumno")
	private Integer idAlumno;
	@Column(name = "nombre")
	private String nombre;

	public Alumno() {
	}

	public Alumno(int idAlumno, String nommbre) {
		this.idAlumno = idAlumno;
		this.nombre = nommbre;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "iddoctor")
    private Doctor doctor; //Este atributo va a @OneToMany en

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
	
}
