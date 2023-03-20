package com.example.demo;

import com.example.demo.DAO.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public ProgressBar progressBar;
    public ComboBox cbSeason;
    public ListView lvActivities;
    public ListView lvPersons;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.loadSeasons();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSeasons() throws URISyntaxException {
        System.out.println(DatabaseManager.getInstance().getAllSeasons());
    }
}