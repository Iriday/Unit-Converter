package converter

fun main() {
    print("Enter a number and a measure of length: ")

    val input = readLine()!!.split(" ")
    val number = input[0].toDouble()
    val type = input[1].toLowerCase()

    val output = when (type) {
        "m", "meter", "meters" -> "$number meter${s(number)} is $number meter${s(number)}"
        "km", "kilometer", "kilometers" -> "$number kilometer${s(number)} is ${number * 1000} meter${s(number * 1000)}"
        "cm", "centimeter", "centimeters" -> "$number centimeter${s(number)} is ${number * 0.01} meter${s(number * 0.01)}"
        "mm", "millimeter", "millimeters" -> "$number millimeter${s(number)} is ${number * 0.001} meter${s(number * 0.001)}"
        "mi", "mile", "miles" -> "$number mile${s(number)} is ${number * 1609.35} meter${s(number * 1609.35)}"
        "yd", "yard", "yards" -> "$number yard${s(number)} is ${number * 0.9144} meter${s(number * 0.9144)}"
        "ft", "foot", "feet" -> "$number f${if (number == 1.0) "oo" else "ee"}t is ${number * 0.3048} meter${s(number)}"
        "in", "inch", "inches" -> "$number inche${s(number)} is ${number * 0.0254} meter${s(number * 0.0254)}"

        else -> "Unknown type"
    }

    print(output)
}

fun s(number: Double): String = if (number == 1.0) "" else "s"
