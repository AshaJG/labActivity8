package project.backend.database

import java.sql.{Connection, DriverManager, ResultSet}

import javafx.event.{ActionEvent, EventHandler}
import org.mindrot.jbcrypt.{BCrypt => B}
import play.api.libs.json.{JsValue, Json}
import scalafx.scene.control.TextField

class Login(inputDisplay: TextField, outputDisplay: TextField) extends EventHandler[ActionEvent] {

  val url = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username = "root"
  val password = "barbequed96"
  var connection: Connection = DriverManager.getConnection(url, username, password)

  setupTable()

  def setupTable(): Unit = {
    val statement = connection.createStatement()
    statement.execute("CREATE TABLE IF NOT EXISTS people (username TEXT, password TEXT)")
  }



  def peopleExists(username: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM people WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()
    result.next()
  }


  def login(username: String, password: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM people WHERE username=? AND password=?")
    statement.setString(1, username)
    statement.setString(2, password)
    val result: ResultSet = statement.executeQuery()
    result.next()
  }

  var JsonInfo : Map[String,JsValue] = Map("user" -> Json.toJson("SAVED"),"account"-> Json.toJson("MADE"),  "gameSate"-> Json.toJson("something Happened"))

  val userInfo = Json.toJson(JsonInfo)
  val output = Json.stringify(userInfo)


  //connecting the info from the userbox and password
  //salt the passwords
  override def handle(event: ActionEvent): Unit = {
    val name : String  = inputDisplay.text.value
    val hiddenWord : String  = outputDisplay.text.value

    if (name.isEmpty && hiddenWord.isEmpty){
      println("the username and the password is empty")
    }
    else

    if (login(name , hiddenWord )){
      var salting   = B.gensalt()
      var hashing  = B.hashpw(hiddenWord, salting)
      println("Login was successful")
      println("Account User Info :")
      println(name)
      println("password hashed and salted: " +hashing)
      println(output)
    }


  }







}