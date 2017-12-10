package problems

import org.junit.Assert.*
import org.junit.Test

class SpiralMemoryTest {
  @Test
  fun run_returnsRightAnswers() {
    val instance = SpiralMemory()
    assertEquals(3, instance.run(12))
    assertEquals(2, instance.run(23))
    assertEquals(31, instance.run(1024))
  }
}
