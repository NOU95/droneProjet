import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.TopicPartition
import java.io.{File, FileOutputStream, PrintWriter}
import java.text.SimpleDateFormat
import java.util
import java.util.{Calendar, Properties}

import KafkaConsumerApp.{format, kafkaConsumer}

import scala.collection.JavaConverters._

object KafkaConsumerAlerte extends App {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "something")
  // I need to read data from kafka and to subscribe to kafka topics
  val kafkaConsumer = new KafkaConsumer[String, String](props)
  // assigner mon tpoic "topic_alerte"
  kafkaConsumer.subscribe(util.Collections.singletonList("topic_alerte"))

  val format = new SimpleDateFormat("d_M_y")


  def printer_records(records: ConsumerRecords[String, String]): Unit = {
    val rec = records.asScala.head.value()
    println(rec)
    val wr = new PrintWriter(new FileOutputStream(new File("alert_"+format.format(Calendar.getInstance().getTime()).toString+".csv"),true))
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
