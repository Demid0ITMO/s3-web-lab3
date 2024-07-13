package itmo.web.lab3
import jakarta.persistence.Persistence

class DBManager {
    private val entityManagerFactory = Persistence.createEntityManagerFactory("DEMID")
    private val entityManager = entityManagerFactory.createEntityManager()

    fun getHistory() = entityManager.createQuery("select p from Point p").resultList.map { it as Point } as ArrayList<Point>

    fun addPoint(point: Point): Boolean {
        try {
            entityManager.transaction.begin()
            entityManager.persist(point)
            entityManager.transaction.commit()
            return true
        } catch (e: Exception) {
            println(e.message)
            return false
        }
    }

    fun clearHistory(): Boolean {
        try {
            entityManager.transaction.begin()
            entityManager.createQuery("delete from Point").executeUpdate()
            entityManager.transaction.commit()
            return true
        } catch (e: Exception) {
            println(e.message)
            return false
        }
    }
}