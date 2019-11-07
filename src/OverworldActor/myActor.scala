package OverworldActor
import akka.actor._
//todo WHATS THE DIFFERENCE BETWEEN THE OBJECT AND THE CLASS



class myActor extends Actor   {
  var roster:List[String]= List()
  def receive : Receive ={
    case receive: Receive  =>
      {
        case AddParty(x) =>
        { println(x)
        }
          case MoveParty(PartyID : Int , x : Double , y : Double ) =>
          {
            x+y // throaway code
          }
          case RemoveParty =>
          {//do nothing
          }

        sender() ! receive
      }

  }



}
