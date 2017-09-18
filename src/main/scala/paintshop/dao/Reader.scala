package paintshop.dao
/**
 * This Reader trait defines methods for data reads.
 */
trait Reader {
    /**
     * load a text file
     *
     * @param fileName the relative file path with file name
     * @return a list of strings, each string in the list is from each line in the file
     */
    def loadTextFile(fileName: String): List[String] = { null }
}