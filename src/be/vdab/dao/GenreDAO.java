package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Genre;

public class GenreDAO extends AbstractDAO {
	private static final String SELECT_GENRE = "select genrenr,naam from genres ";
	private static final String READ_GENRE = SELECT_GENRE + "where genrenr=?";

	public Iterable<Genre> findAllGenre() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_GENRE)) {
			List<Genre> genres = new ArrayList<>();
			while (resultSet.next()) {
				genres.add(resultSetRijNaarGenre(resultSet));
			}
			return genres;
		} catch (SQLException ex) {
			throw new DAOException();
		}

	}

	public Genre readGenre(long nummer) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(READ_GENRE)) {
			statement.setLong(1, nummer);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetRijNaarGenre(resultSet);
				}
				return null;
			}
		} catch (SQLException ex) {
			throw new DAOException();
		}

	}

	private Genre resultSetRijNaarGenre(ResultSet resultSet)
			throws SQLException {
		return new Genre(resultSet.getLong("genrenr"),
				resultSet.getString("naam"));
	}



}
