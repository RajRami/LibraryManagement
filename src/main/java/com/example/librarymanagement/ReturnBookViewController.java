package com.example.librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;

public class ReturnBookViewController {

    @FXML
    private TextField authorNameTxt;

    @FXML
    private TextField bookNameTxt;

    @FXML
    private TextField issuerContactTxt;

    @FXML
    private TextField issuerNameTxt;

    @FXML
    private Button loadAvailableBooksBtn;

    @FXML
    private Label msgLbl;

    @FXML
    private Button returnBookBtn;

    private String bookName1;
    private String authorName1;
    private String issuerName1;
    private String issuerContact1;
    private LocalDate publishedDate1;

    @FXML
    public void returnABook(String bookName, String authorName, LocalDate publishedDate, String issuerName, String issuerContact) {
        bookName1 = bookName;
        authorName1 = authorName;
        publishedDate1 = publishedDate;
        issuerName1 = issuerName;
        issuerContact1 = issuerContact;
        msgLbl.setVisible(false);
        bookNameTxt.setEditable(false);
        authorNameTxt.setEditable(false);
        bookNameTxt.setText(bookName);
        authorNameTxt.setText(authorName);
    }

    @FXML
    private void returnBookBtnClicked() {
        if (issuerNameTxt.getText().isEmpty() || issuerContactTxt.getText().isEmpty()) {
            msgLbl.setVisible(true);
            msgLbl.setText("each field must have value.!!");
            msgLbl.setTextFill(Color.web("red"));
        } else {
            System.out.println(issuerName1);
            System.out.println(issuerContact1);
            System.out.println(issuerNameTxt.getText());
            System.out.println(issuerContactTxt.getText());
            if (!issuerNameTxt.getText().equalsIgnoreCase(issuerName1) || !issuerContactTxt.getText().equalsIgnoreCase(issuerContact1)) {
                msgLbl.setVisible(true);
                msgLbl.setText("Your name or contact does not match in our record.. Try again!");
                msgLbl.setTextFill(Color.web("red"));
            } else {
                Book book = new Book(bookNameTxt.getText(), authorNameTxt.getText(), publishedDate1);
                DBUtility.returnABook(book);
                DBUtility.deleteBookFromIssuedBooks(bookNameTxt.getText(), authorNameTxt.getText());
                msgLbl.setVisible(true);
                msgLbl.setText("Book has been returned.., Click loadAvailableBook button to see the book.");
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

}
