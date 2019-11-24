package project.backend.database

import java.sql.{Connection, DriverManager, ResultSet}

import javafx.event.{ActionEvent, EventHandler}
import scalafx.scene.control.TextField

class Register(inputDisplay: TextField, outputDisplay: TextField) extends EventHandler[ActionEvent] {


  val url = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username = "root"
  val password = "barbequed96"
  var connection: Connection = DriverManager.getConnection(url, username, password)

  setupTable()

  def setupTable(): Unit = {
    val statement = connection.createStatement()
    statement.execute("CREATE TABLE IF NOT EXISTS people (username TEXT, password TEXT)")
  }

  override def handle(event: ActionEvent): Unit = {
    setupTable()
    var name1 = inputDisplay.text.value
    var hiddenWord2 = outputDisplay.text.value
    //checking the minimum length and maximum length required
    if(name1.length <=6){
      println("Password too short")
    }

    if(hiddenWord2.length <= 6){
      println("Username too short")
    }

    if(name1.isEmpty && hiddenWord2.isEmpty){
      println("Username and password  is empty ")
    }

    /*if(hiddenWord2.isEmpty){
      println("Password too short")
    }*/

    else{
      Register(name1,hiddenWord2)
      println("Register completed!")
    }
  }


  def peopleExists(username: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM people WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()
    result.next()
  }


  def Login(username: String, password: String): Unit = {
    val statement = connection.prepareStatement("SELECT * FROM people WHERE username=? AND password=?")
    statement.setString(1, username)
    statement.setString(2, password)
    val result: ResultSet = statement.executeQuery()
    result.next()
  }

  def Register(username: String, password : String): Unit = {
    val statement = connection.prepareStatement("INSERT INTO people VALUE (?, ?)")
    statement.setString(1, username)
    statement.setString(2, password)
    statement.execute()
  }

}