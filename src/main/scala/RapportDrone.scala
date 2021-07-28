import faker._

object RapportDrone {
  // créer une case class qui rassemble les infos du rapport
  case class Rapport(_id : Int, _latitude : String, _longitude : String, _alentours : Map[String, Int], _words : List[String], _day : String ) {
    val id = _id
    val latitude = _latitude
    val longitude = _longitude
    val alentours = _alentours
    val words = _words
    val day = _day
  }
  // définir une méthode qui renvoie une réponse random :
  def reponserandom() = {
    val r = scala.util.Random
    val n = scala.util.Random.nextInt(20)
    val m = scala.util.Random.nextInt(10)
    val list_day = List("Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    // un jour sur 7
    val rday = r.nextInt(7)
    Rapport(r.nextInt(100000),
      Address.latitude,
      Address.longitude,
      List.fill(m)((Name.name, r.nextInt(100))).toMap,
      Lorem.words(n),
      list_day(rday)
    )
  }

  def dronedisp(d: Rapport) = {
    val rep = "id = " + d._id.toString + " latitude = " + d._latitude + " longitude = " + d._longitude + " alentours = " + d._alentours.toString + " words = " + d._words.toString+" day = "+d._day.toString
    println(rep)
  }
}

