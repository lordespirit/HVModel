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

public class TestInsert {
	DBConnector dbConnector;
	DBAccess dbAccess; 

	@Before
	public void init(){
		dbAccess = new DBAccess(); 	
		dbConnector = new DBConnector();
		dbConnector.connect();
		dbConnector.deleteAll(Person.class);
		dbConnector.close();
	}
	
	@Test(expected = RuntimeException.class)
	public void insertMascotaAndPersonAllNull(){
		
		Person person1 = null;
		Mascota mascota1 = null;
		
		dbAccess.insert(person1,mascota1);
		
	}
	
	@Test(expected = RuntimeException.class)
	public void insertMascotaNotNullAndPersonNull(){
		
		Person person1 = null;
		Mascota mascota1 = getMockMascota("Rex", "canido");
		
		dbAccess.insert(person1,mascota1);

	}
	
	
	@Test
	public void insertMascotaAndPersonAllNotNull(){
		
		Person person1 = getMockPerson("Jose", "García");
		Mascota mascota1 = getMockMascota("Rex", "canido");
		
		dbAccess.insert(person1,mascota1);
		
		ArrayList<model.Mascota> mascotasList = new ArrayList<model.Mascota>(dbAccess.selectAllMascota());
		ArrayList<model.Person> personList = new ArrayList<model.Person>(dbAccess.selectAllPerson());
		
		Assert.assertEquals(1, mascotasList.size());
		Assert.assertEquals("Rex", mascotasList.get(0).getName());
		Assert.assertEquals("Jose", mascotasList.get(0).getOwner().getName());
		Assert.assertEquals(1, personList.size());
		Assert.assertEquals("Jose", personList.get(0).getName());

	}
	
	@Test
	public void insertMascotaNullAndPersonNotNull(){
		
		Person person1 = getMockPerson("Jose", "García");
		Mascota mascota1 = null;
		
		dbAccess.insert(person1,mascota1);
		
		ArrayList<model.Mascota> mascotasList = new ArrayList<model.Mascota>(dbAccess.selectAllMascota());
		ArrayList<model.Person> personList = new ArrayList<model.Person>(dbAccess.selectAllPerson());
		
		Assert.assertEquals(0, mascotasList.size());
		Assert.assertEquals(1, personList.size());
		Assert.assertEquals("Jose", personList.get(0).getName());

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
