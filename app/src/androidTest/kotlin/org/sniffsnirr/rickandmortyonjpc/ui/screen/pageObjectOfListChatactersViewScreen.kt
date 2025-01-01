package org.sniffsnirr.rickandmortyonjpc.ui.screen

import androidx.compose.ui.test.junit4.ComposeTestRule
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import org.sniffsnirr.rickandmortyonjpc.ui.ListCharactersViewCompanion.Companion.LIST_OF_CHARACTERS_TEST_TAG
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterViewCompanion.Companion.ITEM_NAME_TEST_TEG

class pageObjectOfListChatactersViewScreen(val composeTestRule: ComposeTestRule) :
    ComposeScreen<pageObjectOfListChatactersViewScreen>(composeTestRule) {
    val listChatacters: KNode = child { hasTestTag(LIST_OF_CHARACTERS_TEST_TAG) }

    val menuButtonGoToListLocationView: KNode = child {
        useUnmergedTree = true
        hasTestTag("menu_button_2")
    }

    val nameChatacter: KNode = listChatacters.child {
        useUnmergedTree = true
        hasTestTag("${ITEM_NAME_TEST_TEG}_1")
    }


}

