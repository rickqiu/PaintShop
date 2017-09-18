package paintshop.controller
import paintshop.model.Customer
import paintshop.service.ColorMixer
import paintshop.dao.FileReader
import paintshop.log.ConsoleLogger
import paintshop.util.Utils
import scala.util.control.Breaks._

/**
 * This class acts as a controller to control and delegates tasks
 * 
 * @author rqiu
 * Date: 31-Jul-2017
 */
class MainController extends ConsoleLogger {
    
    /**
     * Sum of T values for customers in a test case
     * 
     * @param customers the customer list
     */
    def sumT(customers: List[Customer]) = {
        var total = 0
        for (c <- customers) {
            total += c.preferenceList.size
        }
        
        total 
    }
    
    /**
     * Execute by processing each file passed in as arguments
     */
    def execute(args: Array[String]) = {
        for (fileName: String <- args) {
            val data = new FileReader().loadTextFile(fileName)
            val testCases = Utils.createTestCases(data)

            var lines = 0
            var limit = 0
            for (testCase <- testCases) {
                breakable {
      
                    if (testCase.numOfColors < 11 && testCase.customers.size < 101) {
                        limit = 100
                    } else if (testCase.numOfColors < 2001 && testCase.customers.size < 2001) {
                        limit = 5
                    }
                    
                    val total = sumT(testCase.customers)
                    if (total > 3000){
                        warn(f"Case #${testCase.caseNumber}: T values exceeds 3000. Skip the test case.")
                        break  
                    }
                    
                    val engine = new ColorMixer()
                    val result = engine.findSolution(testCase.customers, testCase.numOfColors)
                    val builder = StringBuilder.newBuilder
                    builder.append("Case #").append(testCase.caseNumber).append(": ").append(result)

                    if (lines < limit) log(builder.toString())
                    lines += 1
                }
            }
        }
    }
}