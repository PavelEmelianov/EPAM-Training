package ua.nure.your_last_name.Practice9.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.nure.your_last_name.Practice9.db.bean.UserBean;
import ua.nure.your_last_name.Practice9.db.entity.Role;
import ua.nure.your_last_name.Practice9.db.entity.User;

public class DBManager {
	
	//================SQL queries
		
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	
	private static final String SQL_FIND_USERS_BY_ROLE_NAME = "SELECT u.* FROM users u, roles r WHERE u.role_id=r.id AND r.name=?";
	
	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	private static final String SQL_FIND_ROLE_BY_USER = "SELECT * FROM roles WHERE id=?";

	private static final String SQL_FIND_ROLE_BY_NAME = "SELECT * FROM roles WHERE name=?";

	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";

	private static final String SQL_DELETE_USER = "DELETE FROM users WHERE login=?";

	private static final String SQL_UPDATE_USER = "UPDATE users SET password=?, full_name=?, email=?, role_id=? WHERE login=?";

	private static final String SQL_FIND_ALL_USERBEANS = "SELECT u.*, r.name FROM users u, roles r WHERE u.role_id=r.id";

	private static final String SQL_FIND_USERBEANS_BY_ROLE = "SELECT u.*, r.name FROM users u, roles r WHERE u.role_id=r.id AND r.name=?";
	
	//================SINGLETON
	
	private static DBManager instance;
	
	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}	
	
	/**
	 * Obtains DS object by name from JNDI (see configuration file /META-INF/context.xml) 
	 */
	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/p9db");
		} catch (NamingException ex) {
			throw new DBException("Cannot obtain a data source", ex);
		}
	}
	
	//================Data Source object (only one object of DS will be created)
	
	private DataSource ds;

	/**
	 * Obtains connection.
	 */
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}	
	
	//================Business logic methods
	
	public User findUser(String login) throws DBException {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot obtain user by login " + login, ex);
		} finally {
			close(rs, pstmt, con);
		}
		return user;
	}

	public List<User> findUsersByRoleName(String roleName) throws DBException {
		List<User> users = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USERS_BY_ROLE_NAME);
			pstmt.setString(1, roleName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot obtain users by role name " + roleName, ex);
		} finally {
			close(rs, pstmt, con);
		}
		return users;
	}
	
	public List<User> findAllUsers() throws DBException {
		List<User> users = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot obtain user beans", ex);
		} finally {
			close(rs, stmt, con);
		}
		return users;
	}

	public List<UserBean> findAllUserBeans() throws DBException {
		List<UserBean> users = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USERBEANS);
			while (rs.next()) {
				users.add(extractUserBean(rs));
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot obtain users", ex);
		} finally {
			close(rs, stmt, con);
		}
		return users;
	}

	public List<UserBean> findUserBeansByRole(String roleName) throws DBException {
		List<UserBean> users = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USERBEANS_BY_ROLE);
			pstmt.setString(1, roleName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(extractUserBean(rs));
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot obtain user beans", ex);
		} finally {
			close(rs, pstmt, con);
		}
		return users;
	}
	
	public Role findRoleByUser(User user) throws DBException {
		Role role = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ROLE_BY_USER);
			pstmt.setInt(1, user.getRoleId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				role = extractRole(rs);
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot obtain role by user", ex);
		} finally {
			close(rs, pstmt, con);
		}
		return role;
	}
	
	public Role findRoleByName(String roleName) throws DBException {
		Role role = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ROLE_BY_NAME);
			pstmt.setString(1, roleName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 role = extractRole(rs);				 
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot obtain role id by role name", ex);
		} finally {
			close(rs, pstmt, con);
		}
		return role;
	}

	public boolean insertUser(User user) throws DBException {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_USER);
			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFullName());
			pstmt.setString(k++, user.getEmail());
			pstmt.setInt(k++, user.getRoleId());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}		
		} catch (SQLException ex) {
			throw new DBException("Cannot insert user", ex);
		} finally {
			close(rs, pstmt, con);
		}
		return result;		
	}
	
	public boolean deleteUser(String login) throws DBException {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_USER);
			pstmt.setString(1, login);
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}		
		} catch (SQLException ex) {
			throw new DBException("Cannot insert user", ex);
		} finally {
			close(rs, pstmt, con);
		}
		return result;		
	}
	
	public boolean updateUser(User user) throws DBException {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_USER);
			int k = 1;
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFullName());
			pstmt.setString(k++, user.getEmail());
			pstmt.setInt(k++, user.getRoleId());
			pstmt.setString(k++, user.getLogin());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}		
		} catch (SQLException ex) {
			throw new DBException("Cannot update user", ex);
		} finally {
			close(rs, pstmt, con);
		}
		return result;		
	}
	
	//=================== Utils methods

	// NOT USED
	@SuppressWarnings("unused")
	private void rollback(Connection con) {
		try {
			if (con != null) {
				con.rollback();
			}
		} catch (SQLException ex) {
			System.out.println("Cannot rollback connection");
		}
	}

	private void close(ResultSet rs, Statement stmt, Connection con) {
		close(rs);
		close(stmt);
		close(con);
	}
	
	private void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			System.out.println("Cannot close a connection");
		}
	}
	private void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException ex) {
			System.out.println("Cannot close a statement");
		}
	}
	
	private void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			System.out.println("Cannot close a result set");
		}
	}	

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setEmail(rs.getString("email"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setFullName(rs.getString("full_name"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}
	
	private UserBean extractUserBean(ResultSet rs) throws SQLException {
		UserBean userBean = new UserBean();
		userBean.setEmail(rs.getString("email"));
		userBean.setLogin(rs.getString("login"));
		userBean.setPassword(rs.getString("password"));
		userBean.setFullName(rs.getString("full_name"));
		userBean.setRoleId(rs.getInt("role_id"));
		userBean.setRoleName(rs.getString("name"));
		return userBean;
	}
	
	private Role extractRole(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setId(rs.getInt("id"));
		role.setName(rs.getString("name"));
		return role;
	}

}