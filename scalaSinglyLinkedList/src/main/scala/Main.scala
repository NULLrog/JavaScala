object Main {
  def main(args: Array[String]): Unit = {
    val l: SinglyLinkedList[Int] = new SinglyLinkedList[Int](10)
    l.insertElement(15)
    l.insertElement(13)
    l.showAll()
    l.insertElement(11, 2)
    l.showAll()
    l.deleteElement()
    l.showAll()
    l.deleteElement(0)
    l.showAll()
    println("forEach:")
    l.ForEach(new TForEach[Int]() {
      @Override
      def toDo(data: Int): Int ={
        data * 2
      }
    })
    l.showAll()
    println("Sort:")
    l.Sort(new TComparator[Int]() {
      @Override
      def compare(first: Int, second: Int): Boolean ={
        first>second
      }
    })
    l.showAll()
  }
}
