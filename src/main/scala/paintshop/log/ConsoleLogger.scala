package paintshop.log
/**
 * This ConsoleLogger trait extends Logger trait
 */
trait ConsoleLogger extends Logger {
    override def log(msg: String) = {
        println(msg)
    }
}