package parking

fun main() {
    while (true){
        val input = readln().split(" ")
        val cmd = input.first().lowercase()
        when(cmd){
            "create" -> Parking.create(input[1].toInt())
            "park" -> Parking.park(input[1], input[2])
            "leave" -> Parking.leave(input[1].toInt())
            "reg_by_color" -> Parking.regByColor (input[1])
            "spot_by_color" -> Parking.spotByColor(input[1])
            "spot_by_reg" -> Parking.spotByReg(input[1])
            "status" -> Parking.status()
            "exit" -> break
        }
    }
}
object Parking{
    private var spots = emptyList<Pair<String, String>>().toMutableList()
    fun status(){
        if (spots.all { it == Pair("", "") }){
            println("Parking lot is empty.")
            return
        }
        for ((index, value) in spots.withIndex()){
            if (value != Pair("", "")) println("${index + 1} ${value.first} ${value.second.replaceFirstChar { it.uppercase() }}")
        }
    }
    fun create(size: Int){
        if (size > 0){
            spots = MutableList(size){ Pair("", "") }
            println("Created a parking lot with $size spots.")
        }
    }

    fun park(regNumber: String, color: String){
        if (spots.isEmpty()){
            println("Sorry, a parking lot has not been created.")
            return
        }
        if (!spots.contains(Pair("", ""))) {
            println("Sorry, the parking lot is full.")
            return
        }
        for ((index, value) in spots.withIndex()){
            if (value.first == ""){
                spots[index] = Pair(regNumber, color.lowercase())
                println("${color.replaceFirstChar { it.uppercase() }} car parked in spot ${index + 1}.")
                return
            }
        }
    }

    fun leave(numberOfSpot: Int){
        if (spots.isEmpty()){
            println("Sorry, a parking lot has not been created.")
            return
        }
        if (spots[numberOfSpot - 1].first == ""){
            println("There is no car in spot $numberOfSpot.")
        }else{
            spots[numberOfSpot - 1] = Pair("", "")
            println("Spot $numberOfSpot is free.")
        }
    }

    fun regByColor(color: String){
        if (spots.isEmpty()){
            println("Sorry, a parking lot has not been created.")
            return
        }
        val list = mutableListOf<String>()
        for (spot in spots){
            if (spot.second == color.lowercase()) list.add(spot.first)
        }
        if (list.isEmpty()) println("No cars with color ${color.uppercase()} were found.")
        else println(list.joinToString { it })
    }

    fun spotByColor(color: String){
        if (spots.isEmpty()){
            println("Sorry, a parking lot has not been created.")
            return
        }
        val list = mutableListOf<Int>()
        for (spot in spots){
            if (spot.second == color.lowercase()) list.add(spots.indexOf(spot) + 1)
        }
        if (list.isEmpty()) println("No cars with color ${color.uppercase()} were found.")
        else println(list.joinToString { it.toString() })
    }

    fun spotByReg(regNumber: String){
        if (spots.isEmpty()){
            println("Sorry, a parking lot has not been created.")
            return
        }
        for (spot in spots){
            if (spot.first == regNumber) {
                println(spots.indexOf(spot) + 1)
                return
            }
        }
        println("No cars with registration number $regNumber were found.")
    }
}