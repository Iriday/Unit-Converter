package converter

import converter.Unit.*
import converter.Type.*

fun main() {
    Main().run()
}

private val regexCheckInput =
    Regex("(?i)\\s*[-+]?(\\d+|\\d+.\\d+)\\s+([a-z]+|degree[s]?\\s+[a-z]+)\\s+[^\\s]+\\s+([a-z]+|degree[s]?\\s+[a-z]+)\\s*")

private val regexSplitInput = Regex("\\s+")

class Main {
    fun run() {
        while (true) {
            print("Enter what you want to convert (or exit): ")

            val notParsedInput = readLine()!!.trim().toLowerCase()
            if (notParsedInput.toLowerCase() == "exit") {
                return
            }
            val input = parseInput(notParsedInput)
            if (input.isEmpty()) {
                println("Parse error")
                continue
            }

            val inValue = input[0].toDouble() // checked
            val enumInUnit = Unit.getUnit(input[1])
            val enumOutUnit = Unit.getUnit(input[2])

            if (!isConversionPossible(enumInUnit, enumOutUnit)) {
                val i = convertUnitName(enumInUnit, 2.0)
                val o = convertUnitName(enumOutUnit, 2.0)
                println("Conversion from $i to $o is impossible")
                continue
            }
            if ((enumInUnit.type == LENGTH || enumInUnit.type == WEIGHT) && inValue < 0) {
                println("${enumInUnit.type.name.toLowerCase().capitalize()} shouldn't be negative")
                continue
            }

            val outValue = convert(inValue, enumInUnit, enumOutUnit)

            println(createOutputString(enumInUnit, inValue, enumOutUnit, outValue))
        }
    }

    private fun parseInput(input: String): List<String> {
        if (input.matches(regexCheckInput)) {
            val data = input.split(regexSplitInput)
            if (data.size == 4) {
                return listOf(data[0], data[1], data[3])
            }
            if (data.size == 5) {
                if (data[1] == "degree" || data[1] == "degrees")
                    return listOf(data[0], data[1] + " " + data[2], data[4])
                if (data[3] == "degree" || data[3] == "degrees")
                    return listOf(data[0], data[1], data[3] + " " + data[4])
            }
            if (data.size == 6) {
                return listOf(data[0], data[1] + " " + data[2], data[4] + " " + data[5])
            }
        }
        return emptyList()
    }
}

fun isConversionPossible(inUnit: Unit, outUnit: Unit): Boolean {
    return inUnit.type == outUnit.type && inUnit.type != Type.UNKNOWN
}

fun convert(inValue: Double, inUnit: Unit, outUnit: Unit): Double {
    return when (inUnit) {
        METER, KILOMETER, CENTIMETER, MILLIMETER, MILE, YARD, FOOT, INCH -> {
            metersTo(toMeters(inValue, inUnit), outUnit)
        }
        GRAM, KILOGRAM, MILLIGRAM, POUND, OUNCE -> {
            gramsTo(toGrams(inValue, inUnit), outUnit)
        }
        CELSIUS -> celsiusTo(inValue, outUnit)
        FAHRENHEIT -> fahrenheitTo(inValue, outUnit)
        KELVIN -> kelvinTo(inValue, outUnit)

        else -> throw IllegalArgumentException()
    }
}

fun createOutputString(inUnit: Unit, inValue: Double, outUnit: Unit, outValue: Double): String {
    val iUnit = convertUnitName(inUnit, inValue)
    val oUnit = convertUnitName(outUnit, outValue)

    return "$inValue $iUnit is $outValue $oUnit"
}

fun convertUnitName(unit: Unit, value: Double): String {
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

        else -> "???"
    }
}

fun s(number: Double): String = if (number == 1.0) "" else "s"
