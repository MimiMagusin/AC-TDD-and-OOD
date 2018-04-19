import org.assertj.core.api.Assertions
import kotlin.test.Test

class DemoTest {
    @Test
    fun assertionsSimpleDemo() {
        val myNumber = 4
        Assertions.assertThat(myNumber).isGreaterThan(2).isLessThan(5)

        val myStringList = listOf("A", "B", "C")
        Assertions.assertThat(myStringList).hasSize(3)
        Assertions.assertThat(myStringList).contains("A", "B")
    }
}