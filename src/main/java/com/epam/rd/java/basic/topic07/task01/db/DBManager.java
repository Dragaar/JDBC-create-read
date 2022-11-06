package com.epam.rd.java.basic.topic07.task01.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.epam.rd.java.basic.topic07.task01.db.entity.*;

import static com.epam.rd.java.basic.topic07.task01.db.Fields.*;

public class DBManager {
	private final static  DBManager INSTANCE = new DBManager();
	//-------------------
	InputStream appConfigPath = getClass().getResourceAsStream("app.properties");
	Properties appProps = new Properties();
	//-------------------
	private DBManager(){
		try {
			appProps.load(new FileInputStream("app.properties")); //загрузка файлу конфігурації app.properties
		} catch (IOException e){ e.printStackTrace();}
	}
	public static synchronized DBManager getInstance() {return INSTANCE;}

	public List<User> findAllUsers() throws DBException {
		List<User> users = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(appProps.getProperty("connection.url"));
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS))
		{
			while (rs.next()){
			User user = User.createUser(rs.getString(USER_LOGIN));
			user.setId(rs.getInt(USER_ID));
				users.add(user);
			}

		} catch (SQLException e){
			e.printStackTrace();
		}
		return users;
	}

	public boolean insertUser(User user) throws DBException {
		try {
			Connection con = DriverManager.getConnection(appProps.getProperty("connection.url"));
			PreparedStatement stmt = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getLogin());

			int countSuccessfulInsertions = stmt.executeUpdate();
			if(countSuccessfulInsertions >0){
				try(ResultSet rs = stmt.getGeneratedKeys()) {
					if(rs.next()) {
						user.setId(rs.getInt(1));
					}
				}
			}
			stmt.close();
			con.close();
			if(countSuccessfulInsertions >0){return true; } else return false;
		} catch (SQLException e){
			e.printStackTrace();
			throw new DBException();
		}
	}

	public User getUser(String login) throws DBException {
		User user = null;
		try {
			Connection con = DriverManager.getConnection(appProps.getProperty("connection.url"));
			PreparedStatement stmt = con.prepareStatement(SELECT_USER);
			stmt.setString(1, login);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()){
				user = User.createUser(rs.getString(USER_LOGIN));
				user.setId(rs.getInt(USER_ID));
			}
			stmt.close();
			con.close();
			return user;
		} catch (SQLException e){
			e.printStackTrace();
			throw new DBException();
		}
	}

	public Team getTeam(String name) throws DBException {
		Team team = null;
		try {
			Connection con = DriverManager.getConnection(appProps.getProperty("connection.url"));
			PreparedStatement stmt = con.prepareStatement(SELECT_TEAM);
			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()){
				team = Team.createTeam(rs.getString(TEAM_NAME));
				team.setId(rs.getInt(TEAM_ID));
			}
			stmt.close();
			con.close();
			return team;
		} catch (SQLException e){
			e.printStackTrace();
			throw new DBException();
		}
	}

	public List<Team> findAllTeams() throws DBException {
		List<Team> teams = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(appProps.getProperty("connection.url"));
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_TEAMS))
		{
			while (rs.next()){
				Team team = Team.createTeam(rs.getString(TEAM_NAME));
				team.setId(rs.getInt(TEAM_ID));
				teams.add(team);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return teams;
	}

	public boolean insertTeam(Team team) throws DBException {
		try {
			Connection con = DriverManager.getConnection(appProps.getProperty("connection.url"));
			PreparedStatement stmt = con.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, team.getName());

			int countSuccessfulInsertions = stmt.executeUpdate();
			if(countSuccessfulInsertions >0){
				try(ResultSet rs = stmt.getGeneratedKeys()) {
					if(rs.next()) {
						team.setId(rs.getInt(1));
					}
				}
			}
			stmt.close();
			con.close();
			if(countSuccessfulInsertions >0){return true; } else return false;
		} catch (SQLException e){
			e.printStackTrace();
			throw new DBException();
		}
	}

}
