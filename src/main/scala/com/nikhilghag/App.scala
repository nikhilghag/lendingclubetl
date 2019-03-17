package com.nikhilghag

import com.nikhilghag.service.{Extractor, Loader, Transformer}
import org.apache.log4j.{Level, Logger, Priority}
import org.apache.spark.sql.SparkSession

import scala.util.{Failure, Success}

object App {
  def main(args: Array[String]): Unit = {

    val logger = Logger.getLogger(classOf[App])
    logger.setLevel(Level.INFO)

    val sparkSession = SparkSession.builder().appName("lendingclub-etl").master("local").getOrCreate()
    sparkSession.sparkContext.setLogLevel("ERROR")

    logger.info("Reading File " + args(0))
    val dataFrame = Extractor.extract(sparkSession, args(0))
    logger.info("File Read Successfully" + args(0))

    logger.info("Dropping Columns")
    val dropped = Transformer.dropColumns(dataFrame, "policy_code", "zip_code", "url", "id")
    logger.info("Dropped Columns")

    logger.info("Adding Issue Year column")
    val transformed = Transformer.addIssueYear(dropped.get, "issue_year", "issue_d", "-")
    logger.info("Added Issue Year column")

    logger.info("Replacing Does not meet the credit policy. Status:")
    val replaced = Transformer.replaceValues(transformed.get, "loan_status_new", "loan_status",
      "Does not meet the credit policy. Status:", "")
    logger.info("Replaced Does not meet the credit policy. Status:")


    logger.info("Saving Transformed DF in Parquet Format")
    Loader.save(replaced.get, args(1))
    logger.info("Saved Transformed DF in Parquet Format")

    sparkSession.close()

  }


}
