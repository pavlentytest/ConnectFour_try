fun main() {
    val str = readln()
    val sum = arrayOf(0,0)
    for(i in str.indices) {
        if(i<=2) sum[0] += str[i].digitToInt()
        else sum[1] += str[i].digitToInt()
    }
    println(if (sum[0]==sum[1]) "Lucky" else "Regular")
}
/*
   readLine()!!.toList().map { it.toInt() }.let {
        println(if (it.subList(0, 3).sum() == it.subList(3, 6).sum()) "Lucky" else "Regular")
    }
*/
