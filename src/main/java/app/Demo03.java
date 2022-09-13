package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {

	public static void main(String[] args) {
		//Configurar --> obtiene la conexion (DAO)
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		// Empieza la transaccion
		em.getTransaction().begin(); // --> registrar, actualizar, eliminar
		
		//Proceso -> eliminar un Usuario
		Usuario u = em.find(Usuario.class, 20);
		em.remove(u); // --> solo para eliminar
		//Confirmar los procesos
		em.getTransaction().commit(); // confirma los procesos
		
		//cerrar
		em.close();

	}

}
