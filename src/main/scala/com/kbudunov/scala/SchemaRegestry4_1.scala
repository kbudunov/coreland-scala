//package com.kbudunov.scala
//
//object SchemaRegestry4_1 {
//
//  private var schemaRegistryClient: SchemaRegistryClient = _
//
//  def main(args: Array[String]): Unit = {
//    val spark = SparkSession.builder()
//      .appName(SchemaRegestry3.getClass.getName)
//      .master("local[2]")
//      .getOrCreate()
//
//    val bootstrapServers = "localhost:9092"
//    val topic = "users"
//    val schemaRegistryUrl = "http://localhost:8081"
//
//    consumeAvro(spark, bootstrapServers, topic, schemaRegistryUrl)
//
//    spark.streams.awaitAnyTermination()
//  }
//
//  private def consumeAvro(spark: SparkSession, bootstrapServers: String, topic: String, schemaRegistryUrl: String): Unit = {
//    schemaRegistryClient = new CachedSchemaRegistryClient(schemaRegistryUrl, 128)
//
//    val rawDf = spark.readStream
//      .format("kafka")
//      .option("kafka.bootstrap.servers", bootstrapServers)
//      .option("includeHeaders", "true")
//      .option("subscribe", topic)
//      .load()
//
//
//    val resultDFs = lookupTopicSchemas(topic)
//      .map { case (versionId, schemaAvro) =>
//        val df = rawDf
//          .select(
//            col("key").cast(DataTypes.StringType).as("key"),
//            col("partition").cast(DataTypes.StringType).as("partition"),
//            col("offset").cast(DataTypes.StringType).as("offset"),
//            col("value").as[Array[Byte]],
//            col("headers").as[Array[Byte]],
//          )
//          .where(col("key").cast(DataTypes.StringType) === s"$versionId")
//          .select(
//            col("key").cast(DataTypes.StringType).as("key"),
//            col("partition").cast(DataTypes.StringType).as("partition"),
//            col("offset").cast(DataTypes.StringType).as("offset"),
//            from_avro(col("value"), schemaAvro).as("val")
//          ).select(
//            col("key"),
//            col("partition").cast(DataTypes.StringType).as("partition"),
//            col("offset").cast(DataTypes.StringType).as("offset"),
//            //          map_from_arrays(col("headers.key"), col("headers.value")),
//            col("val.*")
//        )
//        versionId -> df
//      }
//
//    resultDFs
//      .foreach { case (versionId, df) =>
//        println(versionId)
//        df.writeStream
//          .format("csv")
//          .option("path", s"user_$versionId")
//          .option("checkpointLocation", s"C:\\projects\\spark-basic-hw_$versionId")
//          .start()
//      }
//  }
//
//  private def lookupTopicSchemas(subjectId: String, isKey: Boolean = false): Map[Int, String] = {
//    val topicId = subjectId + (if (isKey) "-key" else "-value")
//    val versionIds = schemaRegistryClient.getAllVersions(topicId).toList
//    val schemas = versionIds.map(schemaRegistryClient.getSchemaMetadata(topicId, _).getSchema)
//    versionIds.map(_.intValue).zip(schemas).toMap
//  }
//
//}
//
