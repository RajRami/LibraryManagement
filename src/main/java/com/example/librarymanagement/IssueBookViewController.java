package com.example.librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;

public class IssueBookViewController {

    @FXML
    private TextField authorNameTxt;

    @FXML
    private TextField bookNameTxt;

    @FXML
    private Button issueBookBtn;

    @FXML
    private Button loadAvailableBooksBtn;

    @FXML
    private Label msgLbl;

    @FXML
    private TextField issuerContactTxt;

    @FXML
    private TextField issuerNameTxt;


    private String bookName1;
    private String authorName1;
    private LocalDate publishedDate1;

    @FXML
    public void issueABook(String bookName, String authorName, LocalDate publishedDate) {
        bookName1 = bookName;
        authorName1 = authorName;
        publishedDate1 = publishedDate;
        msgLbl.setVisible(false);
        bookNameTxt.setEditable(false);
        authorNameTxt.setEditable(false);
        bookNameTxt.setText(bookName);
        authorNameTxt.setText(authorName);
    }

    @FXML
    private void issueBookBtnClicked() {
        if (issuerNameTxt.getText().isEmpty() || issuerContactTxt.getText().isEmpty()) {
            msgLbl.setVisible(true);
            msgLbl.setText("each field must have value.!!");
            msgLbl.setTextFill(Color.web("red"));
        } else {
            if (!issuerContactTxt.getText().matches("\\(?[2-9]\\d{2}\\)?[-\\s]?[2-9]\\d{2}[-\\s]?\\d{4}")) {
                msgLbl.setVisible(true);
                msgLbl.setText("Phone number is invalid..!!");
                msgLbl.setTextFill(Color.web("red"));
            } else {
                Book book = new Book(bookNameTxt.getText(), authorNameTxt.getText(), publishedDate1, issuerNameTxt.getText(), issuerContactTxt.getText(), LocalDate.now());
                DBUtility.issueABook(book);
                DBUtility.deleteBookFromAvailableBooks(bookName1, authorName1);
                msgLbl.setVisible(true);
                msgLbl.setText("Book has been issued.., Click loadIssuedBook button to see the book.");
                msgLbl.setTextFill(Color.web("blue"));
                bookNameTxt.setText("");
                authorNameTxt.setText("");
                issuerNameTxt.setText("");
                issuerContactTxt.setText("");
                bookNameTxt.setEditable(false);
                authorNameTxt.setEditable(false);
                issuerNameTxt.setEditable(false);
                issuerContactTxt.setEditable(false);

            }
        }
    }

    @FXML
    private void loadAvailableBooks(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "listOfAvailableBooksView.fxml");
    }

    @FXML
    private void loadIssuedBooks(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "listOfIssuedBooksView.fxml");
    }
}
