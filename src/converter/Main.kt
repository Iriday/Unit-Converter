package converter

import converter.Unit.*

fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")

        val input = readLine()!!.split(" ")

        if (input.size == 1) {
            if (input[0].toLowerCase().equals("exit")) {
                return
            } else {
                println("Incorrect input")
                continue
            }
        }
        if (input.size != 4) {
            println("Incorrect input")
            continue
        }

        val inValue = input[0].toDouble()
        val enumInUnit = Unit.getUnit(input[1])
        val enumOutUnit = Unit.getUnit(input[3])

        val meters: Double = toMeters(inValue, enumInUnit)
        val grams: Double = toGrams(inValue, enumInUnit)
        if (meters == -1.0 && grams == -1.0) {
            println("Incorrect input")
            continue
        }
        val outValue = if (meters != -1.0) metersTo(meters, enumOutUnit) else gramsTo(grams, enumOutUnit)

        println(createOutputString(enumInUnit, inValue, enumOutUnit, outValue))
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

        else -> "Unknown unit"
    }
}

fun s(number: Double): String = if (number == 1.0) "" else "s"
