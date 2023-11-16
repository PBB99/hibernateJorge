package Main;

import org.hibernate.*;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

import Modelo.Doctor;
import Modelo.Especialidades;
import Modelo.Hospital;

public class Main {

	public static void main(String[] args) {

		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Doctor.class)
				.addAnnotatedClass(Especialidades.class).addAnnotatedClass(Hospital.class).buildSessionFactory();
		Session miSesion = miFactory.openSession();

		String[] tablasDMl = { "Hospital", "Doctor", "Especialidades" };

		String[] opciones = { "Tabla", "Relaciones" };
		String[] dml = { "INSERT", "uPDATE", "DELETE", "SELECT" };

		int tabla = JOptionPane.showOptionDialog(null, "¿En qué tabla quieres operar?", "Elige",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, tablasDMl, tablasDMl[0]);

		switch (tabla) {
//se elige operar en Hospital
		case 0:
			// ELIGES WHERE O OBJETOS
			int opcion = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Elige",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			switch (opcion) {
			// se elige Tabla u Objeto
			case 0:
				// elige entre insert updtae delete o select
				int opcionDml = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Elige",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dml, dml[0]);
				switch (opcionDml) {
				// hacer un insert
				case 0:
					// guarda el hospital
					String nomnbreHospital = JOptionPane.showInputDialog(null, "Como se llama el hospital",
							"Nombre Hospital", JOptionPane.INFORMATION_MESSAGE);
					Hospital h = new Hospital(nomnbreHospital);
					miSesion.beginTransaction();
					miSesion.save(h);
					miSesion.getTransaction().commit();
					break;
				// hacer un update por id del hospital
				case 1:
					int idHospital = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué id tiene  el hospital?",
							"Id Hospital", JOptionPane.INFORMATION_MESSAGE));
					nomnbreHospital = JOptionPane.showInputDialog(null, "Como lo quieres llamar al hospital",
							"Nombre Hospital", JOptionPane.INFORMATION_MESSAGE);
					miSesion.beginTransaction();
					Hospital h2 = new Hospital(idHospital, nomnbreHospital);
					miSesion.update(h2);
					miSesion.getTransaction().commit();
					break;
				// hacer un delete de un hospital por id
				case 2:
					miSesion.beginTransaction();
					idHospital = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué id tiene  el hospital?",
							"Id Hospital", JOptionPane.INFORMATION_MESSAGE));
					Hospital h3 = new Hospital(idHospital, "nombre");
					miSesion.delete(h3);
					miSesion.getTransaction().commit();
					break;
				// hacer una consulta select con where(sacar el nombre por id)
				case 3:
					idHospital = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué id tiene  el hospital?",
							"Id Hospital", JOptionPane.INFORMATION_MESSAGE));
					Query<Hospital> consulta = miSesion.createQuery("FROM Hospital where idhospital=:idHospital",
							Hospital.class);
					consulta.setParameter("idHospital", idHospital);
					Hospital h4 = consulta.getSingleResult();
					JOptionPane.showMessageDialog(null, h4, "Resultado de la Consulta",
							JOptionPane.INFORMATION_MESSAGE);

					break;
				}
				break;
			// a través de relaciones
			case 1:
				System.out.println("prueba");
				break;
			}
			break;
		case 1:
			opcion = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Elige",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			switch (opcion) {
			case 0:
				// elige entre insert updtae delete o select
				int opcionDml = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Elige",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dml, dml[0]);
				switch (opcionDml) {
				// hacer un insert
				case 0:
					// guarda el doctor
					String nomnbreDoctor = JOptionPane.showInputDialog(null, "Como se llama el doctor",
							"Nombre Hospital", JOptionPane.INFORMATION_MESSAGE);
					String contraDoctor = JOptionPane.showInputDialog(null, "Contraseña del doctor",
							"Contraseña Hospital", JOptionPane.INFORMATION_MESSAGE);
					//RELACION HASTA
//					Query<Hospital> consulta = miSesion.createQuery("FROM Hospital", Hospital.class);
//					List<Hospital> lista = consulta.getResultList();
					//SACAR UNA LISTA DE DOCTORES
//					Query<Doctor> consulta2 = miSesion.createQuery("FROM Doctor ",Doctor.class);
//					List<Doctor>listaDoctores=consulta2.getResultList();
					Doctor d = new Doctor(nomnbreDoctor, contraDoctor);
//					Hospital hospitales = (Hospital) JOptionPane.showInputDialog(null, "Hospitales", "Elegir",
//							JOptionPane.QUESTION_MESSAGE, null, lista.toArray(), lista.toArray()[0]);
//					d.setHospiral(hospitales);
					//RELACION AQUI
					miSesion.beginTransaction();
					miSesion.save(d);
					miSesion.getTransaction().commit();
					break;
				// hacer un update por id del hospital
				case 1:
					int idDoctor = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué id tiene  el doctor?",
							"Id Doctor", JOptionPane.INFORMATION_MESSAGE));
					String nuevaContra = JOptionPane.showInputDialog(null, "Cual va a ser la nueva contraseña",
							"Cambio contrseña", JOptionPane.INFORMATION_MESSAGE);
					miSesion.beginTransaction();
					Doctor d2 = new Doctor(idDoctor, nuevaContra);
					miSesion.update(d2);
					miSesion.getTransaction().commit();
					break;
				// hacer un delete de un hospital por id
				case 2:
					miSesion.beginTransaction();
					idDoctor = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué doctor quieres borrar?",
							"Id Doctor", JOptionPane.INFORMATION_MESSAGE));
					Doctor d3 = new Doctor(idDoctor, "nombre");
					miSesion.delete(d3);
					miSesion.getTransaction().commit();
					break;
				// hacer una consulta select con where(sacar el nombre por id)
				case 3:
					idDoctor = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué id tiene  el doctor?",
							"Id Hospital", JOptionPane.INFORMATION_MESSAGE));
					Query<Doctor> consulta2 = miSesion.createQuery("FROM Doctor where iddoctor=:idDoctor",
							Doctor.class);
					consulta2.setParameter("idDoctor", idDoctor);
					Doctor d4 = consulta2.getSingleResult();
					JOptionPane.showMessageDialog(null, d4 + d4.getHospital().toString(), "Resultado de la Consulta",
							JOptionPane.INFORMATION_MESSAGE);

					break;
				}
				break;
			case 1:
				System.out.println("switch opcion case 2");
				break;
			}
		case 2: //tabla especialidades
			opcion = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Elige",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			switch (opcion) {
			case 0:
				// elige entre insert updtae delete o select
				int opcionDml = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Elige",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dml, dml[0]);
				switch (opcionDml) {
				// hacer un insert
				case 0:
					// guarda el doctor
					String nomnbreEspecialidad = JOptionPane.showInputDialog(null, "Como se llama la especialidad",
							"Nombre Especialidad", JOptionPane.INFORMATION_MESSAGE);			
					Especialidades e=new Especialidades(nomnbreEspecialidad);			
					miSesion.beginTransaction();
					miSesion.save(e);
					miSesion.getTransaction().commit();
					break;
				// hacer un update por id de la especialidad
				case 1:
					int idEspecialidad = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué id tiene  la especialidade a cambiar?",
							"Id Especialidad", JOptionPane.INFORMATION_MESSAGE));
					String nuevaEspecialidad = JOptionPane.showInputDialog(null, "Qué nombre va a recibir la especialidad",
							"Cambio contrseña", JOptionPane.INFORMATION_MESSAGE);
					miSesion.beginTransaction();
					Especialidades e2=new Especialidades(idEspecialidad,nuevaEspecialidad);
					miSesion.update(e2);
					miSesion.getTransaction().commit();
					break;
				// hacer un delete de una especialidad por id
				case 2:
					miSesion.beginTransaction();
					idEspecialidad = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué especialidad quieres borrar?",
							"Id Doctor", JOptionPane.INFORMATION_MESSAGE));
					Especialidades e3=new Especialidades(idEspecialidad,"borrar");
					miSesion.delete(e3);
					miSesion.getTransaction().commit();
					break;
				// hacer una consulta select con where(sacar el nombre por id)
				case 3:
					idEspecialidad = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué id tiene  la especialidade?",
							"Id Especialidad", JOptionPane.INFORMATION_MESSAGE));
					Query<Especialidades> consulta3 = miSesion.createQuery("FROM Especialidades where idespecialidades=:idEspecialidad",
							Especialidades.class);
					consulta3.setParameter("idEspecialidad", idEspecialidad);
					Especialidades e4 = consulta3.getSingleResult();
					JOptionPane.showMessageDialog(null, e4, "Resultado de la Consulta",
							JOptionPane.INFORMATION_MESSAGE);

					break;
				}
				break;
			case 1:
				System.out.println("switch opcion case 2");
				break;
			}
			break;
		case 3:// tabla Doctor_has_Especialidades
			
		break;
		}

	}
}
