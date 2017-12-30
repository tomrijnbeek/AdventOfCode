class MemoryReallocation {
  fun run() : Int {
    val history = mutableListOf(input)
    var curr = input.clone()
    var cycles = 0

    while (true) {
      cycles++
      var i = curr.indexOf(curr.max()!!)
      var max = curr[i]
      curr[i] = 0
      for (k in max downTo 1) {
        i = (i + 1) % curr.size
        curr[i]++
      }
      val fi = history.indexOfFirst { it.contentEquals(curr) }
      if (fi >= 0) {
        return cycles - fi
      }
      history.add(curr.clone())
    }
  }

  val input = intArrayOf(10,3,15,10,5,15,5,15,9,2,5,8,5,2,3,6)
//  val input = intArrayOf(0,2,7,0)
}