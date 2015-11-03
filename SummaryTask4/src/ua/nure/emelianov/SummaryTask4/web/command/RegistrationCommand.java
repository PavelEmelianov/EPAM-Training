package ua.nure.emelianov.SummaryTask4.web.command;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.emelianov.SummaryTask.security.Password;
import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.Validators;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * registration command,validates all field values and creates new user if validation was successful
 * @author Emelianov Pavel
 *
 */
public class RegistrationCommand extends Command {
	
	private static final String PASSWORD = "Password";
	private static final long serialVersionUID = 648955643838627779L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		String page = Path.ERROR_PAGE;
		DBManager db = DBManager.getInstance();

		List<Users> users = db.getUsers();

		List<String> logins = new ArrayList<String>(users.size());

		for (Users user : users) {
			logins.add(user.getLogin());
		}

		String login = request.getParameter("setLogin");
		
		if(Validators.validateOnlyLatinFields(login, "Login", 20)!=null){
			throw new AppException(Validators.validateOnlyLatinFields(login, "Login", 20));
		}
		
		if (logins.contains(login)) {
			throw new AppException("Login is already exists");
		}

		String password = request.getParameter("setPassword");
		
		if(Validators.validateOnlyLatinFields(password, PASSWORD, 20)!=null){
			throw new AppException(Validators.validateOnlyLatinFields(password, PASSWORD, 20));
		}

		String confirmPassword = request.getParameter("confirmPassword");
		
		Validators.validateFields(confirmPassword, "Confirm password", 20);
		
		if(Validators.validateOnlyLatinFields(confirmPassword, "Confirm password", 20)!=null){
			throw new AppException(Validators.validateOnlyLatinFields(confirmPassword, "Confirm password", 20));
		}
		
		if (!password.equals(confirmPassword)) {
			throw new AppException("Password mismatch");
		} 

		String 	fName =request.getParameter("setFName");
		
		if(Validators.validateFields(fName, PASSWORD, 20)!=null){
			throw new AppException(Validators.validateFields(fName, "First Name", 20));
		}

		String lName = request.getParameter("setLName");
		
		if(Validators.validateFields(lName, PASSWORD, 20)!=null){
			throw new AppException(Validators.validateFields(lName, "Last Name", 20));
		}

		String eMail = request.getParameter("setEMail");

		if (Validators.validateEMail(eMail,30)!=null) {
			throw new AppException(Validators.validateEMail(eMail,30));
		}
			
		db.createUser(login, Password.hash(password), fName, lName, eMail);

		page = Path.ACTION_PAGE;
		request.setAttribute("register", true);

		return page;
		}
}



