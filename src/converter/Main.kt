package converter

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
        val inType = input[1].toLowerCase()
        val convType = input[3].toLowerCase()

        val meters: Double = toMeters(number, inType)
        val grams: Double = toGrams(number, inType)
        if (meters == -1.0 && grams == -1.0) {
            println("Incorrect input")
            continue
        }
        val convValue = if (meters != -1.0) metersTo(meters, convType) else gramsTo(grams, convType)

        println(createOutputString(inType, number, convType, convValue))
    }
}

fun toMeters(number: Double, type: String): Double {
    return when (type) {
        "m", "meter", "meters" -> number
        "km", "kilometer", "kilometers" -> number * 1000
        "cm", "centimeter", "centimeters" -> number * 0.01
        "mm", "millimeter", "millimeters" -> number * 0.001
        "mi", "mile", "miles" -> number * 1609.35
        "yd", "yard", "yards" -> number * 0.9144
        "ft", "foot", "feet" -> number * 0.3048
        "in", "inch", "inches" -> number * 0.0254
        else -> -1.0
    }
}

fun metersTo(number: Double, type: String): Double {
    return when (type) {
        "m", "meter", "meters" -> number
        "km", "kilometer", "kilometers" -> number / 1000.0
        "cm", "centimeter", "centimeters" -> number / 0.01
        "mm", "millimeter", "millimeters" -> number / 0.001
        "mi", "mile", "miles" -> number / 1609.35
        "yd", "yard", "yards" -> number / 0.9144
        "ft", "foot", "feet" -> number / 0.3048
        "in", "inch", "inches" -> number / 0.0254
        else -> -1.0
    }
}

fun toGrams(value: Double, type: String): Double {
    return when (type) {
        "g", "gram", "grams" -> value
        "kg", "kilogram", "kilograms" -> value * 1000
        "mg", "milligram", "milligrams" -> value * 0.001
        "lb", "pound", "pounds" -> value * 453.592
        "oz", "ounce", "ounces" -> value * 28.3495
        else -> -1.0
    }
}

fun gramsTo(value: Double, type: String): Double {
    return when (type) {
        "g", "gram", "grams" -> value
        "kg", "kilogram", "kilograms" -> value / 1000.0
        "mg", "milligram", "milligrams" -> value / 0.001
        "lb", "pound", "pounds" -> value / 453.592
        "oz", "ounce", "ounces" -> value / 28.3495
        else -> -1.0
    }
}

fun createOutputString(inType: String, inValue: Double, convType: String, convValue: Double): String {
    val iType = convertTypeName(inType, inValue)
    val cType = convertTypeName(convType, convValue)

    return "$inValue $iType is $convValue $cType"
}

fun convertTypeName(typeName: String, value: Double): String {
    return when (typeName) {
        "m", "meter", "meters" -> "meter${s(value)}"
        "km", "kilometer", "kilometers" -> "kilometer${s(value)}"
        "cm", "centimeter", "centimeters" -> "centimeter${s(value)}"
        "mm", "millimeter", "millimeters" -> "millimeter${s(value)}"
        "mi", "mile", "miles" -> "mile${s(value)}"
        "yd", "yard", "yards" -> "yard${s(value)}"
        "ft", "foot", "feet" -> if (value == 1.0) "foot" else "feet"
        "in", "inch", "inches" -> if (value == 1.0) "inch" else "inches"

        "g", "gram", "grams" -> "gram${s(value)}"
        "kg", "kilogram", "kilograms" -> "kilogram${s(value)}"
        "mg", "milligram", "milligrams" -> "milligram${s(value)}"
        "lb", "pound", "pounds" -> "pound${s(value)}"
        "oz", "ounce", "ounces" -> "ounce${s(value)}"

        else -> "Unknown type"
    }
}

fun s(number: Double): String = if (number == 1.0) "" else "s"
