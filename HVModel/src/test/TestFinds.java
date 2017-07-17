package test;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBConnector;
import model.Mascota;
import model.Person;

public class TestFinds {
	DBConnector dbconnector; 


	@Before
	public void init(){
		dbconnector = new DBConnector(); 	
		dbconnector.connect();
		dbconnector.deleteAll(Person.class);
		dbconnector.close();
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