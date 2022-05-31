package states;

import application.Application;
import databaseservice.DataBaseService;

import java.sql.SQLException;

public class UtilQueryState extends State{
    private final DataBaseService dataBaseService;

    private static final String[] MESSAGE_UTIL_QUERY = {"***QUERY FROM HOME TASK***\nchoose the right point:",
            "press 1 -> SUM(salary) in some project",
            "press 2 -> List all developers in some project",
            "press 3 -> List all Java-developers",
            "press 4 -> List all middle-developers",
            "press 5 -> List project with specific format",
            "press 0 -> MAIN MENU"};

    private static final String queryForSumSalaryInSomeProject = "SELECT projects.name, SUM(salary) " +
            "FROM projects " +
            "LEFT JOIN developers_projects " +
            "ON id = projects_id " +
            "LEFT JOIN developers " +
            "ON developers_id = developers.id " +
            "WHERE projects.id = %s "+
            "GROUP BY projects.name;";

    private static final String queryForListAllDevInSomeProject = "SELECT developers.* " +
            "FROM developers_projects " +
            "LEFT JOIN developers " +
            "ON developers_id = id " +
            "WHERE projects_id = %s;";

    private static final String queryForListAllJavaDev = "SELECT developers.*" +
            "FROM developers " +
            "LEFT JOIN developers_skills " +
            "ON developers_id = developers.id " +
            "LEFT JOIN skills " +
            "ON skills_id = skills.id " +
            "WHERE skills.industry = 'Java';";

    private static final String queryForListAllMiddleDev = "SELECT developers.*" +
            "FROM developers " +
            "LEFT JOIN developers_skills " +
            "ON developers_id = developers.id " +
            "LEFT JOIN skills " +
            "ON skills_id = skills.id " +
            "WHERE skills.degree = 'Middle';";
    private static final String queryForListAllProjectWithSpecFormat = "SELECT projects.release_date, projects.name, COUNT(developers_id) " +
            "FROM projects " +
            "LEFT JOIN developers_projects " +
            "ON developers_id = id " +
            "GROUP BY projects.id;";

    public UtilQueryState(Application app) {
        super(app);
        this.dataBaseService = app.getDataBaseService();
    }

    @Override
    public void doWhenPress() {
        int inputNumber = checkCorrectInputAndReturnNumber(MESSAGE_UTIL_QUERY);
        try{
            switch (inputNumber) {
                case 1:
                    System.out.println("Write number of project:");
                    String temp = scanner.nextLine();
                    dataBaseService.readAndPrintData(String.format(queryForSumSalaryInSomeProject, temp));
                    break;
                case 2:
                    System.out.println("Write number of project:");
                    String temp2 = scanner.nextLine();
                    dataBaseService.readAndPrintData(String.format(queryForListAllDevInSomeProject, temp2));
                    break;
                case 3:
                    dataBaseService.readAndPrintData(queryForListAllJavaDev);
                    break;
                case 4:
                    dataBaseService.readAndPrintData(queryForListAllMiddleDev);
                    break;
                case 5:
                    dataBaseService.readAndPrintData(queryForListAllProjectWithSpecFormat);
                    break;
                case 0:
                    returnToStartState();
                    break;
            }
        }catch (SQLException e) {
                printMessageWhenSQLExceptionCatch(e.getMessage());
        }
    }
}
