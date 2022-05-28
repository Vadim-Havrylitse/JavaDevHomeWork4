package application;

import databaseservice.DataBaseService;
import dbenum.DBEnum;
import lombok.AccessLevel;
import lombok.Getter;
import states.StartState;
import states.State;

@Getter
public class Application {
    @Getter(AccessLevel.PRIVATE)
    private State state;
    private final DataBaseService dataBaseService;

    public Application(DBEnum db){
        this.dataBaseService = DataBaseService.of(db);
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

}
