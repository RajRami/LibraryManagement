package com.example.librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class SceneChanger {
    public static void changeScenes(ActionEvent event, String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScenes(ActionEvent event, String fxmlFileName, String bookName, String authorName, LocalDate publishedDate) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        //get the controller from the destination
        IssueBookViewController issueBookViewController = fxmlLoader.getController();
        issueBookViewController.issueABook(bookName, authorName, publishedDate);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public static void changeScenes(ActionEvent event, String fxmlFileName , String bookName, String authorName, LocalDate publishedDate, String issuerName, String issuerContact) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        //get the controller from the destination
        ReturnBookViewController returnBookViewController = fxmlLoader.getController();
        returnBookViewController.returnABook(bookName, authorName, publishedDate, issuerName, issuerContact);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
