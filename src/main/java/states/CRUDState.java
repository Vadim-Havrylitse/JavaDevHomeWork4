package states;

import application.Application;
import databaseservice.DataBaseService;

import java.sql.SQLException;

public class CRUDState extends State{
    private final DataBaseService dataBaseService;
    private final String[] MESSAGE_CRUD = {"\n***CRUD MENU***\nchoose the right point:",
            "press 1 -> Create",
            "press 2 -> Read data",
            "press 3 -> Update data",
            "press 4 -> Delete",
            "press 0 -> MAIN MENU"};

    public CRUDState(Application app) {
        super(app);
        this.dataBaseService = app.getDataBaseService();
    }

    @Override
    public void doWhenPress() {
        int inputNumber = checkCorrectInputAndReturnNumber(MESSAGE_CRUD);
        try {
            switch (inputNumber){
                case 1:
                    dataBaseService.create(getSqlQueryFromUser());
                    break;
                case 2:
                    dataBaseService.readData(getSqlQueryFromUser());
                    break;
                case 3:
                    dataBaseService.updateData(getSqlQueryFromUser());
                    break;
                case 4:
                    dataBaseService.delete(getSqlQueryFromUser());
                    break;
                case 0:
                    returnToStartState();
                    break;
            }
        } catch (SQLException e) {
            checkForContinueWhenSQLExceptionCatch();
        }
    }

    private String getSqlQueryFromUser() {
        System.out.println("Write your sql query and then write \"do\" for execute:");
        String sqlQuery;
        StringBuilder builder = new StringBuilder();
        while (true){
            sqlQuery = scanner.nextLine();
            if (!sqlQuery.equalsIgnoreCase("do")){
                builder.append(sqlQuery);
                continue;
            }
            break;
        }
        return builder.toString();
    }

}
