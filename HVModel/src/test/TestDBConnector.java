package test;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBConnector;
import model.Mascota;
import model.Person;

public class TestDBConnector {
	DBConnector dbConnector; 

	@Before
	public void init(){
		dbConnector = new DBConnector(); 	
		dbConnector.connect();
		dbConnector.deleteAll(Person.class);
		dbConnector.close();
	}
	
	@Test
	public void testSbConection(){	
		dbConnector.connect();
		 	Assert.assertNotNull(dbConnector.getEntityManager()); 
		dbConnector.close();
	}
	
	@Test
	public void testInsert(){
		
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		Person person4 = getMockPerson("Julian", "Reton");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");
		Mascota mascota4 = getMockMascota("Ratata", "roedor");
		
		dbConnector.insert(person1,mascota1);
		dbConnector.insert(person2,mascota2);
		dbConnector.insert(person3,mascota3);
		dbConnector.insert(person4,mascota4);
		
		dbConnector.connect();
		ArrayList<Mascota> list  = dbConnector.selectAll(Mascota.class);
		dbConnector.close();
		
		Assert.assertEquals(4, list.size());
		
		Assert.assertEquals("Rex", list.get(0).getName());
		Assert.assertEquals("Jose", list.get(0).getOwner().getName());
		Assert.assertEquals("Garfield", list.get(1).getName());
	}
	
	@Test
	public void testFindAllMascotas(){
		
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		Person person4 = getMockPerson("Julian", "Reton");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");
		Mascota mascota4 = getMockMascota("Ratata", "roedor");
		
		dbConnector.insert(person1,mascota1);
		dbConnector.insert(person2,mascota2);
		dbConnector.insert(person3,mascota3);
		dbConnector.insert(person4,mascota4);
		
		HashSet<Mascota> listaCompleta =  dbConnector.selectAllMascota();
		
		Assert.assertEquals(4, listaCompleta.size());

	}

	@Test
	public void testFindAllPerson(){
		
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");
		Mascota mascota4 = getMockMascota("Ratata", "roedor");
		
		dbConnector.insert(person1,mascota1);
		dbConnector.insert(person2,mascota2);
		dbConnector.insert(person3,mascota3);
		dbConnector.insert(person2,mascota4);
		
		HashSet<Person> listaCompleta =  dbConnector.selectAllPerson();
		
		Assert.assertEquals(3, listaCompleta.size());
	}
	
	@Test
	public void testFindLikeByOwnerName(){
		
		String ownerName = "ju";
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		Person person4 = getMockPerson("Julian", "Reton");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");
		Mascota mascota4 = getMockMascota("Ratata", "roedor");
				
			dbConnector.insert(person1,mascota1);
			dbConnector.insert(person2,mascota2);
			dbConnector.insert(person3,mascota3);
			dbConnector.insert(person4,mascota4);
		
		HashSet<Person> list = dbConnector.findLikeByOwnerName(ownerName);
		
		Assert.assertEquals(2, list.size());
			
	}
	
	@Test
	public void testFindMascotasOfAnOwner(){
		
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");
		Mascota mascota4 = getMockMascota("Ratata", "roedor");
		Mascota mascota5 = getMockMascota("Tobby", "canido");		
		Mascota mascota6 = getMockMascota("Guacamayo", "ave");
		
		dbConnector.insert(person1,mascota1);
		dbConnector.insert(person1,mascota6);
		dbConnector.insert(person2,mascota2);
		dbConnector.insert(person2,mascota4);
		dbConnector.insert(person2,mascota5);
		dbConnector.insert(person3,mascota3);

		
		HashSet<Mascota> listaPerson2 =  dbConnector.findMascota(person2.getId());
		HashSet<Mascota> listaPerson1 =  dbConnector.findMascota(person1.getId());
		
		Assert.assertEquals(3, listaPerson2.size());
		Assert.assertEquals(2, listaPerson1.size());

	}
	
	@Test
	public void testUpdateMascota(){
		
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");

		
		dbConnector.insert(person1,mascota1);
		dbConnector.insert(person2,mascota2);
		dbConnector.insert(person3,mascota3);

		mascota2.setName("SuperGarfield");
		
			dbConnector.update(mascota2);
			
			dbConnector.connect();
			Mascota mascotaRecovered = dbConnector.find(Mascota.class,mascota2.getId());
			dbConnector.close();
			
		Assert.assertEquals("SuperGarfield", mascotaRecovered.getName());
	}
	
	@Test
	public void testUpdatePerson(){
		
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");

		
		dbConnector.insert(person1,mascota1);
		dbConnector.insert(person2,mascota2);
		dbConnector.insert(person3,mascota3);

		person3.setName("Marquitos");
		
			dbConnector.update(person3);
			
			dbConnector.connect();
			Person personRecovered = dbConnector.find(Person.class,person3.getId());
			dbConnector.close();
			
		Assert.assertEquals("Marquitos", personRecovered.getName());
	}
	
	@Test
	public void testRemovePerson(){
		
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");
		Mascota mascota4 = getMockMascota("Ratata", "roedor");
		Mascota mascota5 = getMockMascota("Tobby", "canido");		
		Mascota mascota6 = getMockMascota("Guacamayo", "ave");
		
		dbConnector.insert(person1,mascota1);
		dbConnector.insert(person2,mascota2);
		dbConnector.insert(person3,mascota3);
		dbConnector.insert(person3,mascota4);
		dbConnector.insert(person3,mascota5);
		dbConnector.insert(person3,mascota6);

		
			dbConnector.remove(person3);
			
			dbConnector.connect();
			ArrayList<Person> allPersons = dbConnector.selectAll(Person.class);
			ArrayList<Mascota> allMascotas = dbConnector.selectAll(Mascota.class);
			dbConnector.close();
			
		Assert.assertEquals(2, allPersons.size());
		Assert.assertEquals(2, allMascotas.size());
	}
	
	
	// MOCKLIST
	
	private Person getMockPerson(String name, String surname) {
		 Person person = new Person();
		 person.setName(name);
		 person.setSurname(surname);
		 person.setEmail("prueba123@gmail.com");
		 person.setPhone("000000000");
		 person.setAddress("Calle falsa 123");
		 return person;
	}
	
	private	Mascota getMockMascota(String name, String type) {
		Mascota mascota = new Mascota();
		mascota.setName(name);
		mascota.setTypeClass(type);
		mascota.setLength(1.1f);
		mascota.setHeight(0.9f);
		mascota.setWeight(123.321f);
		return  mascota;
	}
	
	
}