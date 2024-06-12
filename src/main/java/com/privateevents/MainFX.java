package com.privateevents;

import com.privateevents.utils.view.SessionManager;
import com.privateevents.utils.view.fx.FilesFXML;
import com.privateevents.utils.view.fx.PageManagerSingleton;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application{

    static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws OperationFailedException, NotFoundException {
        Integer session = SessionManager.getSessionManager().createSession();
        PageManagerSingleton.getInstance(stage);
        PageManagerSingleton.getInstance().setHome(FilesFXML.HOME.getPath(), session);
    }


}
