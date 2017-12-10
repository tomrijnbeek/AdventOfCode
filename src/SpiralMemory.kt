import kotlin.coroutines.experimental.buildSequence
import kotlin.math.absoluteValue

val up = Pair(0, 1)
val right = Pair(1, 0)
val down = Pair(0, -1)
val left = Pair(-1, 0)

class SpiralMemory {
  fun run(input : Int) : Int {
    var i = 1
    while (i * i < input) i += 2

    i -= 2

    var stepsOnCircle = input - (i*i)
    while (stepsOnCircle > i) stepsOnCircle -= (i + 1)
    val stepsFromCorner = stepsOnCircle - 1
    println(stepsOnCircle)
    val stepsFromCenter = (stepsFromCorner - (i - 1) / 2).absoluteValue
    println(stepsFromCenter)

    return (i - 1) / 2 + 1 + stepsFromCenter
  }

  fun run2(input : Int) : Int {
      var arr = IntArray(1000 * 1000)
      arr[flattenCoords(500, 500)] = 1

      var i = 3

      while (true) {
        val cycleLength = i*i - (i-2)*(i-2)

        val offset = (i - 1) / 2
        var currCoords = Pair(500 - offset, 500 - offset + 1)
        var direction = up

        for (k in 0 until cycleLength) {
          val sum = neighbours(currCoords).sumBy { arr[flattenCoords(it.first, it.second)] }
          arr[flattenCoords(currCoords.first, currCoords.second)] = sum

          println(sum)
          if (sum > input)
            return sum

          if (k == cycleLength - 1) {
            break
          }

          currCoords = Pair(currCoords.first + direction.first, currCoords.second + direction.second)
          if ((currCoords.first - 500).absoluteValue + (currCoords.second - 500).absoluteValue > 2 * offset) {
            currCoords = Pair(currCoords.first - direction.first, currCoords.second - direction.second)
            direction = nextDirection(direction)
            currCoords = Pair(currCoords.first + direction.first, currCoords.second + direction.second)
          }
        }

        i += 2
      }
  }

  fun flattenCoords(x : Int, y : Int) : Int {
    return 1000 * x + y
  }

  fun nextDirection(dir : Pair<Int, Int>) : Pair<Int, Int> {
    return mapOf(up to right, right to down, down to left)[dir]!!
  }

  fun neighbours(point : Pair<Int, Int>) : Sequence<Pair<Int, Int>> {
    return buildSequence {
      for (i in -1..1)
        for (j in -1..1)
          if (i != 0 || j != 0)
            yield(Pair(point.first + i, point.second + j))
    }
  }
}