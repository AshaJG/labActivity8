package project.backend

import java.sql.{Connection, DriverManager, ResultSet}

object Database  {
  var url = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  var username = "root"
  val password = "barbequed96"

  var connection : Connection = DriverManager.getConnection(url, username, password)

  def setUpTable() : Unit = {
    val statement = connection.createStatement()
    //create my table if it doesnt exist
    statement.execute("CREATE TABLE IF NOT EXISTS players(username String ,password String ,gameState String )")

  }
  def playerExists(username : String) : Unit = {
    val statement = connection.prepareStatement("INSERT INTO players VALUE(?, ? )")
    statement.setString(1,username)
   // val newGame : String =
    statement.execute()
  }

  def saveGameSate(username : String , gameState : String ): Unit = {
    val statement = connection.prepareStatement("UPDATE players set gameState = ? WHERE username = ? ")
    statement.setString(1, gameState )
    statement.setString(1, gameState)
    statement.execute()
  }

  def loadGameState(username : String ): String = {
    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")
    statement.setString(1, username)
    val result : ResultSet = statement.executeQuery()
    result.next()
    result.getString("gameSate")
  }




}
