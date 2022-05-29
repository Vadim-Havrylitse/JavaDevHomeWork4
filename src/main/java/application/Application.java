package application;

import databaseservice.DataBaseService;
import dbenum.DBEnum;
import lombok.AccessLevel;
import lombok.Getter;
import states.StartState;
import states.State;

import java.util.Scanner;

@Getter
public class Application {
    @Getter(AccessLevel.PRIVATE)
    private State state;
    private final DataBaseService dataBaseService;

    public Application(){
        DBEnum dbEnum = chooseDB();
        this.dataBaseService = DataBaseService.of(dbEnum);
        this.state = new StartState(this);
    }

    public void changeState (State state){
        this.state = state;
    }

    public void startApplication (){

        while (true){
            state.doWhenPress();
        }
    }

    public DBEnum chooseDB (){
        Scanner scannerApplication = new Scanner(System.in);
        int pressNumberInMessage;
        int pressNumberFromUser;
        DBEnum[] dbEnums = DBEnum.values();

        do {
            pressNumberInMessage = 1;
            System.out.println("CHOOSE DB FOR WORK:");
            for (DBEnum db : dbEnums) {
                System.out.println("press " + (pressNumberInMessage++) + " -> " + db.name());
            }
             pressNumberFromUser = scannerApplication.nextInt();
        } while (pressNumberFromUser > DBEnum.values().length);

        return dbEnums[pressNumberFromUser-1];


    }

}
