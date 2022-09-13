package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo06 {
	//Listado de todos los usuarios
	public static void main(String[] args) {
		// obtener la conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//proceso --> consulta -> no lleva ...begin()
		//select * from tb_xxxx --> coleccion List / Array
		//lista de java utils
		// Para ser consultas hay varias formas
		//createNamedQuery ->ejecuta consulta asociada a un nombre
		//createNativeQuery -> ejecuta sentencias propiamente sql -> recomendable
		//cuando las tablas no estan normalizadas
		//createQuery -> ejecuta sentencias sql normalizadas utilizando sintanxis jpa(entidades) usando  jpql
		List<Usuario> lstUsuarios =em.createQuery("select u from Usuario u",Usuario.class).getResultList();
		
		//mostrar el resultado (lista)
		System.out.println("Listado");
		for (Usuario u : lstUsuarios) {
			System.out.println("Codigo : " + u.getCodigo());
			System.out.println("Nombre : " + u.getNombre() + " "+ u.getApellido());
			System.out.println("Tipo.. : " + u.getTipo() + " " + u.getObjTipo().getDescripcion());
			System.out.println("-------------------- ");
			
		}
		
		//cerrar
		em.close();
	}
}
