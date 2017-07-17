package dao;

import java.util.ArrayList;
import java.util.HashSet;
import model.Mascota;
import model.Person;

public class DBConnector  extends DBManager implements HVServices{

	
	
	// Miquel 
	@Override
	public void insert(Person owner, Mascota mascota) {

		if(owner==null){
			throw new RuntimeException("No se pueden enviar objectos nulos");
		}else{
		
			connect();
			getEntityManager().getTransaction().begin();
			
				if(owner.getId()==0)
					getEntityManager().persist(owner);
				else
				    owner = getEntityManager().find(Person.class, owner.getId());
				
				if(mascota!=null){
					owner.getMascotas().add(mascota);
					mascota.setOwner(owner);
				}
				
			getEntityManager().getTransaction().commit();
			close();
		}
	}

	// Jordi 
	@Override
	public void remove(Mascota mascota) {

		connect();
			getEntityManager().getTransaction().begin();
				getEntityManager().remove(getEntityManager().find(Mascota.class, mascota.getId()));
			getEntityManager().getTransaction().commit();
		close();
		
	}

	//Jordi //Luis
	@Override
	public void remove(Person owner) {
		connect();
			getEntityManager().getTransaction().begin();
				getEntityManager().remove(getEntityManager().find(Person.class, owner.getId()));
			getEntityManager().getTransaction().commit();
		close();
	}

	//Toni 
	@Override
	public void update(Person person) {
		
		if(person==null){
			throw new RuntimeException("No se pueden enviar objectos con valor null");
		}else if(person.getId()<=0){
			throw new RuntimeException("No se puede enviar un contacto para update con una ID 0 o menor que 0");
		}else{
				connect();
				Person recovered = find(Person.class, person.getId());
				
			/*  
			    // FORMA DE UPDATE CON MERGE (MÉTODO IDEAL PARA GENÉRICO)
				getEntityManager().getTransaction().begin();
				recovered = person;
				getEntityManager().merge(recovered);
				getEntityManager().getTransaction().commit();

			*/
				
				// FORMA DE UPDATE CON CONOCIMIENTO DE LAS INSTANCIAS
				getEntityManager().getTransaction().begin();
					recovered.setName(person.getName());
					recovered.setSurname(person.getSurname());
					recovered.setAddress(person.getAddress());
					recovered.setEmail(person.getEmail());
					recovered.setPhone(person.getPhone());
				getEntityManager().getTransaction().commit();
			
				close();
		}
		
	}

	//Toni //Eduar 
	@Override
	public void update(Mascota mascota) {
		
		if(mascota==null){
			throw new RuntimeException("No se pueden enviar objectos nulos");
		}else{
				connect();
				Mascota recovered = find(Mascota.class, mascota.getId());
				
			/*  
			    // FORMA DE UPDATE CON MERGE (MÉTODO IDEAL PARA GENÉRICO)
				getEntityManager().getTransaction().begin();
				recovered = mascota;
				getEntityManager().merge(recovered);
				getEntityManager().getTransaction().commit();

			*/
				
				// FORMA DE UPDATE CON CONOCIMIENTO DE LAS INSTANCIAS
				getEntityManager().getTransaction().begin();
					recovered.setName(mascota.getName());
					recovered.setTypeClass(mascota.getTypeClass());
					recovered.setHeight(mascota.getHeight());
					recovered.setLength(mascota.getLength());
					recovered.setWeight(mascota.getWeight());
				getEntityManager().getTransaction().commit();
			
				close();
		}
	}

	//Eduard 
	@Override
	public HashSet<Person> findLikeByOwnerName(String strLike) {
		connect();
		ArrayList<Person> list = selectLike(Person.class, "name", strLike);  
		close();
		HashSet<Person> set = new HashSet<>(list);
		return set;
	}

	//Luis 
	@Override
	public HashSet<Mascota> findMascota(int idPerson) {
		connect();
		ArrayList<Mascota> list = selectEqual(Mascota.class, "owner.id", ""+idPerson);
		close();
		HashSet<Mascota> set = new HashSet<>(list);
		return set;
		
	}

	//Dorian 
	@Override
	public HashSet<Person> selectAllPerson() {
		connect();
		ArrayList<Person> listAll = selectAll(Person.class);
		close();
		HashSet<Person> set = new HashSet<>(listAll);
		return set;
	}

	//Dorian 
	@Override
	public HashSet<Mascota> selectAllMascota() {
		connect();
		ArrayList<Mascota> listAll = selectAll(Mascota.class);
		close();
		HashSet<Mascota> set = new HashSet<>(listAll);
		return set;
		
	}
	
}
