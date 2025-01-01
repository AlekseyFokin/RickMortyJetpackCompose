package org.sniffsnirr.rickandmortyonjpc.ui.screen

import androidx.compose.ui.test.junit4.ComposeTestRule
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import org.sniffsnirr.rickandmortyonjpc.ui.ListLocationsViewCompanion.Companion.NO_INTERNET_BUTTON

class pageObjectOfListLocationsViewScreen(val composeTestRule: ComposeTestRule) :
    ComposeScreen<pageObjectOfListLocationsViewScreen>(composeTestRule) {
    val noInternetButton: KNode = child {
        useUnmergedTree = true
        hasTestTag(NO_INTERNET_BUTTON)
    }
}