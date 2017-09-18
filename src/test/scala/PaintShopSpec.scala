import collection.mutable.Stack
import org.scalatest._

import paintshop.model.Customer
import paintshop.service.ColorMixer
import paintshop.dao.FileReader
import paintshop.util.Utils
class PaintShopSpec extends FlatSpec with Matchers {

    "The ColorMixer on file0.txt" should "output Case #1: 1 0 0 0 0' Case #2: IMPOSSIBLE" in {
        val data = new FileReader().loadTextFile("src/test/resources/file0.txt")
        val testCases = Utils.createTestCases(data)

        for (testCase <- testCases) {
            val colorMixer = new ColorMixer()
            if (testCase.caseNumber == 1) {
                colorMixer.findSolution(testCase.customers, testCase.numOfColors) should be("1 0 0 0 0")
            }

            if (testCase.caseNumber == 2) {
                colorMixer.findSolution(testCase.customers, testCase.numOfColors) should be(Utils.IMPOSSIBLE)
            }
        }
    }

    "The ColorMixer on file1.txt" should "output Case #1: 0 1 0 1 0 Case #2: 1 1" in {
        val data = new FileReader().loadTextFile("src/test/resources/file1.txt")
        val testCases = Utils.createTestCases(data)

        for (testCase <- testCases) {
            val colorMixer = new ColorMixer()
            if (testCase.caseNumber == 1) {
                colorMixer.findSolution(testCase.customers, testCase.numOfColors) should be("0 1 0 1 0")
            }

            if (testCase.caseNumber == 2) {
                colorMixer.findSolution(testCase.customers, testCase.numOfColors) should be("1 1")
            }
        }
    }
}