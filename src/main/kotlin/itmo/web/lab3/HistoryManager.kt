package itmo.web.lab3

class HistoryManager {
    private val dbManager = DBManager()
    private val history = dbManager.getHistory()

    fun addPoint(point: Point) {
        if (dbManager.addPoint(point)) history.add(point)
    }

    fun getHistory() = history

    fun clearHistory() {
        if (dbManager.clearHistory()) history.clear()
    }

}