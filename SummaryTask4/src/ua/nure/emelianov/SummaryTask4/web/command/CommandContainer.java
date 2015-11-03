package ua.nure.emelianov.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;
/**
 * class contains commands, required for Controller
 * @author Emelianov Pavel
 *
 */
public class CommandContainer {

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		commands.put("login", new LoginCommand());
		commands.put("getUsers", new GetUsersCommand());
		commands.put("homepage", new HomePageCommand());
		commands.put("block", new BlockUserCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("getTests", new GetTestsCommand());
		commands.put("passTest", new PassTestCommand());
		commands.put("result", new GetResultCommand());
		commands.put("RegistrationPage", new RegistrationPageCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("editTest", new EditTestCommand());
		commands.put("editQuestion", new EditQuestionCommand());
		commands.put("settings", new SettingsCommand());
		commands.put("refactorPage", new RefactorPageCommand());
		commands.put("refactor", new RefactorCommand());
		commands.put("answerStatus", new AnswerStatusCommand());
		commands.put("blockTheme", new BlockThemeCommand());
	}

	public static Command get(String command) {
		return commands.get(command);
	}

}
