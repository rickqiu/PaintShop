package paintshop.model
/**
 *  A Customer has a list of Paints she likes
 *
 *  @constructor create a new customer with a list of Paints.
 *  @param customerNumber the customer's number in a test case
 *  @param preferenceList the customer's preferred list of paints
 */
case class Customer(preferenceList: List[Paint]) {
    override def toString = f"preferenceList=${preferenceList}"
}