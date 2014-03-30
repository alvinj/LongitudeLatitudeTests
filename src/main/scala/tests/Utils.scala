package tests

import java.io.File

object Utils {

  /**
   * The set will contain fewer records than the data file because 
   * (a) some records are bad, and (b) multiple zip codes can have
   * the same lat/long values, so the lat/long values are duplicated,
   * and removed here by the use of a Set.
   */
  def getLatLongSetFromZipCodeFile = {
    val file = new File(getClass.getResource("zipcode_data.csv").toURI)
    
    // create a set of latitude/longitude pairs from the csv file
    val latLongSet = scala.collection.mutable.Set[(Double, Double)]()
    using(io.Source.fromFile(file)) { source =>
      for (line <- source.getLines.drop(1)) {
        val rec = line.split(",").map(_.trim)
        try {
          latLongSet += Tuple2(
              rec(1).replaceAll("\"", "").toDouble, 
              rec(2).replaceAll("\"", "").toDouble)
        } catch {
          case e: Exception => // println(e) 
        }      
      }
    }
    latLongSet.toSet
  }

  private def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B = {
    try {
      f(resource)
    } finally {
      resource.close
    }
  }

  
}