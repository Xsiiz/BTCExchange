import java.lang.NumberFormatException

fun main(args: Array<String>) {
//    pinIntCheck("Test")
    var pin = "125327"
    var pinIntCheckStatus = pinIntCheck(pin)
    if(pinIntCheckStatus) {
        println(pin)
        pinConditionCheck(pin)
    } else {
        println("pin is not number")
    }
}

private fun pinIntCheck(pin : String) : Boolean{
    val pin = try {
        pin.toInt()
        return true
    } catch (e: NumberFormatException) {
        return false
    }
}

private fun pinConditionCheck(pin: String) {
    val pin = pin
    var pinList = arrayListOf<Int>()
    for (i in pin.indices) {
        pinList.add(pin.get(i).toString().toInt())
    }
    if (pinList.size != 6) {
        println("Pin error : Pin has not 6 number")
    } else if (pinList.size == 6) {
        println("Pin number check success")
        pinDuplicateCheck(pinList)
        pinSortCheck(pinList)
        pinSetDuplicateCheck(pinList)
    }
}

private fun pinDuplicateCheck(pinList: ArrayList<Int>) {
    val pinList = pinList
    for (i in 1 until  pinList.size - 1) {
        if (pinList[i] == pinList[i-1] && pinList[i] == pinList[i+1]) {
            println("Pin error : More than 2 duplicate Pins")
            return
        }
    }
    println("Pin duplicate check success")
}

private fun pinSortCheck(pinList: ArrayList<Int>) {
    val pinList = pinList
    for (i in 1 until  pinList.size - 1) {
        if(pinList[i]-1 == pinList[i-1] && pinList[i]+1 == pinList[i+1]) {
            println("Pin error : More than 2 sort Pins")
            return
        }
    }
    println("Pin sort check success")
}

private fun pinSetDuplicateCheck(pinList: ArrayList<Int>) {
    val pinList = pinList
    var count = 0
    for (i in 1 until  pinList.size step 2) {
        if(pinList[i-1] == pinList[i]) {
            count++
            if(count > 2) {
                println("Pin error : More than 2 set duplicate Pins")
                return
            }
        }
    }
    println("Pin set duplicate check success")
}