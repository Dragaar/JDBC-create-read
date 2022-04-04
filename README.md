� �������� ���� ������ ������������ ����������� ��.
�� �������� ��� �������:
```
users (id, login)
teams (id, name)
users_teams (user_id, team_id)
```
���������� ������� �� ������ ����� ��������� ���������� (��. ��� ������ Demo)
� ����� ������� ������� sql � ��������� � ��� ������ �������� ���� ������ `db-create.sql`
������� � ����������� ���� ����� �������, ����� ��� ������� ������ Demo 
������������ ��������������� ����������������.
--------------------------------------------------
�����: `com.epam.rd.java.basic.topic07.task01`
������: 
Demo - �������� ������������ �����������, �������� ��� ���������.
��������� ������ ��. �����������.
--------------------------------------------------
���������� ������ Demo:
```
public class Demo {

	public static void main(String[] args) throws DBException {
		// users  ==> [ivanov]
		// teams  ==> [teamA]
		DBManager dbManager = DBManager.getInstance();

		dbManager.insertUser(User.createUser("petrov"));
		dbManager.insertUser(User.createUser("obama"));
		System.out.println(dbManager.findAllUsers());
		// users  ==> [ivanov, petrov, obama]

		dbManager.insertTeam(Team.createTeam("teamB"));
		dbManager.insertTeam(Team.createTeam("teamC"));
		System.out.println(dbManager.findAllTeams());
		// teams ==> [teamA, teamB, teamC]
	}
}
```
(1) ����� DBManager#`insertUser` ������ �������������� ���� id ������� User.

(2) ����� DBManager#`findAllUsers` ���������� ������ `java.util.List<User>`.

(3) ����� DBManager#`insertTeam` ������ �������������� ���� id ������� Team.

(4) ����� DBManager#`findAllTeams` ���������� ������ `java.util.List<Team>`.

##### ���������.

����� User ������ ���������:
- ����� `getLogin()`, ������� ���������� ����� ������������;
- ����� `toString()`, ������� ���������� ����� ������������;
- ���������� ������ `equals(Object obj)`, �������� ������� ��� ������� User 
����� ����� � ������ �����, ����� ��� ����� ���� �����;
- ����������� ����� `createUser(String login)`, ������� ������� ������ User �� 
������ (������������� ����� 0).

����� Team ������ ���������:
- ����� `getName()`, ������� ���������� �������� ������;
- ����� `toString()`, ������� ���������� �������� ������;
- ���������� ������ `equals(Object obj)`, �������� ������� ��� ������� Team 
����� ����� � ������ �����, ����� ��� ����� ���� ��������.
- ����������� ����� `createTeam(String name)`, ������� ������� ������ Team �� 
����� (������������� ����� 0).
