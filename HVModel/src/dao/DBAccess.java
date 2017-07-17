package dao;

import java.util.HashSet;

import model.Mascota;
import model.Person;

public class DBAccess implements HVServices {
	

	private DBConnector dbConnector;

	public DBAccess() {
		super();
		dbConnector = new DBConnector();
	}


	@Override
	public void insert(Person owner, Mascota mascota) {
		dbConnector.insert(owner, mascota);
	}

	@Override
	public void remove(Mascota mascota) {
		dbConnector.remove(mascota);
	}

	@Override
	public void remove(Person owner) {
		dbConnector.remove(owner);
	}

	@Override
	public void update(Person person) {
		dbConnector.update(person);
	}

	@Override
	public void update(Mascota mascota) {
		dbConnector.update(mascota);
	}

	@Override
	public HashSet<Person> findLikeByOwnerName(String strLike) {
		return dbConnector.findLikeByOwnerName(strLike);
	}

	@Override
	public HashSet<Mascota> findMascota(int idPerson) {
		return dbConnector.findMascota(idPerson);
	}

	@Override
	public HashSet<Person> selectAllPerson() {
		return dbConnector.selectAllPerson();
	}

	@Override
	public HashSet<Mascota> selectAllMascota() {
		return dbConnector.selectAllMascota();
	}
	

}
