package dao;

import java.util.HashSet;

import model.Mascota;
import model.Person;

public interface HVServices {
	
	
	/**
	 * Inserta una mascota con su respectivo propieratio
	 * @param owner
	 * @param mascota
	 */
	public void insert(Person owner, Mascota mascota); 
	
	/**
	 * Remueve una mascota sin eliminar el propietario 
	 * @param mascota
	 */
	public void remove(Mascota mascota); 
	
	/**
	 * Elimina todas las mascotas del propietario y el propietario 
	 * @param owner
	 */
	public void remove(Person owner); 
	
	
	/**
	 * Actualiza exclusivamente los datos del propietario 
	 * @param person
	 */
	public void update(Person person);
	
	/**
	 * Actualiza los datos de la mascota
	 * @param mascota
	 */
	public void update(Mascota mascota);
	
	
	
	/**
	 * Buscar propietarios por el nombre de usuario que inicia con
	 * strLike
	 * @param like
	 * @return
	 */
	public HashSet<Person> findLikeByOwnerName(String strLike); 
	
	/**
	 * Retorna la lista de mascotas de una persona
	 * @param idPerson
	 * @return
	 */
	public HashSet<Mascota> findMascota(int idPerson); 
	
	
	/**
	 * Lista todas las personas 
	 * @return
	 */
	public HashSet<Person> selectAllPerson(); 
	
	
	/**
	 * Lista todas las mascotas 
	 * @return
	 */
	public HashSet<Mascota> selectAllMascota();
	
	
}
