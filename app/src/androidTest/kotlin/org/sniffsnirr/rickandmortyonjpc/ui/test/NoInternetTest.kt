package org.sniffsnirr.rickandmortyonjpc.ui.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.sniffsnirr.rickandmortyonjpc.MainActivity
import org.sniffsnirr.rickandmortyonjpc.ui.screen.pageObjectOfListChatactersViewScreen
import org.sniffsnirr.rickandmortyonjpc.ui.screen.pageObjectOfListLocationsViewScreen

class NoInternetTest : TestCase(kaspressoBuilder = Kaspresso.Builder.withComposeSupport()) {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val mainScreen = pageObjectOfListChatactersViewScreen(composeTestRule)
    private val locationsScreen = pageObjectOfListLocationsViewScreen(composeTestRule)

    @Test
    fun checkNoInternetEvent() = before { device.network.disable()}.after {device.network.enable()}.run {
        step("Проверка наличия кнопки повтора загрузки при отключении интернета") {
            mainScreen.menuButtonGoToListLocationView.assertIsDisplayed()
            mainScreen.menuButtonGoToListLocationView.performClick()
            locationsScreen.noInternetButton.assertIsDisplayed()
        }
    }
}