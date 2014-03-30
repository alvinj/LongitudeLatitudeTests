package tests

import scala.collection.mutable.ArrayBuffer

/**
 * A test of reading the "zipcode_data.csv" file.
 * Format: "zip_code","latitude","longitude","city","state","county"
 * Note: latitude and longitude are repeated for zip codes (multiple zips have the same lat/long)
 */
object Test2 extends App {

  // create a set of latitude/longitude pairs from the csv file
  val latLongSet = Utils.getLatLongSetFromZipCodeFile

  println(s"#elems = ${latLongSet.size}")  
  println(latLongSet.take(1))

  val lats = for (e <- latLongSet) yield e._1
  val longs = for (e <- latLongSet) yield e._2

  // min lat:  17.734211
  // max lat:  48.943793
  // min long: -99.998438
  // max long: -64.734694
  println(s"min lat:  ${lats.min}")
  println(s"max lat:  ${lats.max}")
  println(s"min long: ${longs.min}")
  println(s"max long: ${longs.max}")
    
  
}


