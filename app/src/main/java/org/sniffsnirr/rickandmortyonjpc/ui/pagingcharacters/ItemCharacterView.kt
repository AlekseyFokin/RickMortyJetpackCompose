package org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.sniffsnirr.rickandmortyonjpc.NavigationMenuItem

import org.sniffsnirr.rickandmortyonjpc.ui.theme.AliveColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.CommentColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.DeadColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.ItemBackground
import org.sniffsnirr.rickandmortyonjpc.ui.theme.TextColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.UnknownColor
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.model.Character
import org.sniffsnirr.rickandmortyonjpc.ApiViewModel
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterViewCompanion.Companion.ITEM_COLUMN_TEST_TEG
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterViewCompanion.Companion.ITEM_NAME_TEST_TEG
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterViewCompanion.Companion.ITEM_ROW_TEST_TEG


@Composable
fun ItemCharacterView(character: Character, navController: NavController,viewModel: ApiViewModel, testTag:String) {// элемент ленивого списка героев
    Card(
        shape = RoundedCornerShape(10.dp),modifier = Modifier.fillMaxWidth(),colors = CardDefaults.cardColors(
            containerColor = ItemBackground
        ),
        onClick = {
            viewModel.loadCharacterFromCache(character.id)// передача параметров через состояние - новое состояние данных перерисовывает экран
            viewModel.loadEpisodes(character.episode)
            navController.navigate(NavigationMenuItem.SCREEN_2)
        }
    ) {
        Row (modifier = Modifier.testTag("${ITEM_ROW_TEST_TEG}_${testTag}")){
            AsyncImage(
                model = character.image,
                contentDescription = "Character pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(start=8.dp,top = 8.dp, bottom = 8.dp)
                    .background(ItemBackground)
                    .testTag("${ITEM_COLUMN_TEST_TEG}_${testTag}")
            ) {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = TextColor,
                    modifier = Modifier.testTag("${ITEM_NAME_TEST_TEG}_${testTag}")
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                ) {
                    val currentColor = when (character.status) {
                        "Alive" -> AliveColor
                        "Dead" -> DeadColor
                        else -> UnknownColor
                    }
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(currentColor, shape = RoundedCornerShape(12.dp))
                    )
                    Text(
                        text = "${character.status} - ${character.species}",
                        fontSize = 14.sp,
                        color = TextColor,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Text(
                    text = "Last known location:",
                    fontSize = 14.sp,
                    color = CommentColor
                )
                Text(
                    text = character.location.name,
                    fontSize = 14.sp,
                    color = TextColor
                )
            }
        }
    }
}

class ItemCharacterViewCompanion(){
    companion object{
        const val ITEM_NAME_TEST_TEG="name"
        const val ITEM_ROW_TEST_TEG="row"
        const val ITEM_COLUMN_TEST_TEG="column"
    }
}