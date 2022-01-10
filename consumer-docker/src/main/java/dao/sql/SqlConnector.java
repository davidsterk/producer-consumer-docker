/*
 * Description: Class MySqlConnector. Creates a Mysql database connection
 */
package dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class SqlConnector {

  private static final String connURL;
  private static final String user;
  private static final String userPassword;

  private static Connection conn;
  private SqlConnector() {}

  /*
  Get database connection info from a environment variables
   */
  static {
      user = System.getenv("SQL_USER");
      userPassword = System.getenv("SQL_USER_PASSWORD");
      connURL= System.getenv("SQL_CONN_URL");
  }
/*
* method createMethod() attempts to connect to the mysql database. If unsuccessful, it will try again
* after 5 seconds. Program will terminate after 5 unsuccessful attempts.
 */
  private static void createConnection() throws InterruptedException, SQLException {
      if(conn == null || conn.isClosed()) {
          int attempts = 0;
          int maxAttempts = 5;
          boolean needConn = true;
          while(needConn) {
              try {
                  conn = DriverManager.getConnection(connURL, user, userPassword);
                  needConn = false;
              } catch (SQLException e) {
                  System.out.println("Failed to connect to database");
                  if(++attempts<maxAttempts) {
                      System.out.println("Retrying Connection in 5 seconds...");
                      TimeUnit.SECONDS.sleep(5);
                  } else {
                      e.printStackTrace();
                      throw e;
                  }
              } catch (Exception e) {
                  e.printStackTrace();
                  throw e;
              }
          }
      }
  }
  /*
  Static method that returns a new Mysql connection
   */
  public static Connection getConnection() throws SQLException, InterruptedException {
      createConnection();
    return conn;
    }

  public void closeConnection() throws SQLException {
      if(conn != null) {
          conn.close();
      }
  }

}
