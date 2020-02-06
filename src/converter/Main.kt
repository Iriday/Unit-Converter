package converter

fun main() {

    print("Enter a number of kilometers: ")
    val km = readLine()!!.toInt()
    print("$km kilometers is ${km * 1000} meters")
}
