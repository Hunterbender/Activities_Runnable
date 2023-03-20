package com.example.demo.DAO;

import com.example.demo.model.Season;
import com.example.demo.util.PropertyManager;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection = null;
    private String driver;
    private String url;
    private String user;
    private String pwd;

    private static final String SEASON_SELECT_ALL = "SELECT * FROM SEASON";
    private static final String GET_PERSONS_CONNECTED_SEASON_ACTIVITY = "SELECT p.FIRSTNAME, p.LASTNAME FROM PERSON p" +
            "JOIN ACTIVITY a ON p.IDACTIVITY = a.ID" +
            "JOIN SEASON s ON a.IDSEASON = s.ID" +
            "WHERE s.IDENTIFIER = ? AND a.IDENTIFIER = ?";


    private static DatabaseManager instance;

    private DatabaseManager() throws URISyntaxException {
        PropertyManager.getInstance().setFilename("db.properties");
        this.driver = PropertyManager.getInstance().readProperty("driver", "oracle.jdbc.OracleDriver");
        this.url = PropertyManager.getInstance().readProperty("url", "jdbc:oracle:thin:@tcif.htl-villach.at:1521/orcl");
        this.user = PropertyManager.getInstance().readProperty("username", "d3b05");
        this.pwd = PropertyManager.getInstance().readProperty("password", "d3b05");
    }

    public static DatabaseManager getInstance() throws URISyntaxException {
        return instance == null ? instance = new DatabaseManager() : instance;
    }

    private Connection createConnection() throws SQLException {
        Connection con = null;

        try {
            //1. Registrieren des Treibers
            Class.forName(this.driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //2. Erzeugen der Verbindung
            con = DriverManager.getConnection(this.url, this.user, this.pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public List<Season> getAllSeasons() {
        List<Season> seasonList;

        try (Connection con = this.createConnection()) {
            PreparedStatement statement = con.prepareStatement(SEASON_SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            seasonList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String identifier = resultSet.getString("IDENTIFIER");
                seasonList.add(new Season(id, identifier));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }



}
