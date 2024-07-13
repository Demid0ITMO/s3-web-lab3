package itmo.web.lab3

import jakarta.enterprise.context.SessionScoped
import jakarta.inject.Named
import java.io.Serializable


@Named
@SessionScoped
class YBean : Serializable {
    var value = ""
    val minimum = -3.0
    val maximum = 5.0
    val message = "Выберите корректный Y (-3..5)"
    fun isValid() = value.isNotBlank() && value.isNotEmpty() && value.toDouble() in minimum..maximum
}