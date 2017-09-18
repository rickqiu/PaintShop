package paintshop.model
/**
 * A TestCase has a case number, the number of colors and a list of customers
 */
case class TestCase(caseNumber: Int, numOfColors: Int, customers: List[Customer]) {
    override def toString = f"(caseNumber=${caseNumber}, numOfColors=${numOfColors}, customers=${customers})\n"
}