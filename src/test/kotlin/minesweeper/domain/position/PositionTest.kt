package minesweeper.domain.position

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertThrows

class PositionTest : FunSpec({

    context("Position 객체가 정상적으로 생성된다") {
        val givenHeight = 20
        val givenWidth = 20

        withData(
            nameFn = { "$it" },
            (givenHeight.indexRange() comma givenWidth.indexRange())
                .map { it.row to it.col }
        ) { (row, col) ->
            row shouldBeGreaterThanOrEqual 0
            col shouldBeGreaterThanOrEqual 0

            val position = Position(row, col)

            position shouldNotBe null
            position.row shouldBe row
            position.col shouldBe col
        }
    }

    context("음수 row 혹은 음수 col일 경우, Position 생성시 IllegalArgumentException 발생") {
        withData(
            nameFn = { "$it" },
            listOf(
                -1 to 0,
                2 to -3,
                -4 to -2
            )
        ) { (row, col) ->
            assertThrows<IllegalArgumentException> {
                Position(row, col)
            }
        }
    }
})