package org.sniffsnirr.rickandmortyonjpc.ui

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.sniffsnirr.rickandmortyonjpc.ui.onlyonecharacter.EpisodeView
import org.sniffsnirr.rickandmortyonjpc.ui.theme.AliveColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.CommentColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.DeadColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.RecyclerviewBackground
import org.sniffsnirr.rickandmortyonjpc.ui.theme.TextColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.UnknownColor
import org.sniffsnirr.rickandmortyonjpc.ApiViewModel
import org.sniffsnirr.rickandmortyonjpc.ui.CharacterViewCompanion.Companion.CHARACTER_COLUMN_TEST_TEG
import org.sniffsnirr.rickandmortyonjpc.ui.CharacterViewCompanion.Companion.CHARACTER_NAME_TEST_TEG

@Composable
fun CharacterView(viewModel: ApiViewModel) {// экран героя

    val character by viewModel.characterFromCache.collectAsState()
    val episodes by viewModel.episodes.collectAsState()
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(8.dp)
            .background(RecyclerviewBackground)
            .verticalScroll(rememberScrollState()).testTag(CHARACTER_COLUMN_TEST_TEG)
    ) {
        AsyncImage(
            model = character?.image,
            contentDescription = "Character pic",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
        Text(
            text = character?.name ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = TextColor,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp, bottom = 8.dp).testTag(CHARACTER_NAME_TEST_TEG)
        )
        val gradient = Brush.horizontalGradient(
            colors = listOf(Color.White, RecyclerviewBackground),
            startX = 0f,
            endX = 300f
        )
        Box(modifier = Modifier
            .height(3.dp)
            .width(300.dp)
            .background(gradient))
        Text(
            text = "Live status:",
            fontSize = 14.sp,
            color = CommentColor,
            modifier = Modifier.padding(top = 8.dp, start = 24.dp, bottom = 4.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp, start = 24.dp)
        ) {
            val currentColor = when (character?.status) {
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
                text = "${character?.status}",
                fontSize = 14.sp,
                color = TextColor,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Text(
            text = "Species and gender:",
            fontSize = 14.sp,
            color = CommentColor,
            modifier = Modifier.padding(top = 8.dp, start = 24.dp, bottom = 4.dp)
        )
        Text(
            text = "${character?.species}(${character?.gender})",
            fontSize = 14.sp,
            color = TextColor,
            modifier = Modifier.padding(bottom = 16.dp, start = 24.dp)
        )

        Text(
            text = "Last known location:",
            fontSize = 14.sp,
            color = CommentColor,
            modifier = Modifier.padding(top = 8.dp, start = 24.dp, bottom = 4.dp)
        )
        Text(
            text = character?.location?.name ?: "",
            fontSize = 14.sp,
            color = TextColor,
            modifier = Modifier.padding(bottom = 16.dp, start = 24.dp)
        )

        Text(
            text = "First seen in:",
            fontSize = 14.sp,
            color = CommentColor,
            modifier = Modifier.padding(top = 8.dp, start = 24.dp, bottom = 4.dp)
        )
        Text(
            text = episodes?.get(0)?.name?:"",
            fontSize = 14.sp,
            color = TextColor,
            modifier = Modifier.padding(bottom = 16.dp, start = 24.dp)
        )

        Text(
            text = "Episodes:",
            fontSize = 18.sp,
            color = TextColor,
            modifier = Modifier.padding(top = 8.dp, start = 24.dp, bottom = 4.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
                .background(RecyclerviewBackground)) {
            episodes?.forEach { episode ->
                EpisodeView(episode)
            }
        }
    }
}
class CharacterViewCompanion(){
    companion object{
        const val CHARACTER_NAME_TEST_TEG="name"
        const val CHARACTER_COLUMN_TEST_TEG="column"
    }
}