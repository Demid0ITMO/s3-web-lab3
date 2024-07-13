package itmo.web.lab3

import jakarta.enterprise.context.SessionScoped
import jakarta.faces.context.FacesContext
import jakarta.inject.Named
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Named
@SessionScoped
class HitChecker: Serializable {
    val historyManager = HistoryManager()
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    fun check(v: Boolean, x: Double, y: Double, r: Double) {
        if (!v) return
        println("$x, $y, $r")
        val point = Point(x, y, r, LocalDateTime.now().format(formatter).toString(), isInArea(x, y, r))
        historyManager.addPoint(point)
    }
    fun checkFromSVG() {
        val params = FacesContext.getCurrentInstance().externalContext.requestParameterMap
        try {
            val x = params["x"]!!.toDouble()
            val y = params["y"]!!.toDouble()
            val r = params["r"]!!.toDouble()
            check(true, x, y, r)
        } catch (e: NullPointerException) {
            println(e.message)
            return
        } catch (e: NumberFormatException) {
            println(e.message)
            return
        }
    }
    fun getHistory() = historyManager.getHistory()
    fun clearHistory() = historyManager.clearHistory()

    private fun isInArea(x: Double, y: Double, r: Double) = isInTriangle(x, y, r) || isInCircle(x, y, r) || isInRectangle(x, y, r)
    private fun isInRectangle(x: Double, y: Double, r: Double) = (x in -r..0.0) && (y in 0.0..r)
    private fun isInCircle(x: Double, y: Double, r: Double) = (x*x + y*y <= r*r/4) && (y <= 0) && (x >= 0)
    private fun isInTriangle(x: Double, y: Double, r: Double) = (y >= - x / 2 - r / 2) && (y <= 0) && (x <= 0)
}