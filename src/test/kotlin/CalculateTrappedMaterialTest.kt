import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

class CalculateTrappedMaterialTest {

    @Test
    fun `should return 0 when array is flat`() {
        val input = IntArray(1000) { 0 }
        val expectedVolume = 0

        val actualVolume = material(input)
        assertEquals(expectedVolume, actualVolume)
    }

    @Test
    fun `should return 0 when array is increasing`() {
        val increasingArray = IntArray(1000) { it }
        val expectedVolume = 0

        val actualVolume = material(increasingArray)
        assertEquals(expectedVolume, actualVolume)
    }

    @Test
    fun `should return 0 when array is decreasing`() {
        val decreasingArray = IntArray(1000) { 1000 - it }
        val expectedVolume = 0

        val actualVolume = material(decreasingArray)
        assertEquals(expectedVolume, actualVolume)
    }

    @Test
    fun `should return 0 when empty array`() {
        val emptyArray = IntArray(0)
        val expectedVolume = 0

        val actualVolume = material(emptyArray)
        assertEquals(expectedVolume, actualVolume)
    }

    @Test
    fun `should return non-negative volume smaller than area times highest bar`() {
        val random = Random.Default
        val heights = IntArray(1000) { random.nextInt(-1000, 1000) }
        val maxHeight = (heights.maxOrNull() ?: 0) - (heights.minOrNull() ?: 0)
        val expectedVolumeUpperLimit = heights.size * maxHeight

        val actualVolume = material(heights)

        assertTrue(actualVolume >= 0)
        assertTrue(actualVolume <= expectedVolumeUpperLimit)
    }

    @Test
    fun `should return positive volume for array with negative values`() {
        val random = Random.Default
        val heights = IntArray(1000) { random.nextInt(-1000, 0) }

        val actualVolume = material(heights)

       assertTrue(actualVolume >= 0)
    }

    @Test
    fun `should not modify input array`() {
        val random = Random.Default
        val input = IntArray(1000) { random.nextInt(-1000, 1000) }
        val originalInput = input.copyOf()

        material(input)

        assertArrayEquals(originalInput, input)
    }

    @Test
    @Timeout(1000)
    fun `should calculate trapped material for large array`() {
        val random = Random.Default
        val input = IntArray(10_000_000) { random.nextInt(-10000, 10000) }

        val actualVolume = material(input)

        assertTrue(actualVolume >= 0)
    }

    @ParameterizedTest
    @MethodSource("provideCustomTestData")
    fun `should return expected volume for custom data`(heights: IntArray, expectedVolume: Int) {
        val actualVolume = material(heights)
        assertEquals(expectedVolume, actualVolume)
    }

    companion object {
        @JvmStatic
        fun provideCustomTestData() = listOf(
            arguments(intArrayOf(0, 1, -1, 2, -2, 0, 1), 6),
            arguments(intArrayOf(6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3), 83),
            arguments(intArrayOf(6, 2, 1, 1, 8, 0, 5, 5, 0, 1, 8, 9, 6, 9, 4, 8, 0, 0), 50)
        )
    }
}