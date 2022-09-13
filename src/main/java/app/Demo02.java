package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {

	public static void main(String[] args) {
		//Configurar --> obtiene la conexion (DAO)
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
				EntityManager em = fabrica.createEntityManager();
				
				// Empieza la transaccion
				em.getTransaction().begin(); // --> registrar, actualizar, eliminar
				
				//Proceso -> actualizar un nuevo Usuario
				Usuario u = new Usuario(20, "Brandon", "Villegas Galvan", "VGBrandon", "123", "1999/06/09", 1, 1);	
				em.merge(u); // --> solo para actualizar
				//Confirmar los procesos
				em.getTransaction().commit(); // confirma los procesos
				
				//cerrar
				em.close();

	}

}
