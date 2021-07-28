import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties

object KafkaProducerReponseAlerte extends App {

  // Les propriétés de KafkaProducer
  val props:Properties = new Properties()
  props.put("bootstrap.servers","localhost:9092")
  props.put("key.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks","all")

  //Instancier le producer
  val producer = new KafkaProducer[String, String](props)

  // Le nom du topic qui est utilisé pour mettre un message
  val topic = "topic_alerte"

  // générer mon rapport
  def generer_rapport(p: KafkaProducer[String, String]) {
    val d1 = RapportDrone.reponserandom()

    val alert = d1._alentours.filter((t) => t._2 < 50)
    val alert_report = new ProducerRecord[String, String](topic ,
     // partition_alert ,
      d1._latitude + "/"
        + d1._longitude + "/"
        + alert.keys + "/"
        + alert.values + ";")

    print(alert_report,"\n")

    if (alert.keys != Set.empty) {
      val metadata_alert = p.send(alert_report)
    }

    Thread.sleep(3000L)
    generer_rapport(p)

  }
  try {
    generer_rapport(producer)
  }
  catch {
    case e:Exception => e.printStackTrace()
  } finally {
    producer.close()

  }

}
