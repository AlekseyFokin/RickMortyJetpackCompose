package org.sniffsnirr.rickandmortyonjpc.ui.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.KNode
import org.junit.Rule

import org.junit.Test
import org.sniffsnirr.rickandmortyonjpc.MainActivity
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterViewCompanion.Companion.ITEM_COLUMN_TEST_TEG
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterViewCompanion.Companion.ITEM_NAME_TEST_TEG
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterViewCompanion.Companion.ITEM_ROW_TEST_TEG
import org.sniffsnirr.rickandmortyonjpc.ui.screen.pageObjectOfCharacterViewScreen
import org.sniffsnirr.rickandmortyonjpc.ui.screen.pageObjectOfListChatactersViewScreen

class ListChatactersViewTest : TestCase(kaspressoBuilder = Kaspresso.Builder.withComposeSupport()) {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val mainScreen = pageObjectOfListChatactersViewScreen(composeTestRule)
    private val characterScreen = pageObjectOfCharacterViewScreen(composeTestRule)

    @Test
    fun checkListChatactersView() = run {

        step("Проверка наличия на главном экране в LazyColumn item с индексом 1 - персонаж") {

            mainScreen.listChatacters.assertIsDisplayed()
            mainScreen.nameChatacter.assertIsDisplayed()
            mainScreen.nameChatacter {
                assertTextEquals(NAME)
            }
            mainScreen.nameChatacter.performClick()
        }


        step("Проверка открытия экрана CharacterView c персонажем из step1") {
            characterScreen.columnChatacter.assertIsDisplayed()
            characterScreen.nameChatacter.assertIsDisplayed()
            characterScreen.nameChatacter {
                assertTextEquals(NAME)
            }
        }
    }

    companion object {
        const val NAME = "Morty Smith"
    }
}


