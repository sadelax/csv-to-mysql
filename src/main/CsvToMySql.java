package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class CsvToMySql {

	private static final String CSV_FILE = "C:\\Users\\sandr\\git\\csv-to-mysql\\src\\main\\resources\\iba-cocktails-ingredients-web.csv";
	private static final String TABLE_NAME = "iba_cocktails";

	public static void main(String[] args) throws IOException, SQLException {
		DataSource ds;
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");	// este es el driver de mysql
		bds.setUrl("jdbc:mysql://localhost/picky_cocktail_db");
		bds.setUsername("root");
		bds.setPassword("root");
		ds = bds;				// me interesa trabajar con datasource en verdad, bds solo para poder acceder a sus métodos

		Connection con = ds.getConnection();

		PreparedStatement preparedStatement = null;
		try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {

			// Descartar la primera línea del csv (encabezado)
			br.readLine();

			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String sql = "INSERT INTO " + TABLE_NAME + " (Category, Name, Ingredient_Direction, Quantity, Unit, Ingredient, Note) VALUES (?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, values[0]);
				preparedStatement.setString(2, values[1]);
				preparedStatement.setString(3, values[2]);
				preparedStatement.setString(4, values[3]);
				preparedStatement.setString(5, values[4]);
				preparedStatement.setString(6, values[5]);
				preparedStatement.setString(7, values[6]);
				preparedStatement.executeUpdate();
			}
			
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
