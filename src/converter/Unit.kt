package converter

enum class Unit(val names: Array<String>) {
    // length
    METER(arrayOf("m", "meter", "meters")),
    KILOMETER(arrayOf("km", "kilometer", "kilometers")),
    CENTIMETER(arrayOf("cm", "centimeter", "centimeters")),
    MILLIMETER(arrayOf("mm", "millimeter", "millimeters")),
    MILE(arrayOf("mi", "mile", "miles")),
    YARD(arrayOf("yd", "yard", "yards")),
    FOOT(arrayOf("ft", "foot", "feet")),
    INCH(arrayOf("in", "inch", "inches")),

    // weight
    GRAM(arrayOf("g", "gram", "grams")),
    KILOGRAM(arrayOf("kg", "kilogram", "kilograms")),
    MILLIGRAM(arrayOf("mg", "milligram", "milligrams")),
    POUND(arrayOf("lb", "pound", "pounds")),
    OUNCE(arrayOf("oz", "ounce", "ounces")),

    // temperature
    CELSIUS(arrayOf("c", "dc", "celsius", "degree celsius", "degrees celsius")),
    FAHRENHEIT(arrayOf("f", "df", "fahrenheit", "degree fahrenheit", "degrees fahrenheit")),
    KELVIN(arrayOf("k", "kelvin", "kelvins")),

    UNKNOWN(arrayOf("???"));


    companion object {
        fun getUnit(unitName: String): Unit {
            val name = unitName.toLowerCase()
            for (enum in values()) {
                for (n in enum.names) {
                    if (n == name)
                        return enum
                }
            }
            return UNKNOWN
        }
    }
}