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

        val number = input[0].toDouble()
        val enumInType = Unit.getUnit(input[1])
        val enumConvType = Unit.getUnit(input[3])

        val meters: Double = toMeters(number, enumInType)
        val grams: Double = toGrams(number, enumInType)
        if (meters == -1.0 && grams == -1.0) {
            println("Incorrect input")
            continue
        }
        val convValue = if (meters != -1.0) metersTo(meters, enumConvType) else gramsTo(grams, enumConvType)

        println(createOutputString(enumInType, number, enumConvType, convValue))
    }
}

fun toMeters(number: Double, type: Unit): Double {
    return when (type) {
        METER -> number
        KILOMETER -> number * 1000
        CENTIMETER -> number * 0.01
        MILLIMETER -> number * 0.001
        MILE -> number * 1609.35
        YARD -> number * 0.9144
        FOOT -> number * 0.3048
        INCH -> number * 0.0254
        else -> -1.0
    }
}

fun metersTo(number: Double, type: Unit): Double {
    return when (type) {
        METER -> number
        KILOMETER -> number / 1000.0
        CENTIMETER -> number / 0.01
        MILLIMETER -> number / 0.001
        MILE -> number / 1609.35
        YARD -> number / 0.9144
        FOOT -> number / 0.3048
        INCH -> number / 0.0254
        else -> -1.0
    }
}

fun toGrams(value: Double, type: Unit): Double {
    return when (type) {
        GRAM -> value
        KILOGRAM -> value * 1000
        MILLIGRAM -> value * 0.001
        POUND -> value * 453.592
        OUNCE -> value * 28.3495
        else -> -1.0
    }
}

fun gramsTo(value: Double, type: Unit): Double {
    return when (type) {
        GRAM -> value
        KILOGRAM -> value / 1000.0
        MILLIGRAM -> value / 0.001
        POUND -> value / 453.592
        OUNCE -> value / 28.3495
        else -> -1.0
    }
}

fun createOutputString(inType: Unit, inValue: Double, convType: Unit, convValue: Double): String {
    val iType = convertTypeName(inType, inValue)
    val cType = convertTypeName(convType, convValue)

    return "$inValue $iType is $convValue $cType"
}

fun convertTypeName(typeName: Unit, value: Double): String {
    return when (typeName) {
        METER -> "meter${s(value)}"
        KILOMETER -> "kilometer${s(value)}"
        CENTIMETER -> "centimeter${s(value)}"
        MILLIMETER -> "millimeter${s(value)}"
        MILE -> "mile${s(value)}"
        YARD -> "yard${s(value)}"
        FOOT -> if (value == 1.0) "foot" else "feet"
        INCH -> if (value == 1.0) "inch" else "inches"

        GRAM -> "gram${s(value)}"
        KILOGRAM -> "kilogram${s(value)}"
        MILLIGRAM -> "milligram${s(value)}"
        POUND -> "pound${s(value)}"
        OUNCE -> "ounce${s(value)}"

        else -> "Unknown type"
    }
}

fun s(number: Double): String = if (number == 1.0) "" else "s"
