var purchasedTicket = 0
var currentIncome = 0

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRow = readln().toInt()
    val totalSeats = rows * seatsInRow
    var mainList = MutableList(rows) { ((1..seatsInRow).map { "S" } as MutableList<String>) }

    var state = true

    while (state) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln().toInt()) {
            1 -> showSeatingArrangment(rows, seatsInRow, mainList)
            2 -> mainList = buyTicket(totalSeats, mainList)
            3 -> statistics(totalSeats, mainList, seatsInRow)
            0 -> state = false
        }
    }
}

fun buyTicket(totalSeats: Int, mainList: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    println("Enter a row number:")
    val rowNumber = readln().toInt()
    println("Enter a seat number in that row:")
    val seatNumber = readln().toInt()

    try {
        if (mainList[rowNumber - 1][seatNumber - 1] == "S") {
            if (totalSeats <= 60) {
                println("Ticket price: $10")
            } else {
                if (mainList.size / 2 >= rowNumber) {
                    println("Ticket price: $10")
                    currentIncome += 10
                } else {
                    println("Ticket price: $8")
                    currentIncome += 8
                }
            }
            mainList[rowNumber - 1][seatNumber - 1] = "B"
            purchasedTicket++
            return mainList
        } else {
            println("That ticket has already been purchased!")
            return buyTicket(totalSeats, mainList)
        }
    } catch (e: Exception) {
        println("Wrong input!")
        return buyTicket(totalSeats, mainList)
    }
}

fun statistics(totalSeats: Int, mainList: MutableList<MutableList<String>>, seatsInRow: Int) {
    val totalIncome = if (totalSeats <= 60) {
        totalSeats * 10
    } else {
        if (mainList.size % 2 == 0) {
            ((mainList.size / 2) * seatsInRow * 10) + ((mainList.size / 2) * seatsInRow * 8)
        } else {
            ((mainList.size / 2) * seatsInRow * 10) + (((mainList.size / 2) + 1) * seatsInRow * 8)
        }
    }

    val percentage:Double = (purchasedTicket.toDouble() / totalSeats.toDouble())*100
    val formatPercentage = "%.2f".format(percentage)


    println("Number of purchased tickets: $purchasedTicket")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")

}

fun showSeatingArrangment(rows: Int, seatsInRow: Int, mainList: MutableList<MutableList<String>>) {
    println("Cinema:")
    println("  ${(1..seatsInRow).map { it }.joinToString(" ")}")
    repeat(rows) {
        println("${it + 1} ${mainList[it].joinToString(" ")}")
    }
}





