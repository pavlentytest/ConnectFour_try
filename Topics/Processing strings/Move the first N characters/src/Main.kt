fun main() {
   val line = readln().split(" ")
    val str = line[0]
    val sd = line[1].toInt()

   val result = if(sd > str.length || sd == 0) str else str.drop(sd) + str.take(sd)
    println(result)

}
