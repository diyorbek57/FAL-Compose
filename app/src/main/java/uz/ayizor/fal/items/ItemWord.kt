package uz.ayizor.fal.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.ayizor.fal.models.Word


@Composable
fun ItemWord(word: Word) {
    val shape = RoundedCornerShape(12.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 25.dp)
                .clip(shape)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 25.dp)
                    .padding(10.dp)
            ) {
                Text(text = word.word_uz!!)
                Text(text = word.word_ru!!)
                Row() {
                    Text(text = word.word_en!!)
                    Spacer(Modifier.requiredWidth(15.dp))
                    Text(text = word.word_en_reading!!)
                }

            }
        }
    }


}

@Preview(name = "Item words")
@Composable
fun ItemWordPreview() {
    ItemWord(word = Word("0", "test_uz", "test_ru", "test_en", "test_en_reding"))
}

