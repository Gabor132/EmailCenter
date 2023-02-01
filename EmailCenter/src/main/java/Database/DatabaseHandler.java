/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import User.Accounts;
import User.Friends;
import User.Messages;
import User.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Dragos-Alexandru
 */
public class DatabaseHandler {
    private static DatabaseHandler INSTANCE;
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    private static final String DB_URL_LOCAL = "jdbc:mysql://localhost/";
    
    private static final String USER_LOCAL = "root";
    private static final String PASS_LOCAL = "";
    private static final String DB_NAME_LOCAL = "emailcenter";
    
    private static final String DB_URL_REMOTE = "jdbc:mysql://sql7.freesqldatabase.com:3306/";
    
    private static final String USER_REMOTE = "sql7120529";
    private static final String PASS_REMOTE = "uKIEiWexMe";
    private static final String DB_NAME_REMOTE = "sql7120529";
    
    private final boolean IS_REMOTE;
    
    public static final String LOCAL_CONFIG = "db_test_local";
    public static final String REMOTE_CONFIG = "db_test";
    public static final String RESET_CONFIG = "bjfotjsdfhew213das4";
    
    private final String[] TABLE_NAMES = {"USERS", "ACCOUNTS", "FRIENDS", "MESSAGES"};
    
    private Connection connection;
    
    private DatabaseHandler(boolean IS_REMOTE, boolean RESET_TABLES){
        this.IS_REMOTE = IS_REMOTE;
        createConnection();
        createDatabase();
        if(!checkTables()){
            createTables();
        }else if(RESET_TABLES){
            deleteTables();
            createTables();
        }
    }
    
    private void createConnection(){
        connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            if(IS_REMOTE){
                System.out.println("Connectiong to remote database");
                connection = DriverManager.getConnection(DB_URL_REMOTE, USER_REMOTE, PASS_REMOTE);
            }else{
                System.out.println("Connectiong to local database");
                connection = DriverManager.getConnection(DB_URL_LOCAL, USER_LOCAL, PASS_LOCAL);
            }
            System.out.println("Succesfully connected to database");
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, " Failed to connect to database"
                    + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }
    
    private Connection getConnection(){
        try {
            try (Statement stmt = connection.createStatement()) {
                if(IS_REMOTE){
                    stmt.executeUpdate("USE "+DB_NAME_REMOTE+";");
                }else{
                    stmt.executeUpdate("USE "+DB_NAME_LOCAL+";");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        return connection;
    }
    
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Closed database connection");
            }
        } catch (SQLException se) {
            System.err.println("Failed "+se.getMessage());
        }
    }
    
    /**
     * Create the database
     *
     * @return true if the database has been created, otherwise return false
     */
    private boolean createDatabase() {
        Statement stmt = null;
        Connection conn;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE " + DB_NAME_REMOTE + "\n" +
                    " DEFAULT CHARACTER SET utf8\n" +
                    " DEFAULT COLLATE utf8_general_ci;";
            
            stmt.executeUpdate(sql);
            
        } catch (SQLException sqlException) {
            //if the database already exist
            if (sqlException.getErrorCode() == 1007) {
                System.err.println(sqlException.getMessage());
                return false;
            } else {
                System.err.println("Failed: "+sqlException.getMessage());
                return false;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                return false;
            }
        }
        return true;
    }
    
    private void createTables() {
        Statement stmt = null;
        Connection conn;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            System.out.println("Creating tables");
            //create USERS
            String sqlQuery = "CREATE TABLE "+TABLE_NAMES[0]+" "
                    + "(user_id INT AUTO_INCREMENT, "
                    + " username VARCHAR(100) NOT NULL UNIQUE, "
                    + " password VARCHAR(100) NOT NULL, "
                    + " PRIMARY KEY ( user_id ))ENGINE=InnoDB;";
            
            stmt.executeUpdate(sqlQuery);

            // create ACCOUNT
            sqlQuery = "CREATE TABLE "+TABLE_NAMES[1]+" "
                    + "(account_id INT not NULL AUTO_INCREMENT, "
                    + " email VARCHAR(100) NOT NULL, "
                    + " password VARCHAR(100) NOT NULL, "
                    + " user_id INT, "
                    + " PRIMARY KEY ( account_id ),"
                    + " FOREIGN KEY ( user_id ) REFERENCES USERS (user_id) ON DELETE CASCADE "
                    + " )ENGINE=InnoDB;";
            
            stmt.executeUpdate(sqlQuery);
            
            sqlQuery = "CREATE TABLE "+TABLE_NAMES[2]+" "
                    + "( friend_id INT NOT NULL AUTO_INCREMENT, "
                    + " email VARCHAR(100) NOT NULL, "
                    + " account_id INT, "
                    + " PRIMARY KEY ( friend_id ),"
                    + " FOREIGN KEY( account_id ) REFERENCES ACCOUNTS (account_id) ON DELETE CASCADE "
                    + " )ENGINE=InnoDB;";
            
            stmt.executeUpdate(sqlQuery);
            
            sqlQuery = "CREATE TABLE "+TABLE_NAMES[3]+" " 
                    + "( message_id INT AUTO_INCREMENT, "
                    + " subject varchar(100) NOT NULL, "
                    + " content TEXT NOT NULL, "
                    + " attachment_name varchar(100), "
                    + " attachment_size varchar(100), "
                    + " send_date varchar(100) NOT NULL, "
                    + " friend_id INT, "
                    + " PRIMARY KEY ( MESSAGE_ID ), "
                    + " FOREIGN KEY ( friend_id ) REFERENCES FRIENDS (friend_id) ON DELETE CASCADE "
                    + " )ENGINE=InnoDB;";
            
            stmt.executeUpdate(sqlQuery);
            
            
            System.out.println("Table created");
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.err.println("Failed "+ex.getMessage());
                }
            }
        }
    }
    
    private void deleteTables(){
        Statement stmt = null;
        Connection conn;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            System.out.println("Dropping tables");
            //create USERS
            String sqlQuery = "DROP TABLE "+TABLE_NAMES[3]+";";
            
            stmt.executeUpdate(sqlQuery);

            // create ACCOUNT
            sqlQuery = "DROP TABLE "+TABLE_NAMES[2]+";";
            
            stmt.executeUpdate(sqlQuery);
            
            sqlQuery = "DROP TABLE "+TABLE_NAMES[1]+";";
            
            stmt.executeUpdate(sqlQuery);
            
            sqlQuery = "DROP TABLE "+TABLE_NAMES[0]+";";
            
            stmt.executeUpdate(sqlQuery);
            
            
            System.out.println("Tables dropped");
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.err.println("Failed "+ex.getMessage());
                }
            }
        }
    }
    
    private boolean checkTables(){
        boolean success = true;
        Statement stmt = null;
        Connection conn;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            System.out.println("Checking that tables exist");
            
            //create USERS
            String sqlQuery = "SELECT * FROM USERS;";
            
            stmt.executeQuery(sqlQuery);

            // create ACCOUNT
            sqlQuery = "SELECT * FROM ACCOUNTS;";
            
            stmt.executeQuery(sqlQuery);
            
            sqlQuery = "SELECT * FROM FRIENDS;";
            
            stmt.executeQuery(sqlQuery);
            
            sqlQuery = "SELECT * FROM MESSAGES";
            
            stmt.executeQuery(sqlQuery);
            
            System.out.println("Tables already exist");
        } catch (SQLException ex) {
            success = false;
            System.err.println("Failed "+ex.getMessage());
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.err.println("Failed "+ex.getMessage());
                }
            }
        }
        return success;
    }
    
    public boolean registerUser(Users user){
        boolean succesful = false;
        Connection conn = getConnection();
        
        String sqlQuery = "INSERT INTO USERS (username, password) values(?,?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            
            int result = stmt.executeUpdate();
            if(result > 0)
                succesful = true;
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        return succesful;
    }
    
    public boolean checkUserForLogin(Users user){
        boolean succesful = false;
        Connection conn = getConnection();
        
        String sqlQuery = "SELECT password FROM USERS WHERE lower(username) = lower(?);";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, user.getUsername());
            
            ResultSet result = stmt.executeQuery();
            if(result.first()){
                if(result.getString(1).equals(user.getPassword())){
                    succesful = true;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        return succesful;
    }
    
    public boolean addAccountToUser(Accounts account){
        Integer user_id = null;
        Connection conn = getConnection();
        
        String sqlQuery = " SELECT user_id FROM USERS WHERE lower(username) = lower(?) "
                + "AND lower(password) = lower(?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, account.getUser().getUsername());
            stmt.setString(2, account.getUser().getPassword());
            
            try (ResultSet result = stmt.executeQuery()) {
                result.next();
                user_id = result.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        if(user_id != null){
            sqlQuery = " INSERT INTO ACCOUNTS (email, password, user_id) VALUES (?, ?, ?);";
            try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){

                stmt.setString(1, account.getEmailAddress());
                stmt.setString(2, account.getPassword());
                stmt.setInt(3, user_id);

                return stmt.executeUpdate() == 1;
                
            } catch (SQLException ex) {
                System.err.println("Failed "+ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean deleteAccountFromUser(Accounts account){
        Integer user_id = null;
        Connection conn = getConnection();
        
        String sqlQuery = " SELECT user_id FROM USERS WHERE lower(username) = lower(?) "
                + "AND lower(password) = lower(?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, account.getUser().getUsername());
            stmt.setString(2, account.getUser().getPassword());
            
            try (ResultSet result = stmt.executeQuery()) {
                result.next();
                user_id = result.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        if(user_id != null){
            sqlQuery = " DELETE FROM ACCOUNTS WHERE lower(email) = lower(?) AND lower(password) = lower(?) AND user_id = ?;";
            try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){

                stmt.setString(1, account.getEmailAddress());
                stmt.setString(2, account.getPassword());
                stmt.setInt(3, user_id);

                return stmt.executeUpdate() == 1;
                
            } catch (SQLException ex) {
                System.err.println("Failed "+ex.getMessage());
            }
        }
        return false;
    }
    
    public List<Accounts> getAccountsForUser(Users user){
        List<Accounts> accounts = new LinkedList<>();
        
        Connection conn = getConnection();
        
        String sqlQuery = "SELECT a.email, a.password FROM ACCOUNTS a JOIN USERS u \n" +
                        "ON(a.user_id = u.user_id) WHERE lower(u.username) = lower(?)\n" +
                        " AND lower(u.password) = lower(?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            
            ResultSet result = stmt.executeQuery();
            if(result.first()){
                do{
                    String email = result.getString(1);
                    String pass = result.getString(2);

                    Accounts account = new Accounts(email, pass, user);
                    accounts.add(account);

                }while(result.next());
            }
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        
        return accounts;
    }
    
    public boolean addFriendToAccount(Friends friend){
        Integer account_id = null;
        Accounts account = friend.getAccount();
        Users user = account.getUser();
        
        Connection conn = getConnection();
        
        String sqlQuery = " SELECT account_id FROM ACCOUNTS JOIN USERS "
                + "USING(user_id) WHERE lower(email) = lower(?) "
                + " AND lower(username) = lower(?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, account.getEmailAddress());
            stmt.setString(2, user.getUsername());
            
            try (ResultSet result = stmt.executeQuery()) {
                result.next();
                account_id = result.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        if(account_id != null){
            sqlQuery = " INSERT INTO FRIENDS (email, account_id) VALUES (?, ?);";
            try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){

                stmt.setString(1, friend.getEmailAddress());
                stmt.setInt(2, account_id);

                return stmt.executeUpdate() == 1;
                
            } catch (SQLException ex) {
                System.err.println("Failed "+ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean deleteFriendFromAccount(Friends friend){
        Integer account_id = null;
        Accounts account = friend.getAccount();
        Users user = account.getUser();
        Connection conn = getConnection();
        
        String sqlQuery = " SELECT account_id FROM ACCOUNTS JOIN USERS "
                + " USING(user_id) WHERE lower(email) = lower(?) "
                + " AND lower(username) = lower(?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, account.getEmailAddress());
            stmt.setString(2, user.getUsername());
            
            try (ResultSet result = stmt.executeQuery()) {
                result.next();
                account_id = result.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        if(account_id != null){
            sqlQuery = " DELETE FROM FRIENDS WHERE lower(email) = lower(?) AND account_id = ?;";
            try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){

                stmt.setString(1, friend.getEmailAddress());
                stmt.setInt(2, account_id);

                return stmt.executeUpdate() == 1;
                
            } catch (SQLException ex) {
                System.err.println("Failed "+ex.getMessage());
            }
        }
        return false;
    }
    
    public List<Friends> getFriendsForAccount(Accounts account){
        List<Friends> friends = new LinkedList<>();
        Users user = account.getUser();
        Connection conn = getConnection();
        
        String sqlQuery = "SELECT f.email FROM FRIENDS f JOIN ACCOUNTS a "
                + "ON(f.account_id = a.account_id) JOIN USERS u "
                + "ON(a.user_id = u.user_id) WHERE lower(u.username) = lower(?) "
                + "AND lower(a.email) = lower(?);";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, account.getEmailAddress());
            
            ResultSet result = stmt.executeQuery();
            if(result.first()){
                do{
                    String email = result.getString(1);
                    Friends auxFriend = new Friends(email, account);
                    friends.add(auxFriend);
                }while(result.next());
            }
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        return friends;
    }
    
    public boolean addMessageToFriend(Messages message){
        Integer friend_id = null;
        Friends friend = message.getReceiver();
        Accounts account = friend.getAccount();
        Users user = account.getUser();
        Connection conn = getConnection();
        
        String sqlQuery = " SELECT f.friend_id FROM FRIENDS f JOIN ACCOUNTS a "
                + "ON(f.account_id = a.account_id) JOIN USERS u "
                + "ON(a.user_id = u.user_id) WHERE lower(u.username) = lower(?)"
                + "AND lower(a.email) = lower(?) AND lower(f.email) = lower(?) ";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, account.getEmailAddress());
            stmt.setString(3, friend.getEmailAddress());
            
            try (ResultSet result = stmt.executeQuery()) {
                result.next();
                friend_id = result.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        if(friend_id != null){
            sqlQuery = " INSERT INTO MESSAGES (subject, content, attachment_name, attachment_size,"
                    + " send_date, friend_id) VALUES (?, ?, ?, ?, ?, ?);";
            try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
                
                stmt.setString(1, message.getSubject());
                stmt.setString(2, message.getTextContent());
                if(message.getAttachmentName() == null){
                    stmt.setString(3, "null");
                    stmt.setString(4, "null");
                }else{
                    stmt.setString(3, message.getAttachmentName());
                    stmt.setString(4, message.getAttachmentSize());
                }
                stmt.setString(5, message.getTimestamp());
                stmt.setInt(6, friend_id);

                return stmt.executeUpdate() == 1;
                
            } catch (SQLException ex) {
                System.err.println("Failed "+ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean deleteMessageFromFriend(Messages message){
        Integer friend_id = null;
        Friends friend = message.getReceiver();
        Accounts account = friend.getAccount();
        Users user = account.getUser();
        Connection conn = getConnection();
        
        String sqlQuery = " SELECT f.friend_id FROM FRIENDS f JOIN ACCOUNTS a "
                + "ON(f.account_id = a.account_id) JOIN USERS u "
                + "ON(a.user_id = u.user_id) WHERE lower(u.username) = lower(?)"
                + "AND lower(a.email) = lower(?) AND lower(f.email) = lower(?) ";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, account.getEmailAddress());
            stmt.setString(3, friend.getEmailAddress());
            
            try (ResultSet result = stmt.executeQuery()) {
                result.next();
                friend_id = result.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        if(friend_id != null){
            sqlQuery = " DELETE FROM MESSAGES WHERE lower(email) = lower(?) AND friend_id = ?;";
            try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){

                stmt.setString(1, friend.getEmailAddress());
                stmt.setInt(2, friend_id);

                return stmt.executeUpdate() == 1;
                
            } catch (SQLException ex) {
                System.err.println("Failed "+ex.getMessage());
            }
        }
        return false;
    }
    
    public List<Messages> getMessageFromFriend(Friends friend){
        List<Messages> messages = new LinkedList<>();
        Accounts account = friend.getAccount();
        Users user = account.getUser();
        Connection conn = getConnection();
        
        String sqlQuery = "SELECT m.subject, m.content, m.attachment_name, m.attachment_size, m.send_date FROM MESSAGES m JOIN FRIENDS f "
                + "ON(m.friend_id = f.friend_id) JOIN ACCOUNTS a "
                + "ON(f.account_id = a.account_id) JOIN USERS u "
                + "ON(a.user_id = u.user_id) WHERE lower(u.username) = lower(?) "
                + "AND lower(a.email) = lower(?) AND lower(f.email) = lower(?);";
        
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery);){
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, account.getEmailAddress());
            stmt.setString(3, friend.getEmailAddress());
            
            ResultSet result = stmt.executeQuery();
            if(result.first()){
                do{
                    String subject = result.getString(1);
                    String content = result.getString(2);
                    String attachmentName = result.getString(3);
                    String attachmentSize = result.getString(4);
                    String date = result.getString(5);
                    Messages auxMessage;
                    if(attachmentName.equals("null")){
                        auxMessage = new Messages(subject, content, date,
                                null, null, friend);
                    }else{
                        auxMessage = new Messages(subject, content, date,
                                attachmentName, attachmentSize, friend);
                    }
                    messages.add(auxMessage);
                }while(result.next());
            }
            
        } catch (SQLException ex) {
            System.err.println("Failed "+ex.getMessage());
        }
        return messages;
    }
    
    public static DatabaseHandler getInstance(boolean IS_REMOTE, boolean RESET_TABLES){
        if(INSTANCE == null){
            INSTANCE = new DatabaseHandler(IS_REMOTE, RESET_TABLES);
        }
        return INSTANCE;
    }
    
}
