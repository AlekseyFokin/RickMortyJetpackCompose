package org.sniffsnirr.rickandmortyonjpc.ui.paginglocations

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sniffsnirr.rickandmortyonjpc.repository.model.locations.Location
import org.sniffsnirr.rickandmortyonjpc.ui.theme.CommentColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.ItemBackground
import org.sniffsnirr.rickandmortyonjpc.ui.theme.TextColor

@Composable
fun ItemLocationView(location: Location) {// элемент ленивого списка локаций
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = ItemBackground

        )
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                .background(ItemBackground)
        ) {

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            ) {
                Text(
                    text = "Type location:",
                    fontSize = 14.sp,
                    color = CommentColor
                )

                Text(
                    text = location.type,
                    fontSize = 14.sp,
                    color = TextColor,
                    modifier = Modifier.padding(start = 8.dp, end = 24.dp)
                )

                Text(
                    text = "Name:",
                    fontSize = 14.sp,
                    color = CommentColor
                )

                Text(
                    text = location.name,
                    fontSize = 14.sp,
                    color = TextColor,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            ) {
                Text(
                    text = "Dimension:",
                    fontSize = 14.sp,
                    color = CommentColor
                )

                Text(
                    text = location.dimension,
                    fontSize = 14.sp,
                    color = TextColor,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

        }
    }
}