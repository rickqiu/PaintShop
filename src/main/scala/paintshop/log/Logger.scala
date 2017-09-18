package paintshop.log
/**
 * This Logger trait defines log methods.
 */
trait Logger {
    def log(msg: String)
    def info(msg: String) { log(s"INFO: $msg") }
    def warn(msg: String) { log(s"WARN: $msg") }
    def error(msg: String) { log(s"ERROR: $msg") }
}