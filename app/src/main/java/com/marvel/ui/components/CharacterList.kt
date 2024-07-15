package com.marvel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CharacterList(
    imageUrl: String,
    name: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(2.dp, Color.Red, RoundedCornerShape(8.dp))
            .height(100.dp)
            .wrapContentWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = description,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
        }
    }
}

@Composable
@Preview
fun CharacterListPreview() {
    CharacterList(
        imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/9/a0/4ce18a834b7f5.jpg",
        name = "John Doe",
        description = "Lorem ipsum dolor sit amet",
        onClick = {}
    )
}