package OverworldActor
case class AddParty(PartyID : Int )
case class RemoveParty(PartyID : Int )
case class Update(timestamp : Long )
case class BattleEnded(WinningPartyID : Int , LoosingParty : Int )
case class MoveParty(PartyID : Int , x : Double , y : Double )
case class StopParty(PartyID : Int)
case class AddPerson(personName:String)


class MessageType {

}
