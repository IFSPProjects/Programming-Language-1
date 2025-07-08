package controller;

import object.BaseTask;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseControllerTest {

    DatabaseController db = new DatabaseController();
    final String CONNECTION_STRING = "jdbc:sqlite:memory:";


    private Connection specificOpenConnection() {
        try (var connection = DriverManager.getConnection(CONNECTION_STRING)) {
            return connection;
        } catch (SQLException e) {
            System.out.printf("Connection not established");
            return null;
        }
    }

    private BaseTask genericTask(ZonedDateTime date) {
        return new BaseTask("Test task.", false, date);
    }

    private void cleanup(int id) {
        DatabaseController.setupDB(CONNECTION_STRING);
        db.removeTask(id, specificOpenConnection());
    }

    @Test
    void isDatabaseBeingCreated() {
        assertDoesNotThrow(() -> specificOpenConnection());
    }
}
/*
    @Test
    void isDatabaseKeepingData() {
        var connection = DatabaseController.openConnection();
        assertNull(db.getTask(1000000, connection));
        db.storeTask(genericTask(ZonedDateTime.now()), connection);
        assertNotNull(db.getTask(1000000, connection));
        cleanup(1000000);
    }

    @Test
    void isDatabaseUpdatingData() {
        var connection = DatabaseController.openConnection();
        db.storeTask(genericTask(ZonedDateTime.now()), connection);
        BaseTask firstAcquiredTask = db.getTask(1000000, connection);
        firstAcquiredTask.setText("Test task 2.");
        db.updateTask(firstAcquiredTask, connection);
        BaseTask secondAcquiredTask = db.getTask(1000000, connection);
        assertEquals("Test task 2.", secondAcquiredTask.getText());
    }
}
*/
