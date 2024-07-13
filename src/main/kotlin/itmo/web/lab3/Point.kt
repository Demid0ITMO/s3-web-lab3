package itmo.web.lab3

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name="points")
class Point(
    var x: Double = .0,
    var y: Double = .0,
    var r: Double = .0,
    var currentTime: String = "",
    var result: Boolean = false
) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    fun getColor() = if (result) "green" else "red"
}