package converter

import converter.Unit.*
import converter.Type.*
import java.math.BigDecimal
import kotlin.text.StringBuilder

fun main() {
    Main().run()
}

private val regexCheckInput =
    Regex("(?i)\\s*[-+]?(\\d+|\\d+.\\d+)\\s+([a-z]+|degree[s]?\\s+[a-z]+)\\s+[^\\s]+\\s+([a-z]+|degree[s]?\\s+[a-z]+)\\s*")

private val regexSplitInput = Regex("\\s+")

private val help = createHelpString()

class Main {
    fun run() {
        while (true) {
            print("Enter what you want to convert (or help, exit): ")

            val notParsedInput = readLine()!!.trim().toLowerCase()
            if (notParsedInput == "exit") return
            if (notParsedInput == "help") {
                println(help)
                continue
            }
            val input = parseInput(notParsedInput)
            if (input.isEmpty()) {
                println("Parse error")
                continue
            }

            val inValue = bD(input[0]) // checked
            val enumInUnit = Unit.getUnit(input[1])
            val enumOutUnit = Unit.getUnit(input[2])

            if (!isConversionPossible(enumInUnit, enumOutUnit)) {
                val i = convertUnitName(enumInUnit, TWO)
                val o = convertUnitName(enumOutUnit, TWO)
                println("Conversion from $i to $o is impossible")
                continue
            }
            if ((enumInUnit.type == LENGTH || enumInUnit.type == WEIGHT) && inValue < ZERO) {
                println("${enumInUnit.type.name.toLowerCase().capitalize()} shouldn't be negative")
                continue
            }

            val outValue = convert(inValue, enumInUnit, enumOutUnit)

            println(createOutputString(enumInUnit, removeTrailingZeros(inValue), enumOutUnit, removeTrailingZeros(outValue)))
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

fun convert(inValue: BigDecimal, inUnit: Unit, outUnit: Unit): BigDecimal {
    return when (inUnit.type) {
        TIME -> secondsTo(toSeconds(inValue, inUnit), outUnit)
        LENGTH -> metersTo(toMeters(inValue, inUnit), outUnit)
        WEIGHT -> gramsTo(toGrams(inValue, inUnit), outUnit)
        FREQUENCY -> hertzTo(toHertz(inValue, inUnit), outUnit)

        TEMPERATURE -> when (inUnit) {
            CELSIUS -> celsiusTo(inValue, outUnit)
            FAHRENHEIT -> fahrenheitTo(inValue, outUnit)
            KELVIN -> kelvinTo(inValue, outUnit)
            else -> throw IllegalArgumentException()
        }
        else -> throw IllegalArgumentException()
    }
}

fun createOutputString(inUnit: Unit, inValue: BigDecimal, outUnit: Unit, outValue: BigDecimal): String {
    val iUnit = convertUnitName(inUnit, inValue)
    val oUnit = convertUnitName(outUnit, outValue)

    return "$inValue $iUnit is $outValue $oUnit"
}

fun convertUnitName(unit: Unit, value: BigDecimal): String {
    val s = s(value)
    val simpleName = "${unit.name.toLowerCase()}$s"
    return when (unit) {
        // time
        SECOND, WEEK, DAY, HOUR, MINUTE, MILLISECOND, MICROSECOND, NANOSECOND -> simpleName
        // length
        METER, KILOMETER, CENTIMETER, MILLIMETER, MICROMETER, NANOMETER, MILE, YARD -> simpleName
        FOOT -> if (value == ONE) "foot" else "feet"
        INCH -> if (value == ONE) "inch" else "inches"
        // weight
        GRAM, KILOGRAM, MILLIGRAM, MICROGRAM, STONE, POUND, OUNCE -> simpleName
        // temperature
        CELSIUS -> "degree$s Celsius"
        FAHRENHEIT -> "degree$s Fahrenheit"
        KELVIN -> "Kelvin$s"
        // frequency
        HERTZ, KILOHERTZ, MEGAHERTZ, GIGAHERTZ -> unit.name.toLowerCase()

        else -> "???"
    }
}

fun s(number: BigDecimal): String = if (number == ONE) "" else "s"

private fun createHelpString(): String {
    return "\nExamples: 10 seconds to ms; 3 Km In Meters; 1500 g to kg; -20 degrees Celsius to Kelvins\n\n${unitsToString()}"
}

private fun unitsToString(): String {
    val sb = StringBuilder()

    var type = Unit.values()[0].type
    sb.append("-->$type\n")
    for (unit in Unit.values()) {
        if (unit.type == Type.UNKNOWN) break
        if (unit.type != type) {
            type = unit.type
            sb.append("-->$type\n")
        }
        sb.append("${unit.toString().toLowerCase().capitalize()}- ")
        var first = true
        unit.names.iterator().forEach { v -> if(!first) sb.append(", $v") else { sb.append(v); first = false} }
        sb.append(".\n")
    }
    return sb.toString()
}
