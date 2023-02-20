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

public class ListOfAvailableBooksViewController implements Initializable {

    @FXML
    private TableColumn<Book, String> authorName;

    @FXML
    private TableColumn<Book, String> bookName;
    @FXML
    private TableColumn<Book, LocalDate> publishedDate;
    @FXML
    private Label noOfBooks;

    @FXML
    private Label headingLbl;
    @FXML
    private Label msgLbl;
    @FXML
    private Button loadIssuedBooksBtn;

    @FXML
    private Button switchToAddBook;

    @FXML
    private Button switchToIssueBook;

    @FXML
    private Button switchToReturnBook;

    @FXML
    private TableView<Book> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLbl.setVisible(false);
        tableView.getItems().clear();
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        publishedDate.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        tableView.getItems().addAll(DBUtility.getAvailableBooksFromDB());
        noOfBooks.setText(tableView.getItems().size() + " Books");
    }

    @FXML
    private void loadIssuedBooks(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "listOfIssuedBooksView.fxml");
    }

    @FXML
    private void addBook(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "addBookView.fxml");
    }

    @FXML
    private void issueBook(ActionEvent event) throws IOException {

        if (tableView.getSelectionModel().isEmpty()) {
            msgLbl.setVisible(true);
            msgLbl.setText("Please select the book first, Then click Issue book button");
            msgLbl.setTextFill(Color.web("red"));
        } else {
            String bookName = tableView.getSelectionModel().getSelectedItem().getBookName();
            String authorName = tableView.getSelectionModel().getSelectedItem().getAuthorName();
            LocalDate publishedDate = tableView.getSelectionModel().getSelectedItem().getPublishedDate();
            SceneChanger.changeScenes(event, "issueBookView.fxml", bookName, authorName, publishedDate);
        }
    }
}
