package converter

import converter.Unit.*
import java.math.BigDecimal

fun toSeconds(value: BigDecimal, unit: Unit): BigDecimal {
    if (value < ZERO) throw IllegalArgumentException()
    return when (unit) {
        SECOND -> value
        WEEK -> value * bD(604800.0)
        DAY -> value * bD(86400.0)
        HOUR -> value * bD(3600.0)
        MINUTE -> value * bD(60.0)
        MILLISECOND -> value / bD(1000.0)
        MICROSECOND -> value / bD(1e+6)
        NANOSECOND -> value / bD(1e+9)
        else -> throw IllegalArgumentException()
    }
}

fun secondsTo(value: BigDecimal, unit: Unit): BigDecimal {
    if (value < ZERO) throw IllegalArgumentException()
    return when (unit) {
        SECOND -> value
        WEEK -> value / bD(604800.0)
        DAY -> value / bD(86400.0)
        HOUR -> value / bD(3600.0)
        MINUTE -> value / bD(60.0)
        MILLISECOND -> value * bD(1000.0)
        MICROSECOND -> value * bD(1e+6)
        NANOSECOND -> value * bD(1e+9)
        else -> throw IllegalArgumentException()
    }
}

fun toMeters(value: BigDecimal, unit: Unit): BigDecimal {
    if (value < ZERO) throw IllegalArgumentException()
    return when (unit) {
        METER -> value
        KILOMETER -> value * bD(1000.0)
        CENTIMETER -> value * bD(0.01)
        MILLIMETER -> value * bD(0.001)
        MILE -> value * bD(1609.35)
        YARD -> value * bD(0.9144)
        FOOT -> value * bD(0.3048)
        INCH -> value * bD(0.0254)
        else -> throw IllegalArgumentException()
    }
}

fun metersTo(value: BigDecimal, unit: Unit): BigDecimal {
    if (value < ZERO) throw IllegalArgumentException()
    return when (unit) {
        METER -> value
        KILOMETER -> value / bD(1000.0)
        CENTIMETER -> value / bD(0.01)
        MILLIMETER -> value / bD(0.001)
        MILE -> value / bD(1609.35)
        YARD -> value / bD(0.9144)
        FOOT -> value / bD(0.3048)
        INCH -> value / bD(0.0254)
        else -> throw IllegalArgumentException()
    }
}

fun toGrams(value: BigDecimal, unit: Unit): BigDecimal {
    if (value < ZERO) throw IllegalArgumentException()
    return when (unit) {
        GRAM -> value
        KILOGRAM -> value * bD(1000.0)
        MILLIGRAM -> value * bD(0.001)
        POUND -> value * bD(453.592)
        OUNCE -> value * bD(28.3495)
        else -> throw IllegalArgumentException()
    }
}

fun gramsTo(value: BigDecimal, unit: Unit): BigDecimal {
    if (value < ZERO) throw IllegalArgumentException()
    return when (unit) {
        GRAM -> value
        KILOGRAM -> value / bD(1000.0)
        MILLIGRAM -> value / bD(0.001)
        POUND -> value / bD(453.592)
        OUNCE -> value / bD(28.3495)
        else -> throw IllegalArgumentException()
    }
}

fun celsiusTo(value: BigDecimal, unit: Unit): BigDecimal {
    return when (unit) {
        CELSIUS -> value
        FAHRENHEIT -> celsiusToFahrenheit(value)
        KELVIN -> celsiusToKelvin(value)
        else -> throw IllegalArgumentException()
    }
}

fun fahrenheitTo(value: BigDecimal, unit: Unit): BigDecimal {
    return when (unit) {
        FAHRENHEIT -> value
        CELSIUS -> fahrenheitToCelsius(value)
        KELVIN -> fahrenheitToKelvin(value)
        else -> throw IllegalArgumentException()
    }
}

fun kelvinTo(value: BigDecimal, unit: Unit): BigDecimal {
    return when (unit) {
        KELVIN -> value
        CELSIUS -> kelvinToCelsius(value)
        FAHRENHEIT -> kelvinToFahrenheit(value)
        else -> throw IllegalArgumentException()
    }
}

fun celsiusToFahrenheit(value: BigDecimal): BigDecimal = value * bD(9.0) / bD(5.0) + bD(32)

fun celsiusToKelvin(value: BigDecimal): BigDecimal = value + bD(273.15)

fun fahrenheitToCelsius(value: BigDecimal): BigDecimal = (value - bD(32)) * bD(5.0) / bD(9.0)

fun fahrenheitToKelvin(value: BigDecimal): BigDecimal = (value + bD(459.67)) * bD(5.0) / bD(9.0)

fun kelvinToCelsius(value: BigDecimal): BigDecimal = value - bD(273.15)

fun kelvinToFahrenheit(value: BigDecimal): BigDecimal = value * bD(9.0) / bD(5.0) - bD(459.67)

fun toHertz(value: BigDecimal, unit: Unit): BigDecimal {
    if (value < ZERO) throw IllegalArgumentException()
    return when (unit) {
        HERTZ -> value
        KILOHERTZ -> value * bD(1000.0)
        MEGAHERTZ -> value * bD(1e+6)
        GIGAHERTZ -> value * bD(1e+9)
        else -> throw IllegalArgumentException()
    }
}

fun hertzTo(value: BigDecimal, unit: Unit): BigDecimal {
    if (value < ZERO) throw IllegalArgumentException()
    return when (unit) {
        HERTZ -> value
        KILOHERTZ -> value / bD(1000.0)
        MEGAHERTZ -> value / bD(1e+6)
        GIGAHERTZ -> value / bD(1e+9)
        else -> throw IllegalArgumentException()
    }
}
