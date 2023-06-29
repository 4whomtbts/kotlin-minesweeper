package minesweeper_refactor.domain.block

enum class OpenState {
    MINE {
        override fun isOpenAroundBlocks(): Boolean {
            throw IllegalStateException("지뢰는 열 수 없는 상태입니다. 다시 지뢰 보드를 생성해 주세요.")
        }
    },
    DO_NOTHING {
        override fun isOpenAroundBlocks(): Boolean = false
    },
    CHAIN_OPEN {
        override fun isOpenAroundBlocks(): Boolean = true
    },
    ;

    abstract fun isOpenAroundBlocks(): Boolean

    companion object {

        fun valueOf(blockState: BlockState): OpenState = when (blockState) {
            BlockState.MINE -> MINE
            BlockState.ZERO -> CHAIN_OPEN
            else -> DO_NOTHING
        }
    }
}