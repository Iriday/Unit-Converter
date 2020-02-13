package converter

import converter.Unit.*

fun toMeters(value: Double, unit: Unit): Double {
    return when (unit) {
        METER -> value
        KILOMETER -> value * 1000
        CENTIMETER -> value * 0.01
        MILLIMETER -> value * 0.001
        MILE -> value * 1609.35
        YARD -> value * 0.9144
        FOOT -> value * 0.3048
        INCH -> value * 0.0254
        else -> -1.0
    }
}

fun metersTo(value: Double, unit: Unit): Double {
    return when (unit) {
        METER -> value
        KILOMETER -> value / 1000.0
        CENTIMETER -> value / 0.01
        MILLIMETER -> value / 0.001
        MILE -> value / 1609.35
        YARD -> value / 0.9144
        FOOT -> value / 0.3048
        INCH -> value / 0.0254
        else -> -1.0
    }
}

fun toGrams(value: Double, unit: Unit): Double {
    return when (unit) {
        GRAM -> value
        KILOGRAM -> value * 1000
        MILLIGRAM -> value * 0.001
        POUND -> value * 453.592
        OUNCE -> value * 28.3495
        else -> -1.0
    }
}

fun gramsTo(value: Double, unit: Unit): Double {
    return when (unit) {
        GRAM -> value
        KILOGRAM -> value / 1000.0
        MILLIGRAM -> value / 0.001
        POUND -> value / 453.592
        OUNCE -> value / 28.3495
        else -> -1.0
    }
}

fun celsiusTo(value: Double, unit: Unit): Double {
    return when (unit) {
        CELSIUS -> value
        FAHRENHEIT -> celsiusToFahrenheit(value)
        KELVIN -> celsiusToKelvin(value)
        else -> Double.MIN_VALUE
    }
}

fun fahrenheitTo(value: Double, unit: Unit): Double {
    return when (unit) {
        FAHRENHEIT -> value
        CELSIUS -> fahrenheitToCelsius(value)
        KELVIN -> fahrenheitToKelvin(value)
        else -> Double.MIN_VALUE
    }
}

fun kelvinTo(value: Double, unit: Unit): Double {
    return when (unit) {
        KELVIN -> value
        CELSIUS -> kelvinToCelsius(value)
        FAHRENHEIT -> kelvinToFahrenheit(value)
        else -> Double.MIN_VALUE
    }
}

fun celsiusToFahrenheit(value: Double): Double = value * 9 / 5 + 32

fun celsiusToKelvin(value: Double): Double = value + 273.15

fun fahrenheitToCelsius(value: Double): Double = (value - 32) * 5 / 9

fun fahrenheitToKelvin(value: Double): Double = (value + 459.67) * 5 / 9

fun kelvinToCelsius(value: Double): Double = value - 273.15

fun kelvinToFahrenheit(value: Double): Double = value * 9 / 5 - 459.67
