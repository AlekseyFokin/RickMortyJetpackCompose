package org.sniffsnirr.rickandmortyonjpc.ui.onlyonecharacter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sniffsnirr.rickandmortyonjpc.repository.model.episodes.Episode
import org.sniffsnirr.rickandmortyonjpc.ui.theme.CommentColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.ItemBackground
import org.sniffsnirr.rickandmortyonjpc.ui.theme.TextColor

@Composable
fun EpisodeView(episode: Episode) { // информация про один эпизод - для списка эпизодов
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = ItemBackground
        )
    ) {

        Column {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = episode.name,
                    fontSize = 16.sp,
                    color = TextColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, bottom = 4.dp)
                )
                Text(
                    text = episode.episode,
                    fontSize = 12.sp,
                    color = CommentColor,
                    modifier = Modifier.padding(top = 8.dp,bottom = 4.dp),
                )
            }

            Text(
                text = episode.air_date,
                fontSize = 12.sp,
                color = TextColor,
                modifier = Modifier.padding(bottom = 16.dp, start = 16.dp)
            )
        }

    }
}
