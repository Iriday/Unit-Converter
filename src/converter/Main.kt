package converter

fun main() {
    print("Enter a number and a measure of length: ")

    val input = readLine()!!.split(" ")
    val number = input[0].toDouble()
    val type = input[1].toLowerCase()

    val meters = toMeters(number, type)
    if (meters == -1.0) {
        println("Incorrect input")
        return
    }
    print(createOutputString(type, number, "meter", meters))
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
        else -> "Unknown type"
    }
}

fun s(number: Double): String = if (number == 1.0) "" else "s"
