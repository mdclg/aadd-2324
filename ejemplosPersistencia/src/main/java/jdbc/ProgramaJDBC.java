package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import editorial.modelo.Revista;
import utils.PropertiesReader;

public class ProgramaJDBC {

	/**
	 * CREATE TABLE `REVISTA` ( `ISSN` varchar(255), `FECHAPUBLICACION` date DEFAULT
	 * NULL, `NUMPAGINAS` int DEFAULT NULL, `PRECIO` float DEFAULT NULL, `TITULO`
	 * varchar(255) DEFAULT NULL, PRIMARY KEY (`ISSN`) );
	 * 
	 */

	private static Connection getConnection() {
		try {
		PropertiesReader properties = new PropertiesReader("jdbc.properties");
		String host = properties.getProperty("host");
		String port = properties.getProperty("port");
		String user = properties.getProperty("user");
		String pass = properties.getProperty("pass");
		Properties connectionProps = new Properties();
		connectionProps.put("user", user);
		connectionProps.put("password", pass);
		Connection con;
		
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/aadd?serverTimezone="+ZoneId.systemDefault(), connectionProps);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	private static Revista buscarRevista(String issn) {
		
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM REVISTA WHERE ISSN = '" + issn + "'");
			if (rs.next()) {
				Revista r = new Revista();
				r.setIssn(rs.getString("ISSN"));
				r.setTitulo(rs.getString("TITULO"));
				r.setFechaPublicacion(rs.getDate("FECHAPUBLICACION"));
				r.setNumPaginas(rs.getInt("NUMPAGINAS"));
				r.setPrecio(rs.getFloat("PRECIO"));

				rs.close();
				stmt.close();
				con.close();
				return r;

			} else {
				rs.close();
				stmt.close();
				con.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void crearRevista(String issn, String titulo, Date fechaPublicacion, Integer numPaginas,
			Float precio) {

		
		try {
			Connection con = getConnection();			
			PreparedStatement stmt = con.prepareStatement(
					"INSERT into REVISTA (ISSN, TITULO, FECHAPUBLICACION, NUMPAGINAS, PRECIO) " + "values (?,?,?,?,?)");

			stmt.setString(1, issn);
			stmt.setString(2, titulo);
			stmt.setDate(3, new java.sql.Date(fechaPublicacion.getTime()));
			stmt.setInt(4, numPaginas);
			stmt.setFloat(5, precio);

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			crearRevista("2342463636", "Nature", new Date(), 250, 40.5f);
			/*
			Revista r = buscarRevista("2342463636");
			if(r != null) {
				System.out.println("Revista "+r.getTitulo()+" encontrada");
			}
			*/
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
