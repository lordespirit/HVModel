package test;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAccess;
import dao.DBConnector;
import model.Mascota;
import model.Person;

public class TestDBConnector {
	DBConnector dbconnector; 


	@Before
	public void init(){
		dbconnector = new DBConnector(); 	
		dbconnector.connect();
		dbconnector.deleteAll(Person.class);
		dbconnector.close();
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
		
		dbconnector.insert(person1,mascota1);
		dbconnector.insert(person2,mascota2);
		dbconnector.insert(person3,mascota3);
		dbconnector.insert(person4,mascota4);
		
		dbconnector.connect();
			ArrayList<Mascota> listMascotas  = dbconnector.selectAll(Mascota.class);
			ArrayList<Person> listPersonas  = dbconnector.selectAll(Person.class);
		dbconnector.close();
		
		Assert.assertEquals(4, listPersonas.size());
		Assert.assertEquals(4, listMascotas.size());
		
		
		Assert.assertNotNull(listMascotas.get(0).getOwner());
		Assert.assertNotNull(listMascotas.get(1).getOwner());
		Assert.assertNotNull(listMascotas.get(2).getOwner());
		
		Assert.assertNotNull(listPersonas.get(0).getMascotas());
		Assert.assertNotNull(listPersonas.get(1).getMascotas());
		Assert.assertNotNull(listPersonas.get(2).getMascotas());
	
		Assert.assertEquals("Rex", listMascotas.get(0).getName());
		Assert.assertEquals("Jose", listMascotas.get(0).getOwner().getName());
		Assert.assertEquals("Garfield", listMascotas.get(1).getName());
		
		
		
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
		
		dbconnector.insert(person1,mascota1);
		dbconnector.insert(person2,mascota2);
		dbconnector.insert(person3,mascota3);
		dbconnector.insert(person4,mascota4);
		
		HashSet<Mascota> listaCompleta =  dbconnector.selectAllMascota();
		
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
		
		dbconnector.insert(person1,mascota1);
		dbconnector.insert(person2,mascota2);
		dbconnector.insert(person3,mascota3);
		dbconnector.insert(person2,mascota4);
		
		HashSet<Person> listaCompleta =  dbconnector.selectAllPerson();
		
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
				
			dbconnector.insert(person1,mascota1);
			dbconnector.insert(person2,mascota2);
			dbconnector.insert(person3,mascota3);
			dbconnector.insert(person4,mascota4);
		
		HashSet<Person> list = dbconnector.findLikeByOwnerName(ownerName);
		
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
		
		dbconnector.insert(person1,mascota1);
		dbconnector.insert(person1,mascota6);
		dbconnector.insert(person2,mascota2);
		dbconnector.insert(person2,mascota4);
		dbconnector.insert(person2,mascota5);
		dbconnector.insert(person3,mascota3);

		
		HashSet<Mascota> listaPerson2 =  dbconnector.findMascota(person2.getId());
		HashSet<Mascota> listaPerson1 =  dbconnector.findMascota(person1.getId());
		
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

		
		dbconnector.insert(person1,mascota1);
		dbconnector.insert(person2,mascota2);
		dbconnector.insert(person3,mascota3);

		mascota2.setName("SuperGarfield");
		mascota2.setHeight(10);
		mascota2.setWeight(8);
		mascota2.setLength(2); 
		mascota2.setOwner(null);
		mascota2.setTypeClass("ave"); 
		
		
		
			dbconnector.update(mascota2);
			
			dbconnector.connect();
				Mascota mascotaRecovered = dbconnector.find(Mascota.class,mascota2.getId());
			dbconnector.close();
			
		Assert.assertEquals("SuperGarfield", mascotaRecovered.getName());
		Assert.assertEquals(10, mascotaRecovered.getHeight(),0);
		Assert.assertEquals(8, mascotaRecovered.getWeight(),0);
		Assert.assertEquals(2, mascotaRecovered.getLength(),0);
		Assert.assertEquals("ave", mascotaRecovered.getTypeClass());
		Assert.assertNotNull(mascotaRecovered.getOwner());
		
	}
	
	@Test
	public void testUpdatePerson(){
		
		Person person1 = getMockPerson("Jose", "García");
		Person person2 = getMockPerson("Juan", "Perez");
		Person person3 = getMockPerson("Marc", "Bert");
		
		Mascota mascota1 = getMockMascota("Rex", "canido");
		Mascota mascota2 = getMockMascota("Garfield", "felino");
		Mascota mascota3 = getMockMascota("Piolin", "ave");

		
		dbconnector.insert(person1,mascota1);
		dbconnector.insert(person2,mascota2);
		dbconnector.insert(person3,mascota3);

		person3.setName("Marquitos");
		
			dbconnector.update(person3);
			
			dbconnector.connect();
			Person personRecovered = dbconnector.find(Person.class,person3.getId());
			dbconnector.close();
			
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
		
		dbconnector.insert(person1,mascota1);
		dbconnector.insert(person2,mascota2);
		dbconnector.insert(person3,mascota3);
		dbconnector.insert(person3,mascota4);
		dbconnector.insert(person3,mascota5);
		dbconnector.insert(person3,mascota6);

		
			dbconnector.remove(person3);
			
			dbconnector.connect();
			ArrayList<Person> allPersons = dbconnector.selectAll(Person.class);
			ArrayList<Mascota> allMascotas = dbconnector.selectAll(Mascota.class);
			dbconnector.close();
			
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