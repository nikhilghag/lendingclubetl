package com.nikhilghag.service

import org.apache.spark.sql.{DataFrame, SaveMode}

object Loader {

  def save(df: DataFrame, path: String, numPartitions: Int = 1) {
    df.coalesce(numPartitions).write.mode(SaveMode.Overwrite).parquet(path)
  }
}
