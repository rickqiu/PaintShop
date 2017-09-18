package paintshop.model
/**
 *  A Paint has color and finish attributes.
 *
 *  @constructor create a new paint with a color and finish.
 *  @param color the paint's color
 *  @param finish the paint's finish (0 to indicate glossy, or 1 to indicated matte)
 */
case class Paint(color: Int, finish: Int) {
    def canEqual(a: Any) = a.isInstanceOf[Paint]
    override def equals(that: Any): Boolean =
        that match {
            case that: Paint => that.canEqual(this) && this.hashCode == that.hashCode
            case _ => false
        }

    override def hashCode: Int = {
        val prime = 31
        var result = 1
        result = prime * result + color;
        result = prime * result + finish
        return result
    }

    override def toString = f"(color=${color}, finish=${finish})"
}