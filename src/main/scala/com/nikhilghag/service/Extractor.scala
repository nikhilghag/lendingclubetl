package com.nikhilghag.service

import org.apache.spark.sql.{DataFrame, SparkSession}

object Extractor {
  def extract(spark: SparkSession, path: String): DataFrame = {
    spark.read.option("header", "true").csv(path)
  }
}
