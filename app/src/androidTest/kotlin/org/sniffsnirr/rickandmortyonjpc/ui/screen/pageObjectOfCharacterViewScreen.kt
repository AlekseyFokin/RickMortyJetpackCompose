package org.sniffsnirr.rickandmortyonjpc.ui.screen

import androidx.compose.ui.test.junit4.ComposeTestRule
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import org.sniffsnirr.rickandmortyonjpc.ui.CharacterViewCompanion.Companion.CHARACTER_COLUMN_TEST_TEG
import org.sniffsnirr.rickandmortyonjpc.ui.CharacterViewCompanion.Companion.CHARACTER_NAME_TEST_TEG
import org.sniffsnirr.rickandmortyonjpc.ui.ListCharactersViewCompanion.Companion.LIST_OF_CHARACTERS_TEST_TAG
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterViewCompanion.Companion.ITEM_NAME_TEST_TEG

class pageObjectOfCharacterViewScreen (val composeTestRule: ComposeTestRule) :
    ComposeScreen<pageObjectOfCharacterViewScreen>(composeTestRule) {
    val columnChatacter: KNode = child { hasTestTag(CHARACTER_COLUMN_TEST_TEG) }
    val nameChatacter: KNode = columnChatacter.child {
        useUnmergedTree = true
        hasTestTag("${CHARACTER_NAME_TEST_TEG}")
    }
    }