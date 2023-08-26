fun main() {
    val str = readln()
    val vowels = "aeiouy"
    var c = 0
    var c_v = 0
    var c_v_ = 0
    for(i in str.indices) {
        if(str[i] in vowels) {
            c_v_=0
            c_v++
        } else {
            c_v = 0
            c_v_++
        }
        if(c_v == 3 || c_v_ == 3) {
            c++
            c_v = 1
            c_v_ = 1
        }
    }
    println(c)

}