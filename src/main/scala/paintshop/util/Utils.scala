package paintshop.util
import paintshop.model.{ Customer, Paint, TestCase }
import scala.collection.mutable.ListBuffer
object Utils {
    
    val IMPOSSIBLE = "IMPOSSIBLE"

    /**
     * Create test cases with a list strings
     * 
     * @param list a list of strings
     * @return a list of test cases
     */
    def createTestCases(list: List[String]): List[TestCase] = {
        val testCases = ListBuffer[TestCase]()
        val numOfTestCases = list(0).toInt
        var numOfColors: Int = 0
        var numOfCustomers: Int = 0;
        var index: Int = 0

        for (t <- 1 to numOfTestCases) {
            index += 1
            numOfColors = list(index).toInt
            index += 1
            numOfCustomers = list(index).toInt

            val customers = ListBuffer[Customer]()

            for (n <- 1 to numOfCustomers) {
                index += 1
                val preferences = list(index).split("\\s+").toArray.map(_.toInt)
                customers += createCustomer(preferences.slice(1, preferences.length))
            }

            testCases += TestCase(t, numOfColors, customers.toList)
        }

        testCases.toList
    }

    /**
     *  Create a customer instance with paint types the customer likes
     *
     *  @param tokens the Array of paint types in integers, For example,
     *         an Array(1, 0, 3, 1) represents color 1, finish 0, color 3, finish 1. 
     *  @return a Customer
     */
    def createCustomer(tokens: Array[Int]): Customer = {
        val list = ListBuffer[Paint]()

        for (i <- 0 until tokens.length by 2) {
            val paint = Paint(tokens(i), tokens(i + 1))
            list += paint
        }

        Customer(list.toList)
    }
}