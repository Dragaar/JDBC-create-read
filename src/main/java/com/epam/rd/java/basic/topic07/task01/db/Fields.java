package com.epam.rd.java.basic.topic07.task01.db;

public abstract class Fields {

	public static final String USER_ID = "id";

	public static final String USER_LOGIN = "login";

	public static final String TEAM_ID = "id";

	public static final String TEAM_NAME = "name";

	//-----------------------------
	public static final String SELECT_ALL_USERS = "SELECT * FROM users" ;
	public static final String SELECT_USER = "SELECT * FROM users WHERE login LIKE \"?\"" ;

	public static final String INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?)" ;
	//-----------------------------
	public static final String SELECT_ALL_TEAMS= "SELECT * FROM teams";
	public static final String SELECT_TEAM = "SELECT * FROM teams WHERE name LIKE \"?\"" ;

	public static final String INSERT_TEAM = "INSERT INTO teams VALUES (DEFAULT, ?)" ;

}
