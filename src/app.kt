import problems.*

val problems : Array<Problem> = arrayOf(
  InverseCaptcha(),
  Checksum()
)

fun main(args: Array<String>) {
  println("Select a problem to run")
  for (i in problems.indices) {
    println("$i: ${problems[i]::class}")
  }
  val input = readLine()
  if (!input.isNullOrBlank()) {
    val index = input!!.toInt()
    problems[index].run()
  }
}
