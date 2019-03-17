package com.nikhilghag.service


import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, split, regexp_replace}
import scala.util.Try

object Transformer {

  def addIssueYear(dataFrame: DataFrame,
                   newColumnName: String,
                   extractYearFrom: String,
                   regex: String
                  ): Try[DataFrame] = {
    Try {
      dataFrame.withColumn(newColumnName, split(col(extractYearFrom), regex)(1))
    }
  }

  def dropColumns(dataFrame: DataFrame, columns: String*): Try[DataFrame] = {
    Try {
      dataFrame.drop(columns: _*)
    }
  }

  def replaceValues(dataFrame: DataFrame,
                    newColumnName: String,
                    columnToSearch: String,
                    regex: String,
                    replaceText: String): Try[DataFrame] = {
    Try {
      dataFrame.withColumn("loan_status_new",
        regexp_replace(col("loan_status"), "Does not meet the credit policy. Status:", ""))
    }
  }

}
