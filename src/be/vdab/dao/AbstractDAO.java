package be.vdab.dao;
import javax.sql.DataSource;

abstract class AbstractDAO {
	protected DataSource dataSource;
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

}
