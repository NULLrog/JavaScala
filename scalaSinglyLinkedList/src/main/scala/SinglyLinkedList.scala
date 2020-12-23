class SinglyLinkedList[T](var data : T, var next : SinglyLinkedList[T] = null) {
  var count: Int = 0

  def getData (index : Int) : T={
    var current : SinglyLinkedList[T] = this
    for(i <- 0 until index - 1){
      if (current.next == null)
        throw new NullPointerException()
      current = current.next
    }
    current.data
  }

  def insertElement (data : T) : Unit = {
    var current : SinglyLinkedList[T] = this
    while(current.next!=null) current = current.next
    current.next = new SinglyLinkedList[T](data)
    println("Element ["+count+"] is " + data + " was inserted!");
    count += 1;
  }

  def insertElement (data : T, index : Int) : Unit = {
    var current : SinglyLinkedList[T] =  this
    if (index == 0) {
      val oldElement : SinglyLinkedList[T] = new SinglyLinkedList[T](this.data, this.next)
      this.next = oldElement
      this.data = data
    }
    else{
      for(i <- 0 until index - 1){
        if(current.next == null)
          throw new NullPointerException()
        current = current.next
      }
      val newElement : SinglyLinkedList[T] = new SinglyLinkedList[T](data)
      newElement.next = current.next
      current.next = newElement
    }
    println("Element ["+count+"] is " + data + " was inserted!");
    count += 1;
  }

  def deleteElement(index : Int) : Unit = {
    var current : SinglyLinkedList[T] = this
    if(index == 0 ) {
      if (current.next == null)
        println("List had only 1 element! Can`t delete first element!")
      else {
        this.data = this.next.data
        this.next =  this.next.next
      }
    }
    else {
      for (i <- 0 until index) {
        current = current.next
      }
      println("Element ["+(index)+"] is " + current.next.data + " was deleted!");
      current.next = current.next.next
    }
    count -= 1;
  }

  def deleteElement() : Unit = {
    var current: SinglyLinkedList[T] = this
    for (i <- 0 until count - 1) {
      current = current.next
    }
    println("Element ["+(count)+"] is " + current.next.data + " was deleted!");
    current.next = current.next.next
    count -= 1;
  }

  def showAll() : Unit = {
    var indexCounter = 0
    println("All list is:")
    var current : SinglyLinkedList[T] = this
    while (current.next != null) {
      println("Element ["+indexCounter+"] is " + current.data);
      current = current.next
      indexCounter += 1
    }
    if (current.data != null)
      println("Element ["+indexCounter+"] is " + current.data);
  }

  def ForEach (fe : TForEach[T] ): Unit ={
    var current : SinglyLinkedList[T] = this
    if(this.data == null) return
    current.data = fe.toDo(current.data)
    while(current.next != null) {
      current = current.next
      current.data = fe.toDo(current.data)
    }
  }

  def Sort (comparator : TComparator[T]) : Unit = {
    var current : SinglyLinkedList[T] = this
    var size = count - 1
    for (i <- 0 to size){
      for ( j <- 0 to size){
        if(comparator.compare(current.data, current.next.data)){
          var tmp : T = current.data
          current.data = current.next.data
          current.next.data = tmp
        }
        current = current.next
      }
      current = this
    }
  }
}

trait TForEach[T]{
  def toDo(in : T) : T
}

trait TComparator[T]{
  def compare (first : T, second : T) : Boolean
}