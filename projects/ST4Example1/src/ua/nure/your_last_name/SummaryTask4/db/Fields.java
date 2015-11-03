package ua.nure.your_last_name.SummaryTask4.db;

/**
 * Holder for fields names of DB tables and beans.
 * 
 * @author D.Kolesnikov
 * 
 */
public final class Fields {
	
	// entities
	public static final String ENTITY_ID = "id";
	
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_ROLE_ID = "role_id";
	
	public static final String ORDER_BILL = "bill";
	public static final String ORDER_USER_ID = "user_id";
	public static final String ORDER_STATUS_ID= "status_id";

	public static final String CATEGORY_NAME = "name";
	
	public static final String MENU_ITEM_PRICE = "price";
	public static final String MENU_ITEM_NAME = "name";
	public static final String MENU_ITEM_CATEGORY_ID = "category_id";	

	// beans
	public static final String USER_ORDER_BEAN_ORDER_ID = "id";	
	public static final String USER_ORDER_BEAN_USER_FIRST_NAME = "first_name";	
	public static final String USER_ORDER_BEAN_USER_LAST_NAME = "last_name";	
	public static final String USER_ORDER_BEAN_ORDER_BILL = "bill";	
	public static final String USER_ORDER_BEAN_STATUS_NAME = "name";
	
}