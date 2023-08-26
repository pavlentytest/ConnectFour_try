package connectfour

import java.lang.Exception
import java.util.*

/*
fun main() {
    println("Connect Four")
    println("First player's name:")
    val first = readln()
    println("Second player's name:")
    val second = readln()
    println("Set the board dimensions (Rows x Columns)")
    println("Press Enter for default (6 x 7)")
    var x = 0
    var y = 0
    while (true) {
        val str = readln().replace(" ", "").replace("\t", "").toLowerCase()
        if (str == "") {
            x = 6
            y = 7
            break
        } else {
            try {
                val numbers = str.split("x").map { it.toInt() }
                x = numbers[0]
                y = numbers[1]
                var flag_x = false
                var flag_y = false
                if (x in 5..9) {
                    flag_x = true
                } else {
                    println("Board rows should be from 5 to 9")
                    println("Set the board dimensions (Rows x Columns)")
                    println("Press Enter for default (6 x 7)")
                }
                if (y in 5..9) {
                    flag_y = true
                } else {
                    println("Board columns should be from 5 to 9")
                    println("Set the board dimensions (Rows x Columns)")
                    println("Press Enter for default (6 x 7)")
                }
                if (flag_x && flag_y) break
            } catch (e: Exception) {
                println("Invalid input")
                println("Set the board dimensions (Rows x Columns)")
                println("Press Enter for default (6 x 7)")
            }
        }

    }
    println("$first VS $second")
    println("$x X $y board")
    val field =  Array(x) { Array(y) { " " } }
    printField(field, x, y)
    val step = IntArray(y)
    var action = ' '
    val scanner = Scanner(System.`in`)
    var flag = false
    while (true) {


        println("$first's turn:")
        action = 'o'
        while (scanner.hasNextLine()) {
            try {
                val first_value = scanner.next()
                if (first_value == "end") {
                    println("Game over!")
                    flag = true
                    break
                }
                val first_value_int = first_value.toInt()
                if(first_value_int !in 1..y ) {
                    println("The column number is out of range (1 - $y)")
                    println("$first's turn:")
                } else if (step[first_value_int-1] == x) {
                    println("Column $first_value_int is full")
                    println("$first's turn:")
                } else {
                    gameField(x, y, field, action, first_value.toInt(), step)
                    printField(field, x, y)
                  //  printArray(field, x, y)
                  //  printFree(step)
                    break
                }
            } catch(e: Exception) {
                println("Incorrect column number")
                println("$first's turn:")
            }

        }
        if(flag) break

         println("$second's turn:")
         action = '*'
         while (scanner.hasNextLine()) {
             try {
                 val second_value = readln()
                 if (second_value == "end") {
                     println("Game over!")
                     flag = true
                     break
                 }
                 val second_value_int = second_value.toInt()
                 if (second_value_int !in 1..y) {
                     println("The column number is out of range (1 - $y)")
                     println("$second's turn:")
                 } else if (step[second_value_int - 1] == x) {
                     println("Column $second_value_int is full")
                     println("$second's turn:")
                 } else {
                     gameField(x, y, field, action, second_value.toInt(), step)
                     printField(field,x,y)
                   //  printArray(field, x, y)
                 //    printFree(step)
                     break
                 }
             } catch (e: Exception) {
                 println("Incorrect column number")
                 println("$second's turn:")
             }
         }
         if(flag) break
    }
}
fun gameField(x: Int, y: Int, field: Array<Array<String>>, action: Char = ' ', value: Int = 0, free: IntArray) {
    val value_ = value-1
    for(kk in x-1 downTo 0) {
        if(field[kk][value_] == "*" || field[kk][value_] == "o") {
            continue
        } else {
            field[kk][value_] = action.toString()
            free[value_]++
            break
        }

    }
}
fun printArray(a: Array<Array<String>>, x: Int, y: Int) {
    println()
    for (i in 0 until x) {
        for (j in 0 until y) {
            print(a[i][j])
            print(" ")
        }
        println()
    }
}
fun printFree(a: IntArray) {
    println(Arrays.toString(a))
}
fun printField(a: Array<Array<String>>, x: Int, y: Int)  {
    var space = ""

    for(i in 1 .. y) {
       space = if(i==1) " " else ""
       print("$space$i ")
   }
   println()
   for(kk in 0 until x) {
       for (ii in 0 until y) {
           if(ii == y-1) {
               print(9553.toChar() + a[kk][ii] + 9553.toChar())
           } else {
               print(9553.toChar() + a[kk][ii])
           }
       }
       println()
   }
   print(9562.toChar())
   for(jj in 1..y) {
       print(9552.toChar())
       if(jj != y)
           print(9577.toChar())
    }
    print(9565.toChar())
    println()
}



import kotlin.system.exitProcess

class Player(val name: String)

class ConnectFour {
    var rows = 6
    var cols = 7
    val dimensionTemplate = "\\d+([xX])\\d+".toRegex()
    val firstPlayer: Player
    val secondPlayer: Player
    val gameBoard: MutableList<MutableList<Char>>
    enum class Turn {FIRST, SECOND}
    var turn = Turn.FIRST
    var disc = 'o'
    var currentPlayerName: String

    init {
        println("Connect Four")
        println("First player's name:")
        firstPlayer = Player(readLine()!!)
        println("Second player's name:")
        secondPlayer = Player(readLine()!!)
        currentPlayerName = firstPlayer.name
        askDimension()
        gameBoard = MutableList(rows) { MutableList(cols) { ' ' } }
        startGame()
    }


    fun startMessage() {
        println("${firstPlayer.name} VS ${secondPlayer.name}\n" +
                "$rows X $cols board")
    }

    fun askDimension() {
        while(true) {
            println("Set the board dimensions (Rows x Columns)\n" +
                    "Press Enter for default (6 x 7)")
            val dimension = readLine()!!.lowercase().replace("\\s+".toRegex(), "")
            when {
                dimension.isEmpty() -> break
                !dimensionTemplate.matches(dimension) -> println("Invalid input")
                else -> {
                    rows = dimension.substringBefore('x').toInt()
                    cols = dimension.substringAfter('x').toInt()
                    if (rows !in 5..9) {
                        println("Board rows should be from 5 to 9")
                    } else if (cols !in 5..9) {
                        println("Board columns should be from 5 to 9")
                    } else {
                        break
                    }
                }
            }
        }
    }

    fun showBoard() {
        println()
        for (j in 1..cols) print(" $j")
        println()
        for (row in gameBoard) {
            println(row.joinToString("║", "║", "║"))
        }
        println("╚${"═╩".repeat(cols - 1)}═╝")
    }

    fun startGame() {
        startMessage()
        showBoard()
        while(true) {
            inputColumn()
            showBoard()
        }
    }

    fun inputColumn() {
        while(true) {
            println("$currentPlayerName's turn:")
            val playerInput = readLine()!!
            val playerCol = playerInput.toIntOrNull()
            if (playerInput == "end") {
                println("Game over!")
                exitProcess(0)
            } else if (playerCol == null) {
                println("Incorrect column number")
            } else if (playerCol !in 1..cols) {
                println("The column number is out of range (1 - $cols)")
            } else if (isColumnFull(playerCol)) {
                println("Column $playerCol is full")
            } else {
                addDisk(playerCol)
                changeTurn()
                break
            }
        }
    }

    fun addDisk(col: Int) {
        for (i in 0 until rows) {
            if (gameBoard[i][col - 1] != ' ') {
                gameBoard[i - 1][col - 1] = disc
                return
            }
        }
        gameBoard[rows - 1][col - 1] = disc // if column is empty
    }

    fun changeTurn() {
        if (turn == Turn.FIRST) {
            turn = Turn.SECOND
            currentPlayerName = secondPlayer.name
            disc = '*'
        } else {
            turn = Turn.FIRST
            currentPlayerName = firstPlayer.name
            disc = 'o'
        }
    }

    fun isColumnFull(col: Int): Boolean {
        return gameBoard[0][col - 1] != ' '
    }
}

fun main() {
    val game = ConnectFour()
}
*/
var start = true
lateinit var bSize: List<Int>
lateinit var board: MutableList<MutableList<String>>
var turn = true
val pS = mutableMapOf("o" to "", "*" to "")

fun main() {
    println("Connect Four")
    println("First player's name: "); pS["o"] = readLine()!!
    println("Second player's name: "); pS["*"] = readLine()!!
    while (start) {
        println("Set the board dimensions (Rows x Columns)\nPress Enter for default (6 x 7)")
        readLine()!!.toString().replace("\\s+".toRegex(), "").lowercase().run {
            when {
                matches(Regex("")) -> { start = false; start(pS["o"]!!, pS["*"]!!, 6, 7) }
                !matches(Regex("[0-9]+x[0-9]+")) -> "Invalid input"
                Character.getNumericValue(first()) !in 5..9 -> "Board rows should be from 5 to 9"
                Character.getNumericValue(last()) !in 5..9 -> "Board columns should be from 5 to 9"
                else -> { start = false; start(pS["o"]!!, pS["*"]!!, first().toString().toInt(), last().toString().toInt()) }
            }.run(::println)
        }
    }
    printB(); play(pS["o"]!!, pS["*"]!!)
}

fun play(firstPlayer: String, secondPlayer: String) {
    while(true) {
        (if (turn) "o" else "*").let {
            if (boardSeq().contains(" ")) println("${pS[it]}'s turn: ")
            else { println("It is a draw\nGame over!"); return }
            readLine()!!.run {
                when {
                    equals("end") -> { println("Game over!"); return }
                    toIntOrNull() == null -> println("Incorrect column number")
                    toInt() !in 1..bSize.last() -> println("The column number is out of range (1 - ${bSize.last()})")
                    makeMove(toInt() - 1, it) -> {
                        turn = !turn; printB()
                        if (win(if (it == "*") "v" else it, bSize.last())) {
                            println("Player ${if (it == "*") secondPlayer else firstPlayer} won\nGame over!"); return
                        }
                    }
                    else -> println("Column ${toInt()} is full")
                }
            }
        }
    }
}

fun makeMove(column: Int, coin: String): Boolean {
    for (elem in board.reversed()) if (elem[column] == " ") { elem[column] = coin; return true}; return false }

fun start(p1name: String, p2name: String, rows: Int, columns: Int): String {
    bSize = listOf(rows, columns)
    board = MutableList(rows) {MutableList(columns) {" "} }
    return "$p1name VS $p2name\n$rows X $columns board" }

fun printB() {
    repeat(bSize.last()) { print(" ${it + 1}") }
    repeat(bSize.first()) { print("\n║${board[it].joinToString("║")}║") }
    println("\n╚" + "═╩".repeat(bSize.last() - 1) + "═╝") }

fun boardSeq() = board.joinToString("-") { it.joinToString("") }.replace("*", "v")

fun win(s: String, col: Int) = listOf("", ".{$col}", ".{${col - 1}}", ".{${col + 1}}").run {
    (count { Regex("$s$it$s$it$s$it$s").containsMatchIn(boardSeq()) } != 0) }




