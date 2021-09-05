/*
 * Description: Class MySqlConnector. Creates a Mysql database connection
 */
package dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnector {

  private static String connURL;
  private static String host;
  private static String port;
  private static String user;
  private static String userPassword;
  private static String database;
  private SqlConnector() {}

  /*
  Get database connection info from a properties file: connection.prop
   */
  static {
      host = System.getenv("SQL_HOST");
      port = System.getenv("SQL_PORT");
      user = System.getenv("SQL_USER");
      userPassword = System.getenv("SQL_USER_PASSWORD");
      database = System.getenv("SQL_DATABASE");
      connURL="jdbc:mysql://"+host+":"+port+"/"+database+"?allowPublicKeyRetrieval=true";
  }


  /*
  Static method that returns a new Mysql connection
   */
  public static Connection getConnection() throws SQLException {
    Connection conn = DriverManager.getConnection(connURL, user, userPassword);
    conn.setAutoCommit(true);
    return conn;
    }


  }
