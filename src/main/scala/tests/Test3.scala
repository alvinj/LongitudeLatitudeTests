package tests

import scala.swing._
import scala.swing.event.EditDone
import scala.swing.event.WindowActivated
import java.awt.Color
import scala.swing.event.WindowClosing
import javax.swing.SwingUtilities
import java.awt.Toolkit
import java.awt.BorderLayout

/**
 * Draw a map of the U.S. by drawing a point for each long/lat pair.
 */
object Test3 extends SimpleSwingApplication { 

  val heightInPixels = 600
  val widthInPixels = 800
  val myPanel = new MyPanel(widthInPixels, heightInPixels)

  def top = new MainFrame {
    title = "Test 1"
    peer.add(myPanel)
    peer.pack
    reactions += {
      case WindowClosing(_) => quit
    }
  }

  override def main(args: Array[String]) {
    // load the data
    val latLongSet = Utils.getLatLongSetFromZipCodeFile
    
    // draw one dot for each lat/long pair
    SwingUtilities.invokeLater(new Runnable {
      def run {
        val f = top
        f.peer.setLocationRelativeTo(null)
        f.peer.setVisible(true)
        for {
          point <- latLongSet
          x = determineX(point._2)
          y = determineY(point._1)
        } myPanel.drawDot(x, y)
      }
    })
  }
  
  /**
   * Longitude is the x-axis. The data range is about -180 to -50.
   */
  private def determineX(long: Double) = {                  // ex: long = -60
    val x0 = -180
    val xmax = -50
    val longRange = xmax - x0                               // -50 - -180 ~> (-50 + 180) = 130
    val delta = long - x0                                   // -60 - -180 ~> (-60 + 180) = 120 
    val relativeDistanceFromX0 = delta / longRange          // 120 / 130 = 12/13
    val distance = relativeDistanceFromX0 * widthInPixels   // (12/13) * 800 = // point will be on far-right of graph
    distance.round.toInt
  }

  /**
   * Latitude is the y-axis. The data range is between 10 and 80.
   */
  private def determineY(lat: Double) = {
    val y0 = 80
    val ymax = 10
    val latRange = y0 - ymax
    val delta = y0 - lat
    val relativeDistanceFromY0 = delta / latRange
    val distance = relativeDistanceFromY0 * heightInPixels
    distance.round.toInt
  }

}











