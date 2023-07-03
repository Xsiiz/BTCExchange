fun primeNumber(num: Int) : Boolean{
    if(num == 0 || num ==1) {
        return false
    }
    for(i in 2 until num){
        if(num%i == 0) {
            return false
        }
    }
    return true
}

fun main() {
    var numInput = 100
    var total: ArrayList<Int> = arrayListOf()
    var primeNumberList: ArrayList<Int> = arrayListOf<Int>()
    for (num in 0 until numInput) {
        if(primeNumber(num)) {
            primeNumberList.add(num)
        }
    }
    print(primeNumberList.toString())
}