package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import be.vdab.entities.Film;

public class FilmDAO extends AbstractDAO {

	private static final String SELECT_FILM = "select filmnr, genrenr, titel, voorraad, gereserveerd, prijs from films ";
	private static final String READ_FILM = SELECT_FILM + "where filmnr=?";
	private static final String FILM_BY_GENRE = SELECT_FILM + "where genrenr=? order by titel";
	

	public Iterable<Film> findAllFilm() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_FILM)) {
			List<Film> films = new ArrayList<>();
			while (resultSet.next()) {
				films.add(resultSetRijNaarFilm(resultSet));
			}
			return films;
		} catch (SQLException ex) {
			throw new DAOException();
		}
	}

	public Film readFilm(long nummer) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(READ_FILM)) {
			statement.setLong(1, nummer);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetRijNaarFilm(resultSet);
				}
				return null;
			}
		} catch (SQLException ex) {
			throw new DAOException();
		}
	}

	public Iterable<Film> findByGenre(long nummer) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(FILM_BY_GENRE)) {
			statement.setLong(1, nummer);
			List<Film> films = new LinkedList<>();
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					films.add(resultSetRijNaarFilm(resultSet));
				}
				return films;

			}
		} catch (SQLException ex) {
			throw new DAOException();
		}

	}


	private Film resultSetRijNaarFilm(ResultSet resultSet) throws SQLException {
		return new Film(resultSet.getLong("filmnr"),
				resultSet.getLong("genrenr"), resultSet.getString("titel"),
				resultSet.getInt("voorraad"), resultSet.getInt("gereserveerd"),
				resultSet.getBigDecimal("prijs"));

	}

}
