const val HERO_NAME = "Link"
var playerLevel = 0

fun main() {
    println("$HERO_NAME announces her presence to the world")
    println("What level is $HERO_NAME?")
    playerLevel = readLine()?.toIntOrNull() ?:0
    println("$HERO_NAME's level is $playerLevel")

    readBountyBoard()

    println("Time passes...")
    println("$HERO_NAME returns from her quest.")

    playerLevel += 1
    println(playerLevel)
    readBountyBoard()
}

private fun readBountyBoard() {
   val message: String = try {
        val quest: String? = obtainQuest(playerLevel)
        quest?.replace("Nogartse", "xxxxxxx")
            ?.let { censoredQuest ->
                """
            |$HERO_NAME approaches the bounty board. It reads:
            |    "$censoredQuest"
        """.trimMargin()
            } ?: "$HERO_NAME approaches the bounty board, but it is blank."
    } catch (e: Exception) {
        "$HERO_NAME can't read what's on the bounty board."
    }
    println(message)
}

private fun obtainQuest(
    playerLevel: Int,
    hasAngeredBarbarians: Boolean = false,
    hasBefrindedBarbarians: Boolean = true,
    playerClass: String = "Paladin"
): String? {
    if (playerLevel <= 0) {
        throw IllegalArgumentException("The player's level must be at least 1.")
    }
    return when (playerLevel) {
        1 -> "Meet Mr.Bubbles in the land of soft things."
        in 2..5 -> {
            // Проверка возможности дипломатического решения
            val canTalkBarbarians = !hasAngeredBarbarians &&
                    (hasBefrindedBarbarians || playerClass == "barbarian")
            if (canTalkBarbarians) {
                "Convince the barbarians to call off their invasion."
            } else {
                "Save the town from the barbarian invasion."
            }
        }

        6 -> "Locate the enchanted sword."
        7 -> "Recover the long-lost artifact of creation."
        8 -> "Defeat Nogartse, bringer of death and eater of worlds."
        else -> null
    }
}

