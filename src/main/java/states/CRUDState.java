package states;

import application.Application;
import databaseservice.DataBaseService;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.Map;

public class CRUDState extends State{
    protected final DataBaseService dataBaseService;
    private static final String[] MESSAGE_CRUD = {"***CRUD MENU***\nchoose the right point:",
            "press 1 -> Create",
            "press 2 -> Read",
            "press 3 -> Update",
            "press 4 -> Delete",
            "press 0 -> MAIN MENU"};

    public CRUDState(Application app) {
        super(app);
        this.dataBaseService = app.getDataBaseService();
    }

    @Override
    public void doWhenPress() {
        int inputNumber = checkCorrectInputAndReturnNumber(MESSAGE_CRUD);
        switch (inputNumber){
            case 1:
                app.changeState(new CreateQueryState(app));
                break;
            case 2:
                app.changeState(new ReadQueryState(app));
                break;
            case 3:
                app.changeState(new UpdateQueryState(app));
                break;
            case 4:
                app.changeState(new DeleteQueryState(app));
                break;
            case 0:
                returnToStartState();
                break;
        }
    }

    protected String chooseColumnFromUserAndReturnNameOfColumn(String nameTable, String message) throws SQLException {
        System.out.println(message);
        Map<Integer, String> mapOfColumnsName = dataBaseService.getMapOfColumnsName(nameTable);
        mapOfColumnsName.forEach((key, value) -> System.out.println("press " + key + " -> " + value));
        String inputNumberOfColumns = scanner.nextLine().trim().replaceAll("\\W+", "");

        StringBuilder result = new StringBuilder();
        for (char el : inputNumberOfColumns.toCharArray()) {
            if (Character.isDigit(el)) {
                int numericValue = Character.getNumericValue(el);
                if (mapOfColumnsName.containsKey(numericValue)) {
                    result.append(mapOfColumnsName.get(numericValue)).append(",");
                }
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }


    @SneakyThrows
    protected void returnToCRUDState (){
        Thread.sleep(500);
        app.changeState(new CRUDState(app));
    }

}
