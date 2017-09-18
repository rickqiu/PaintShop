package paintshop.service

import paintshop.model._
import paintshop.util.Utils
import paintshop.dao.Reader
import scala.collection.mutable.{ Map, ListBuffer }
import scala.util.control.Breaks._

/**
 * This class is used for finding a solution e.g. a mix of paint types of colors and finishes 
 * to satisfy all customers in a test case.
 * If a solution can not found, the console will output IMPOSSIBLE for the test case.
 * 
 * @author rqiu
 * Date 31-Jul-2017
 */
class ColorMixer {
    
    // The map with pairs (color -> finish) for a solution
    val fixedFinishes: Map[Int, Int] = Map()
    
    /**
     * Find a solution with a list of customers and the number of colors in a test case
     * 
     * @param customerList the list of customers in a test case
     * @param numOfColors the number of paint colors 
     * @return a String that represents a solution
     */
    def findSolution(customerList: List[Customer], numOfColors: Int): String = {

        val customers = customerList.sortBy { c => c.preferenceList.length }

        for (customer <- customers) {
            breakable {
                if (customer.preferenceList.length == 1) {
                    recommendPaintForCustomer(customer, null) match {
                        case None => return Utils.IMPOSSIBLE
                        case Some(p) => fixedFinishes += p.color -> p.finish
                    }
                } else {
                    val paintCandidates = ListBuffer[Paint]()
                    recommendPaintForCustomer(customer, paintCandidates) match {
                        case None => { if (paintCandidates.size == 0) { return Utils.IMPOSSIBLE } }
                        case Some(p) => break
                    }
                    
                    var paintToSelect = paintCandidates(0)
                    for (p <- paintCandidates) {
                        if (p.finish == 0) {
                            paintToSelect = p
                        }
                    }

                    fixedFinishes += paintToSelect.color -> paintToSelect.finish
                }
            }

        }

        getOutputString(fixedFinishes, numOfColors)
    }

    /**
     * Recommend a paint for a customer or populate a candidate list for customer who has more preferences
     * 
     * @param customer the customer we have to satisfy
     * @param paintCandidates the paint candidates list
     * @return an Option of Paint
     */
    def recommendPaintForCustomer(customer: Customer, paintCandidates: ListBuffer[Paint]): Option[Paint] = {

        for (p <- customer.preferenceList) {
            val fixedFinish = fixedFinishes.getOrElse(p.color, None)

            if (customer.preferenceList.length == 1) {
                if (fixedFinish == None || fixedFinish == p.finish) {
                    return Some(p)
                } else {
                    return None
                }
            } else {
                if (fixedFinish == None) {
                    paintCandidates += p
                } else if (fixedFinish == p.finish) {
                    return Some(p)
                }
            }
        }
        None
    }

    /**
     * Get a string for output
     * 
     * @ param fixedFinishes the final finishes map with pairs (color -> finish)
     * @ return a String of output
     */
    def getOutputString(fixedFinishes: Map[Int, Int], numOfColors: Int) = {
        val sb: StringBuilder = new StringBuilder

        for (i <- 1 to numOfColors) {
            val v = fixedFinishes.getOrElse(i, 0)
            if (sb.length > 0) sb.append(" ")
            sb.append(v)
        }

        sb.toString()
    }
}