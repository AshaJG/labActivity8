package project.frontend
import project.backend.database.{Login, Register}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color


object GUILogin extends JFXApp {

  val windowWidth : Double = 800
  val windowHeight : Double = 600

  val Username : TextField = new TextField {
    editable = true
    text = "Username"
    style = "-fx-font: 18 ariel;"
  }
  val Password: TextField = new TextField {
    editable = true
    text = "Password"
    style = "-fx-font: 18 ariel;"
  }

  val Login: Button = new Button {
    minWidth = 100
    minHeight = 100
    style = "-fx-font: 28 ariel;"
    text = "Login"
    onAction = new Login(Username, Password)
  }
  val Register: Button = new Button {
    minWidth = 100
    minHeight = 100
    style = "-fx-font: 28 ariel;"
    text = "Register"
    onAction = new Register(Username, Password)
  }


  val verticalBox: VBox = new VBox() {
    children = List(Username,Password ,Login, Register)
  }

  this.stage = new PrimaryStage {
    title = "Enter the Game  "
    scene = new Scene(windowHeight,windowWidth) {
      fill = Color.Lavender
      content = List(verticalBox)
    }
  }
}
