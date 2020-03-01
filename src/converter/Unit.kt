package converter

import converter.Type.*

enum class Unit(val names: Array<String>, val type: Type) {
    // time
    SECOND(arrayOf("s", "sec", "second", "seconds"), TIME),
    WEEK(arrayOf("w", "wk", "wks", "week", "weeks"), TIME),
    DAY(arrayOf("d", "day", "days"), TIME),
    HOUR(arrayOf("h", "hr", "hrs", "hour", "hours"), TIME),
    MINUTE(arrayOf("min", "minute", "minutes"), TIME),
    MILLISECOND(arrayOf("ms", "msec", "millisec", "millisecond", "milliseconds"), TIME),
    MICROSECOND(arrayOf("μs", "us", "microsec", "microsecond", "microseconds"), TIME),
    NANOSECOND(arrayOf("ns", "nsec", "nanosec", "nanosecond", "nanoseconds"), TIME),

    // length
    METER(arrayOf("m", "meter", "meters"), LENGTH),
    KILOMETER(arrayOf("km", "kilometer", "kilometers"), LENGTH),
    CENTIMETER(arrayOf("cm", "centimeter", "centimeters"), LENGTH),
    MILLIMETER(arrayOf("mm", "millimeter", "millimeters"), LENGTH),
    MILE(arrayOf("mi", "mile", "miles"), LENGTH),
    YARD(arrayOf("yd", "yard", "yards"), LENGTH),
    FOOT(arrayOf("ft", "foot", "feet"), LENGTH),
    INCH(arrayOf("in", "inch", "inches"), LENGTH),

    // weight
    GRAM(arrayOf("g", "gram", "grams"), WEIGHT),
    KILOGRAM(arrayOf("kg", "kilogram", "kilograms"), WEIGHT),
    MILLIGRAM(arrayOf("mg", "milligram", "milligrams"), WEIGHT),
    POUND(arrayOf("lb", "pound", "pounds"), WEIGHT),
    OUNCE(arrayOf("oz", "ounce", "ounces"), WEIGHT),

    // temperature
    CELSIUS(arrayOf("c", "dc", "celsius", "degree celsius", "degrees celsius"), TEMPERATURE),
    FAHRENHEIT(arrayOf("f", "df", "fahrenheit", "degree fahrenheit", "degrees fahrenheit"), TEMPERATURE),
    KELVIN(arrayOf("k", "kelvin", "kelvins"), TEMPERATURE),

    UNKNOWN(arrayOf("???"), Type.UNKNOWN);


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
