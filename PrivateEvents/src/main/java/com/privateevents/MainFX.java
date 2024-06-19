package com.privateevents;

import com.privateevents.utils.SessionManager;
import com.privateevents.utils.view.fx.FilesFXML;
import com.privateevents.utils.view.fx.PageManagerSingleton;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application{

    Integer currentSession;

    static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws OperationFailedException, NotFoundException {
        currentSession = SessionManager.getSessionManager().createSession();
        PageManagerSingleton.getInstance(stage);
        PageManagerSingleton.getInstance().setHome(FilesFXML.HOME.getPath(), currentSession);
    }

    @Override
    public void stop(){
        SessionManager.getSessionManager().removeSession(currentSession);
    }


}
