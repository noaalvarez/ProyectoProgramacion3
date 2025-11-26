package Ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConectBaseDatos {
	public static void connect(){
		//String url="jdbc:sqlite:ProyectoProgramBD.db";
		String url="jdbc:sqlite:BaseDatos.db";
		
		
		try (Connection conn=DriverManager.getConnection(url)){
			System.out.println("Connection to SQLite has been stablished");
			Statement stmnt=conn.createStatement();
			String sql="Select * from usuario";
			//PRUEBA PARA VER SI SE A CONECTADO BIEN A LA BASE DE DATOS
			ResultSet rs=stmnt.executeQuery(sql);
			while(rs.next()) {
				String Name=rs.getString("nombre");
				System.out.println("Nombre: "+ Name);
			}
			
		//	rs.close();
			conn.close();
			
		}catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	public static void main(String[] args) {
		connect();
	}
}