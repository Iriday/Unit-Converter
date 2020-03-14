package converter

import java.math.BigDecimal

private val SCALE = 100

val ZERO: BigDecimal = BigDecimal.ZERO
val ONE: BigDecimal = BigDecimal.ONE
val TWO: BigDecimal = BigDecimal(2)

fun bD(value: Double): BigDecimal = BigDecimal(value)

fun bD(value: Long): BigDecimal = BigDecimal(value)

fun bD(value: Int): BigDecimal = BigDecimal(value)

fun bD(value: String): BigDecimal = BigDecimal(value).setScale(SCALE)

private val regexTrailingZeros = Regex("[.]?[0]+$")
fun removeTrailingZeros(value: BigDecimal): BigDecimal {
    return if (value.toString().contains('.'))
        BigDecimal(value.toString().replace(regexTrailingZeros, ""))
    else value
}
