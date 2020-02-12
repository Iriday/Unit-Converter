package converter

import converter.Unit.*

fun main() {
    Main().run()
}

private val regexInput =
    Regex("(?i) *-?(\\d+|\\d+.\\d+) +([a-z]+|degree[s]? +(celsius|fahrenheit)) +[a-z]+ +([a-z]+|degree[s]? +(celsius|fahrenheit)) *")

private val regexSplit = Regex("\\s+(degree[s]?\\s+)?")

class Main {
    fun run() {
        while (true) {
            print("Enter what you want to convert (or exit): ")

            val notParsedInput = readLine()!!.trim()
            if (notParsedInput.toLowerCase() == "exit") {
                return
            }
            val input = parseInput(notParsedInput)
            if (input.isEmpty()) {
                println("Parse error")
                continue
            }

            val inValue = input[0].toDouble()
            val enumInUnit = Unit.getUnit(input[1])
            val enumOutUnit = Unit.getUnit(input[2])


            val outValue = when (enumInUnit) {
                METER, KILOMETER, CENTIMETER, MILLIMETER, MILE, YARD, FOOT, INCH -> {
                    metersTo(toMeters(inValue, enumInUnit), enumOutUnit)
                }
                GRAM, KILOGRAM, MILLIGRAM, POUND, OUNCE -> {
                    gramsTo(toGrams(inValue, enumInUnit), enumOutUnit)
                }
                CELSIUS -> celsiusTo(inValue, enumOutUnit)
                FAHRENHEIT -> fahrenheitTo(inValue, enumOutUnit)
                KELVIN -> kelvinTo(inValue, enumOutUnit)

                else -> Double.MIN_VALUE //String
            }

            println(createOutputString(enumInUnit, inValue, enumOutUnit, outValue))
        }
    }

    private fun parseInput(input: String): List<String> {

        if (input.matches(regexInput)) {
            val list = input.split(regexSplit)
            return listOf(list[0], list[1], list[3])
        }
        return emptyList()
    }
}

fun createOutputString(inUnit: Unit, inValue: Double, outUnit: Unit, outValue: Double): String {
    val iUnit = convertTypeName(inUnit, inValue)
    val oUnit = convertTypeName(outUnit, outValue)

    return "$inValue $iUnit is $outValue $oUnit"
}

fun convertTypeName(unit: Unit, value: Double): String {
    val s = s(value)
    return when (unit) {
        // length
        METER -> "meter$s"
        KILOMETER -> "kilometer$s"
        CENTIMETER -> "centimeter$s"
        MILLIMETER -> "millimeter$s"
        MILE -> "mile$s"
        YARD -> "yard$s"
        FOOT -> if (value == 1.0) "foot" else "feet"
        INCH -> if (value == 1.0) "inch" else "inches"
        // weight
        GRAM -> "gram$s"
        KILOGRAM -> "kilogram$s"
        MILLIGRAM -> "milligram$s"
        POUND -> "pound$s"
        OUNCE -> "ounce$s"
        // temperature
        CELSIUS -> "degree$s Celsius"
        FAHRENHEIT -> "degree$s Fahrenheit"
        KELVIN -> "Kelvin$s"

        else -> "Unknown unit"
    }
}

fun s(number: Double): String = if (number == 1.0) "" else "s"
