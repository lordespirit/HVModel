package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBConnector;
import model.Mascota;
import model.Person;

public class TestRemove {

	DBConnector dbconnector; 

	@Before
	public void init(){
		dbconnector = new DBConnector(); 	
		dbconnector.connect();
		dbconnector.deleteAll(Person.class);
		dbconnector.close();
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
