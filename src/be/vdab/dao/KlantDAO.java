package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Klant;

public class KlantDAO extends AbstractDAO {
	
	private static final String SELECT_KLANT = "Select klantnr, familienaam, voornaam, straatnummer, postcode, gemeente from klanten ";
	private static final String READ_KLANT = SELECT_KLANT+"where klantnr=?";
	private static final String FIND_KLANT = SELECT_KLANT+"where familienaam like ? order by voornaam";
	

	public Klant readKlant(long nummer) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(READ_KLANT)) {
			statement.setLong(1, nummer);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetRijNaarKlant(resultSet);
				}
				return null;
			}
		} catch (SQLException ex) {
			throw new DAOException();
		}
	}
	public Iterable<Klant> searchKlant(String input){
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_KLANT)){
			statement.setString(1, "%"+input+"%");
			List<Klant> klanten = new ArrayList<>();
			try(ResultSet resultSet=statement.executeQuery()){
					while(resultSet.next()){
					klanten.add(resultSetRijNaarKlant(resultSet));
				}
				return klanten;
			}
		} catch(SQLException ex){
			throw new DAOException();
		}
	}
	private Klant resultSetRijNaarKlant(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getLong("klantnr"),
				resultSet.getString("familienaam"), resultSet.getString("voornaam"),
				resultSet.getString("straatnummer"), resultSet.getInt("postcode"),
				resultSet.getString("gemeente"));

	}

}
