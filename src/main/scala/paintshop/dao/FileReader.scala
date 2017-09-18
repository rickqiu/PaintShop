package paintshop.dao
import scala.io.Source
import scala.collection.mutable.ListBuffer
import java.io.{ FileNotFoundException, IOException }

import paintshop.log.ConsoleLogger
/**
 * This FileReader trait extends Reader trait with ConsoleLogger mixin.
 */
class FileReader() extends Reader with ConsoleLogger {

    override def loadTextFile(fileName: String): List[String] = {
        val inputStringList = ListBuffer[String]()
        try {
            inputStringList ++= Source.fromFile(fileName).getLines().filter { line => !line.isEmpty() }.toList
        } catch {
            case e: FileNotFoundException => {
                warn(e.getMessage)
            }
            case e: IOException => {
                warn(e.getMessage)
            }
        }
        inputStringList.toList
    }
}