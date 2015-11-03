package ua.nure.your_last_name.SummaryTask4.db;

import java.sql.Connection;
import java.sql.DriverManager;
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

import org.apache.log4j.Logger;

import ua.nure.your_last_name.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.your_last_name.SummaryTask4.db.entity.Category;
import ua.nure.your_last_name.SummaryTask4.db.entity.MenuItem;
import ua.nure.your_last_name.SummaryTask4.db.entity.Order;
import ua.nure.your_last_name.SummaryTask4.db.entity.User;
import ua.nure.your_last_name.SummaryTask4.exception.DBException;
import ua.nure.your_last_name.SummaryTask4.exception.Messages;

/**
 * DB manager. Works with Apache Derby DB. Only the required DAO methods are
 * defined!
 * 
 * @author D.Kolesnikov
 * 
 */
public final class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static DBManager instance;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// ST4DB - the name of data source
			ds = (DataSource) envContext.lookup("jdbc/ST4DB");
			LOG.trace("Data source ==> " + ds);
		} catch (NamingException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	private DataSource ds;

	// //////////////////////////////////////////////////////////
	// SQL queries
	// //////////////////////////////////////////////////////////

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

	private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";

	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

	private static final String SQL_FIND_ALL_MENU_ITEMS = "SELECT * FROM menu";

	private static final String SQL_FIND_ORDERS_BY_STATUS_AND_USER = "SELECT * FROM orders WHERE status_id=? AND user_id=?";

	private static final String SQL_FIND_MENU_ITEMS_BY_ORDER = "select * from menu where id in (select menu_id from orders_menu where order_id=?)";

	private static final String SQL_FIND_ORDERS_BY_STATUS = "SELECT * FROM orders WHERE status_id=?";

	private static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";

	private static final String SQL_UPDATE_USER = "UPDATE users SET password=?, first_name=?, last_name=?"
			+ "	WHERE id=?";

	private static final String SQL_GET_USER_ORDER_BEANS = "SELECT o.id, u.first_name, u.last_name, o.bill, s.name"
			+ "	FROM users u, orders o, statuses s"
			+ "	WHERE o.user_id=u.id AND o.status_id=s.id";

	/**
	 * Returns a DB connection from the Pool Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in
	 * your WEB_APP_ROOT/META-INF/context.xml file.
	 * 
	 * @return DB connection.
	 */
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}

	// //////////////////////////////////////////////////////////s
	// Methods to obtain beans
	// //////////////////////////////////////////////////////////
	/**
	 * Returns all categories.
	 * 
	 * @return List of category entities.
	 */
	public List<UserOrderBean> getUserOrderBeans() throws DBException {
		List<UserOrderBean> orderUserBeanList = new ArrayList<UserOrderBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_GET_USER_ORDER_BEANS);
			while (rs.next()) {
				orderUserBeanList.add(extractUserOrderBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return orderUserBeanList;
	}

	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////

	/**
	 * Returns all categories.
	 * 
	 * @return List of category entities.
	 */
	public List<Category> findCategories() throws DBException {
		List<Category> categoriesList = new ArrayList<Category>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_CATEGORIES);
			while (rs.next()) {
				categoriesList.add(extractCategory(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return categoriesList;
	}

	/**
	 * Returns all menu items.
	 * 
	 * @return List of menu item entities.
	 */
	public List<MenuItem> findMenuItems() throws DBException {
		List<MenuItem> menuItemsList = new ArrayList<MenuItem>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_MENU_ITEMS);
			while (rs.next()) {
				menuItemsList.add(extractMenuItem(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return menuItemsList;
	}

	/**
	 * Returns menu items of the given order.
	 * 
	 * @param order
	 *            Order entity.
	 * @return List of menu item entities.
	 */
	public List<MenuItem> findMenuItems(Order order) throws DBException {
		List<MenuItem> menuItemsList = new ArrayList<MenuItem>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_MENU_ITEMS_BY_ORDER);
			pstmt.setLong(1, order.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				menuItemsList.add(extractMenuItem(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return menuItemsList;
	}

	/**
	 * Returns menu items with given identifiers.
	 * 
	 * @param ids
	 *            Identifiers of menu items.
	 * @return List of menu item entities.
	 */
	public List<MenuItem> findMenuItems(String[] ids) throws DBException {
		List<MenuItem> menuItemsList = new ArrayList<MenuItem>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();

			// create SQL query like "... id IN (1, 2, 7)"
			StringBuilder query = new StringBuilder(
					"SELECT * FROM menu WHERE id IN (");
			for (String idAsString : ids) {
				query.append(idAsString).append(',');
			}
			query.deleteCharAt(query.length() - 1);
			query.append(')');

			stmt = con.createStatement();
			rs = stmt.executeQuery(query.toString());
			while (rs.next()) {
				menuItemsList.add(extractMenuItem(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(
					Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_IDENTIFIERS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return menuItemsList;
	}

	/**
	 * Returns all orders.
	 * 
	 * @return List of order entities.
	 */
	public List<Order> findOrders() throws DBException {
		List<Order> ordersList = new ArrayList<Order>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_ORDERS);
			while (rs.next()) {
				ordersList.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return ordersList;
	}

	/**
	 * Returns orders with the given status.
	 * 
	 * @param statusId
	 *            Status identifier.
	 * @return List of order entities.
	 */
	public List<Order> findOrders(int statusId) throws DBException {
		List<Order> ordersList = new ArrayList<Order>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ORDERS_BY_STATUS);
			pstmt.setInt(1, statusId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordersList.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(
					Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return ordersList;
	}

	/**
	 * Returns orders with given identifiers.
	 * 
	 * @param ids
	 *            Orders identifiers.
	 * @return List of order entities.
	 */
	public List<Order> findOrders(String[] ids) throws DBException {
		List<Order> ordersList = new ArrayList<Order>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();

			// create SQL query like "... id IN (1, 2, 7)"
			StringBuilder query = new StringBuilder(
					"SELECT * FROM orders WHERE id IN (");
			for (String idAsString : ids) {
				query.append(idAsString).append(',');
			}
			query.deleteCharAt(query.length() - 1);
			query.append(')');

			stmt = con.createStatement();
			rs = stmt.executeQuery(query.toString());
			while (rs.next()) {
				ordersList.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(
					Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_IDENTIFIERS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return ordersList;
	}

	/**
	 * Returns orders of the given user and status
	 * 
	 * @param user
	 *            User entity.
	 * @param statusId
	 *            Status identifier.
	 * @return List of order entities.
	 * @throws DBException
	 */
	public List<Order> findOrders(User user, int statusId) throws DBException {
		List<Order> ordersList = new ArrayList<Order>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ORDERS_BY_STATUS_AND_USER);
			pstmt.setInt(1, statusId);
			pstmt.setLong(2, user.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordersList.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(
					Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_USER_AND_STATUS_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return ordersList;
	}

	/**
	 * Returns a user with the given identifier.
	 * 
	 * @param id
	 *            User identifier.
	 * @return User entity.
	 * @throws DBException
	 */
	public User findUser(long id) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	/**
	 * Returns a user with the given login.
	 * 
	 * @param login
	 *            User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public User findUserByLogin(String login) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 * @throws DBException
	 */
	public void updateUser(User user) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateUser(con, user);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			close(con);
		}
	}

	// //////////////////////////////////////////////////////////
	// Entity access methods (for transactions)
	// //////////////////////////////////////////////////////////

	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 * @throws SQLException
	 */
	private void updateUser(Connection con, User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_USER);
			int k = 1;
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFirstName());
			pstmt.setString(k++, user.getLastName());
			pstmt.setLong(k, user.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	// //////////////////////////////////////////////////////////
	// DB util methods
	// //////////////////////////////////////////////////////////

	/**
	 * Closes a connection.
	 * 
	 * @param con
	 *            Connection to be closed.
	 */
	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Closes a statement object.
	 */
	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	/**
	 * Closes a result set object.
	 */
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * Closes resources.
	 */
	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	// //////////////////////////////////////////////////////////
	// Other methods
	// //////////////////////////////////////////////////////////
	/**
	 * Extracts a user order bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user order bean will be extracted.
	 * @return UserOrderBean object
	 */

	private UserOrderBean extractUserOrderBean(ResultSet rs)
			throws SQLException {
		UserOrderBean bean = new UserOrderBean();
		bean.setId(rs.getLong(Fields.USER_ORDER_BEAN_ORDER_ID));
		bean.setOrderBill(rs.getInt(Fields.USER_ORDER_BEAN_ORDER_BILL));
		bean.setUserFirstName(rs
				.getString(Fields.USER_ORDER_BEAN_USER_FIRST_NAME));
		bean.setUserLastName(rs
				.getString(Fields.USER_ORDER_BEAN_USER_LAST_NAME));
		bean.setStatusName(rs.getString(Fields.USER_ORDER_BEAN_STATUS_NAME));
		return bean;
	}

	/**
	 * Extracts a user entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user entity will be extracted.
	 * @return User entity
	 */
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
		user.setLastName(rs.getString(Fields.USER_LAST_NAME));
		user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
		return user;
	}

	/**
	 * Extracts an order entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which an order entity will be extracted.
	 * @return
	 */
	private Order extractOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong(Fields.ENTITY_ID));
		order.setBill(rs.getInt(Fields.ORDER_BILL));
		order.setUserId(rs.getLong(Fields.ORDER_USER_ID));
		order.setStatusId(rs.getInt(Fields.ORDER_STATUS_ID));
		return order;
	}

	/**
	 * Extracts a category entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a category entity will be extracted.
	 * @return Category entity.
	 */
	private Category extractCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getLong(Fields.ENTITY_ID));
		category.setName(rs.getString(Fields.CATEGORY_NAME));
		return category;
	}

	/**
	 * Extracts a menu item from the result set.
	 * 
	 * @param rs
	 *            Result set from which a menu item entity will be extracted.
	 * @return Menu item entity.
	 */
	private MenuItem extractMenuItem(ResultSet rs) throws SQLException {
		MenuItem menuItem = new MenuItem();
		menuItem.setId(rs.getLong(Fields.ENTITY_ID));
		menuItem.setName(rs.getString(Fields.MENU_ITEM_NAME));
		menuItem.setPrice(rs.getInt(Fields.MENU_ITEM_PRICE));
		menuItem.setCategoryId(rs.getLong(Fields.MENU_ITEM_CATEGORY_ID));
		return menuItem;
	}

	/**************** THIS METHOD IS NOT USED IN THE PROJECT *******/
	/**
	 * Returns a DB connection. This method is just for a example how to use the
	 * DriverManager to obtain a DB connection. It does not use a pool
	 * connections and not used in this project. It is preferable to use
	 * {@link #getConnection()} method instead.
	 * 
	 * @return A DB connection.
	 */
	public Connection getConnectionWithDriverManager() throws SQLException {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException ex) {
			LOG.error("Cannot obtain a connection", ex);
		}
		Connection connection = DriverManager
				.getConnection("jdbc:derby://localhost:1527/st4db;create=true;user=test;password=test");
		connection
				.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		connection.setAutoCommit(false);
		return connection;
	}
	/**************************************************************/

}