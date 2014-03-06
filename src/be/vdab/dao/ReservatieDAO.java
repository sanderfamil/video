package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import be.vdab.entities.Reservatie;

public class ReservatieDAO extends AbstractDAO {
	private static final String NEW_RESERVATIE = "Insert into reservaties(klantnr, filmnr, reservatiedatum) values(?,?,{fn curdate()})";
	private static final String FILM_RESERVEREN = "UPDATE films set gereserveerd= gereserveerd+1 where filmnr=?";
	

	
	public void newReservatie(Reservatie reservatie){
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statementReservatie = connection.prepareStatement(NEW_RESERVATIE);
				PreparedStatement statementFilm = connection.prepareStatement(FILM_RESERVEREN))
				{
				connection.setAutoCommit(false);
			statementReservatie.setLong(1, reservatie.getKlantNr());
			statementReservatie.setLong(2, reservatie.getFilmNr());
			statementFilm.setLong(1, reservatie.getFilmNr());
			statementReservatie.executeUpdate();
			statementFilm.executeUpdate();
			connection.commit();}

					catch(SQLException ex){
			throw new DAOException();
		}
	}

	
	
	


}
