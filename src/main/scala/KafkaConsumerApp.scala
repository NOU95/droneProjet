import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

import java.io._
import java.text.SimpleDateFormat
import java.util
import java.util.{Calendar, Properties}
import scala.collection.JavaConverters._

object KafkaConsumerApp extends App {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "something")

  val kafkaConsumer = new KafkaConsumer[String, String](props)
  kafkaConsumer.subscribe(util.Collections.singletonList("rapport_texte"))
  val format = new SimpleDateFormat("d_M_y")
  def printer_records(records: ConsumerRecords[String, String]): Unit = {
    val rec = records.asScala.head.value()
    println(rec)
    val wr = new PrintWriter(new FileOutputStream(new File("rapport_"+format.format(Calendar.getInstance().getTime()).toString+".csv"),true))
    wr.write(rec+"\n")
    wr.close
  }

  def records_val() {
    val records = kafkaConsumer.poll(5000)
    printer_records(records)
    Thread.sleep(5000L)

    records_val()
  }
  records_val()

}
