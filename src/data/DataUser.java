package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Role;
import entities.User;

public class DataUser {

	public User getByUser(User user) {
		User u = null;
		Role r = null;
		DataRole dr = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idUser, idRol, surname, name, birthDate, email, adress, phoneNumber FROM user WHERE email=? and password=?"
					);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				u = new User();
				r = new Role();
				u.setIdUser(rs.getInt("idUser"));
				u.setName(rs.getString("name"));
				u.setSurname(rs.getString("surname"));
				u.setEmail(rs.getString("email"));
				u.setBirthDate(rs.getString("birthDate"));
				u.setAdress(rs.getString("adress"));
				u.setPhoneNumber(rs.getString("phoneNumber"));
				r.setIdRole(rs.getInt("idRole"));
				u.setRole(dr.getOne(r));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}

	public User getById(User user) {
		User u = null;
		Role r = null;
		DataRole dr = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM user WHERE idUser=?");
			stmt.setInt(1, user.getIdUser());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				u = new User();
				r = new Role();
				u.setIdUser(rs.getInt("idUser"));
				u.setName(rs.getString("name"));
				u.setSurname(rs.getString("surname"));
				u.setEmail(rs.getString("email"));
				u.setBirthDate(rs.getString("birthDate"));
				u.setAdress(rs.getString("adress"));
				u.setPhoneNumber(rs.getString("phoneNumber"));
				r.setIdRole(rs.getInt("idRole"));
				u.setRole(dr.getOne(r));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}
	
}
	