fun fibonacci(numInput: Int) {
    var fibonacciList: ArrayList<Int> = arrayListOf<Int>()
    for(i in 0 until numInput){
        if(i == 0) {
            fibonacciList.add(0)
        } else if(i == 1) {
            fibonacciList.add(1)
        } else {
            fibonacciList.add(fibonacciList[i-1] + fibonacciList[i-2])
        }
    }
    println(fibonacciList.toString())
    println("Sum Total: " + fibonacciList.sum())
}

fun main() {
    var numInput = 10
    fibonacci(numInput)
}