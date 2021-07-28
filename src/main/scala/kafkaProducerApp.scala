import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties

object kafkaProducerApp extends App {
  
  // Les propriétés de KafkaProducer
  val props:Properties = new Properties()
  props.put("bootstrap.servers","localhost:9092")
  props.put("key.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks","all")

  //Instantiate producer
  val producer = new KafkaProducer[String, String](props)

  // Name of the topic that will be used to put messages
  val topic = "rapport_texte"


  def generer_rapport(p: KafkaProducer[String, String]) {
    val d1 = RapportDrone.reponserandom()
    val report = new ProducerRecord[String, String](topic,
      d1._id.toString + "/"
        + d1._latitude + "/"
        + d1._longitude + "/"
        + d1._alentours.toString + "/"
        + d1._words.toString + "/"
        + d1._day + "/"
        + d1._alentours.exists((t) => t._2 < 30) + ";")
    print(report, "\n")
    val metadata = p.send(report)
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



