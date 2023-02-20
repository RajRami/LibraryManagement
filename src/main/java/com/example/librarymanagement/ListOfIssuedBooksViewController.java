package com.example.librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ListOfIssuedBooksViewController implements Initializable {

    @FXML
    private TableColumn<Book, String> authorName;

    @FXML
    private TableColumn<Book, String> bookName;

    @FXML
    private TableColumn<Book, LocalDate> issuedDate;

    @FXML
    private Button loadAvailableBooksBtn;

    @FXML
    private Button switchToAddBook;

    @FXML
    private Button switchToIssueBook;

    @FXML
    private Button switchToReturnBook;

    @FXML
    private TableView<Book> tableView;

    @FXML
    private Label noOfBooks;
    @FXML
    private Label msgLbl;

    @FXML
    private TableColumn<Book, LocalDate> publishedDate;
    @FXML
    private TableColumn<Book, String> issuerName;

    @FXML
    private TableColumn<Book, String> issuerContact;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLbl.setVisible(false);
        tableView.getItems().clear();
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        publishedDate.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        issuerName.setCellValueFactory(new PropertyValueFactory<>("issuerName"));
        issuerContact.setCellValueFactory(new PropertyValueFactory<>("issuerContact"));
        issuedDate.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
        tableView.getItems().addAll(DBUtility.getIssuedBooksFromDB());
        noOfBooks.setText(tableView.getItems().size() + " Books");
    }

    @FXML
    private void loadAvailableBooks(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "listOfAvailableBooksView.fxml");
    }

    @FXML
    private void addBook(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "addBookView.fxml");
    }

    @FXML
    private void returnBook(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().isEmpty()) {
            msgLbl.setVisible(true);
            msgLbl.setText("Please select the book first, Then click Return book button");
            msgLbl.setTextFill(Color.web("red"));
        } else {
            String bookName = tableView.getSelectionModel().getSelectedItem().getBookName();
            String authorName = tableView.getSelectionModel().getSelectedItem().getAuthorName();
            LocalDate publishedDate = tableView.getSelectionModel().getSelectedItem().getPublishedDate();
            String issuerName = tableView.getSelectionModel().getSelectedItem().getIssuerName();
            String issuerContact = tableView.getSelectionModel().getSelectedItem().getIssuerContact();
            SceneChanger.changeScenes(event, "returnBookView.fxml", bookName, authorName, publishedDate, issuerName, issuerContact);
        }
    }
}
