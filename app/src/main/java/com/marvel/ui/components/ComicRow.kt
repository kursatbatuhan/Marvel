import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marvel.R
import com.marvel.ui.components.NoResult

@Composable
fun ComicRow(year: Int, hashTag: String?, titleWithoutHashTag: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color.Red, RoundedCornerShape(8.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = hashTag ?: "", fontSize = 16.sp)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = year.toString(),
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 16.sp
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = titleWithoutHashTag, fontSize = 16.sp)
        }
    }
}

@Composable
fun ComicList(comics: List<String>) {
    val parsedComics = comics.mapNotNull { comic ->
        val yearRegex = "\\((\\d{4})\\)".toRegex()
        val matchResult = yearRegex.find(comic)
        val year = matchResult?.groups?.get(1)?.value?.toIntOrNull()

        val titleWithoutYear = yearRegex.replace(comic, "").trim()

        val hashTagRegex = "#\\S+".toRegex()
        val hashTagMatch = hashTagRegex.find(titleWithoutYear)
        val hashTag = hashTagMatch?.value
        val titleWithoutHashTag = hashTagRegex.replace(titleWithoutYear, "").trim()

        if (year != null && year > 2005) {
            Triple(year, hashTag, titleWithoutHashTag)
        } else {
            null
        }
    }.sortedByDescending { it.first }

    val top10Comics = parsedComics.take(10)

    if (top10Comics.isEmpty()) {
        NoResult(imageResId = R.drawable.ic_avengers, text = R.string.no_comics_info)
    }
    Column {
        top10Comics.forEach { (year, hashTag, titleWithoutHashTag) ->
            ComicRow(year, hashTag, titleWithoutHashTag)
        }
    }
}

