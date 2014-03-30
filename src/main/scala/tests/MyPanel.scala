package tests

import javax.swing.JPanel
import java.awt.{Color, Graphics}
import scala.util.Random
import java.awt.Point
import java.awt.Dimension

class MyPanel(val panelWidth: Int, val panelHeight: Int) extends JPanel {

  setPreferredSize(new Dimension(panelWidth, panelHeight))
  // all the dots that need to be drawn
  private val dotLocations = new scala.collection.mutable.ArrayBuffer[Point]()
  val ballDiameter = 2
  val halfPanelWidth = panelWidth / 2
  val halfPanelHeight = panelHeight / 2
  var color = Color.YELLOW
  private val r = new Random
  setBackground(Color.BLACK)
  
  def drawDot(x: Int, y: Int) {
    dotLocations += new Point(x, y)
    repaint()
  }
  
//  def drawRandomDot {
//    var x1 = r.nextInt(halfPanelWidth) + halfPanelWidth
//    if (x1 > (panelWidth-ballDiameter)) x1 = panelWidth-ballDiameter
//    dotLocations += new Point(x1, getRandomY)
//    repaint()
//  }
//  
//  def getRandomY = {
//    var y = r.nextInt(panelHeight)
//    if (y < ballDiameter) y = ballDiameter / 2
//    if (y > (panelHeight-ballDiameter)) y = panelHeight-2*ballDiameter
//    y
//  }

  override def paintComponent(g: Graphics) {
    super.paintComponent(g)
    g.setColor(color)
    for (p <- dotLocations) {
      g.fillOval(p.x, p.y, ballDiameter, ballDiameter)
    }
  }

}