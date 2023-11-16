package Main;

import org.hibernate.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
				switch(opcion) {
				//se elige Tabla
				case 0:
					int opcionDml = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Elige",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dml, dml[0]);
					switch(opcionDml) {
					//hacer un insert
					case 0:
						//guarda el hospital
						String nomnbreHospital=JOptionPane.showInputDialog(null,"Como se llama el hospital","Nombre Hospital",JOptionPane.INFORMATION_MESSAGE);
						Hospital h=new Hospital(nomnbreHospital);
						miSesion.beginTransaction();
						miSesion.save(h);
						miSesion.getTransaction().commit();
						break;
						//hacer un update por id del hospital
					case 1:
						int idHospital=Integer.parseInt(JOptionPane.showInputDialog(null,"¿Qué id tiene  el hospital?","Id Hospital",JOptionPane.INFORMATION_MESSAGE));
						 nomnbreHospital=JOptionPane.showInputDialog(null,"Como lo quieres llamar al hospital","Nombre Hospital",JOptionPane.INFORMATION_MESSAGE);
						 miSesion.beginTransaction();
						 Hospital h2=new Hospital(idHospital,nomnbreHospital);
						 miSesion.update(h2);
						 miSesion.getTransaction().commit();
						break;
						//hacer un delete  de un hospital por id
					case 2:
						miSesion.beginTransaction();
						idHospital=Integer.parseInt(JOptionPane.showInputDialog(null,"¿Qué id tiene  el hospital?","Id Hospital",JOptionPane.INFORMATION_MESSAGE));
						Hospital h3=new Hospital(idHospital,"nombre");
						miSesion.delete(h3);
						miSesion.getTransaction().commit();
						break;
						//hacer una consulta select con where(sacar el nombre por id)
					case 3:
						idHospital=Integer.parseInt(JOptionPane.showInputDialog(null,"¿Qué id tiene  el hospital?","Id Hospital",JOptionPane.INFORMATION_MESSAGE));
						Query<Hospital>consulta=miSesion.createQuery("FROM Hospital where idhospital=:idHospital",Hospital.class);
						consulta.setParameter("idHospital", idHospital);
						Hospital h4=consulta.getSingleResult();
						JOptionPane.showMessageDialog(null, h4,"Resultado de la Consulta",JOptionPane.INFORMATION_MESSAGE);
						
						break;
					}break;
					//a través de relaciones
				case 1:
					
					break;
				}
			break;
		
		}
	}

}
