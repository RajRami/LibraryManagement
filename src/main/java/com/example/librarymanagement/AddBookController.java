package com.example.librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    private Button addBookBtn;

    @FXML
    private TextField authorNameTxt;
    @FXML
    private DatePicker dateField;
    @FXML
    private Label msgLbl;
    @FXML
    private TextField bookNameTxt;

    @FXML
    private Button loadAvailableBooksBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLbl.setVisible(false);
        dateField.setEditable(false);
    }

    @FXML
    private void addBtnClicked() {
        if (bookNameTxt.getText().isEmpty() || authorNameTxt.getText().isEmpty() || dateField.getValue() == null) {
            msgLbl.setVisible(true);
            msgLbl.setText("each field must have value.!!");
            msgLbl.setTextFill(Color.web("red"));
        } else {
            if (dateField.getValue().isAfter(LocalDate.now()) || dateField.getValue().isEqual(LocalDate.now())) {
                msgLbl.setVisible(true);
                msgLbl.setText("Published date must be in the past");
                msgLbl.setTextFill(Color.web("red"));
            } else {
                Book book = new Book(bookNameTxt.getText(), authorNameTxt.getText(), dateField.getValue());
                DBUtility.insertNewBookIntoDB(book);
                msgLbl.setVisible(true);
                msgLbl.setText("Book has been added.!!");
                msgLbl.setTextFill(Color.web("blue"));
                bookNameTxt.setText("");
                authorNameTxt.setText("");
                dateField.setValue(null);
            }
        }
    }

    @FXML
    private void loadAvailableBooks(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "listOfAvailableBooksView.fxml");
    }
}


