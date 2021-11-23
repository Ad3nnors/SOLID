package br.ufscar.dc.dsw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD {
	
	public static void main(String[] args) {
		try {
	
			/* Setup para uso do banco de dados MySQL */
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		    String url = "jdbc:mysql://localhost:3306/SistemaAgendamento";
			Connection con = (Connection) DriverManager.getConnection(url, "root", "root"); //mudar a senha caso necessario
		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from CLIENTES");
			while (rs.next()) {
				System.out.print(rs.getLong("id"));
				System.out.print(", " + rs.getString("CPF"));
				System.out.print(", " + rs.getString("Email"));
				System.out.print(", " + rs.getString("Senha"));
				System.out.print(", " + rs.getString("Nome"));
				System.out.print(", " + rs.getString("Telefone"));
				System.out.print(", " + rs.getString("Sexo"));
				System.out.print(", " + rs.getInt("ano"));
				System.out.print("/" + rs.getInt("mes"));
				System.out.print("/" + rs.getInt("dia"));
				System.out.print("\n\n");
			}
			
			rs = stmt.executeQuery("select * from PROFISSIONAIS");
			while (rs.next()) {
				System.out.print(rs.getLong("id"));
				System.out.print(", " + rs.getString("CPF"));
				System.out.print(", " + rs.getString("Email"));
				System.out.print(", " + rs.getString("Senha"));
				System.out.print(", " + rs.getString("Nome"));
				System.out.print(", " + rs.getString("Area"));
				System.out.print(", " + rs.getString("Especialidade"));
				System.out.print("\n\n");
			}
			
			rs = stmt.executeQuery("select * from CONSULTAS");
			while (rs.next()) {
				System.out.print(rs.getLong("id"));
				System.out.print(", " + rs.getLong("id_C"));
				System.out.print(", " + rs.getLong("id_P"));
				System.out.print(", " + rs.getInt("ano"));
				System.out.print("/" + rs.getInt("mes"));
				System.out.print("/" + rs.getInt("dia"));
				System.out.print(" " + rs.getInt("hora") + ":00");
				System.out.print("\n\n");
			}
			
			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe do driver de conexão não foi encontrada!");
		} catch (SQLException e) {
			System.out.println("O comando SQL não pode ser executado!\n ----->\n" + e + "\n <-----");
		}
	}
}
