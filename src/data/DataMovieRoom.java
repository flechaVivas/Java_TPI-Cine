package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import entities.MovieRoom;

public class DataMovieRoom {
	
	public MovieRoom getOne(MovieRoom m){
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MovieRoom mr = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM movieroom where roomNumber = ? ");
			stmt.setInt(1, m.getRoomNumber());
			rs = stmt.executeQuery();
			if(rs != null && rs.next() ) {
				mr = new MovieRoom();
				mr.setRoomNumber(rs.getInt("roomNumber"));
				mr.setCapacity(rs.getInt("capacity"));
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mr;
	}
	
	public LinkedList<MovieRoom> list(){
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<MovieRoom> mrs = new LinkedList<MovieRoom>();
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("SELECT * FROM movieRoom");
			while(rs.next()) {
				MovieRoom mr = new MovieRoom();
				mr.setRoomNumber(rs.getInt("roomNumber"));
				mr.setCapacity(rs.getInt("capacity"));
				mrs.add(mr);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mrs;
	}
	
	public MovieRoom update(MovieRoom mr) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE movieroom set capacity = ? where = roomNumber ");			
			stmt.setInt(1, mr.getCapacity());
			stmt.setInt(2, mr.getRoomNumber());
			stmt.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return mr;
	}
	
	public MovieRoom CreateMovieRoom(MovieRoom mr){
		PreparedStatement stmt = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("INSERT INTO movieroom(capacity) values(?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, mr.getCapacity());
			stmt.executeUpdate();
			ResultSet keyResultSet = stmt.getGeneratedKeys();
			if(keyResultSet != null && keyResultSet.next()) {
				mr.setRoomNumber(keyResultSet.getInt(1));
			}
		}catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return mr;
	}
	
	public void deleteMovieRoom(MovieRoom mr) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM movieroom WHERE roomNumber ");
			stmt.setInt(1, mr.getRoomNumber());
			stmt.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
}
