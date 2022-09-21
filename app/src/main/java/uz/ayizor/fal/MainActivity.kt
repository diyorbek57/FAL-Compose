package uz.ayizor.fal

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import uz.ayizor.fal.items.ItemWord
import uz.ayizor.fal.models.Word
import uz.ayizor.fal.ui.theme.FALTheme

@OptIn(ExperimentalPagerApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val tabItems = listOf(getString(R.string.dictionary), getString(R.string.my_words))
            val pagerState = rememberPagerState()
            FALTheme {
                Surface {

                    Column(
                        Modifier.background(color = Color(0xffF8F8F8)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .height(56.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(
                                modifier = Modifier.size(24.dp),
                                onClick = { Toast.makeText(this@MainActivity, "search", Toast.LENGTH_SHORT).show() }
                            ) {
                                Icon(
                                    Icons.Filled.Search,
                                    "contentDescription",
                                )
                            }
                        }


                        TabRow(
                            selectedTabIndex = pagerState.currentPage,
                            backgroundColor = Color(
                                0xffEFEFEF
                            ),
                            modifier = Modifier
                                .height(55.dp)
                                .padding(vertical = 9.dp, horizontal = 10.dp)
                                .clip(RoundedCornerShape(20))
                                .background(Color(0xffEFEFEF))
                                .padding(1.dp),
                            indicator = { tabPositions ->
                                Box {}

                            },
                            divider = {}
                        ) {

                            tabItems.forEachIndexed { index, title ->
                                val selected = pagerState.currentPage == index
                                Tab(
                                    modifier = if (selected) Modifier
                                        .padding(2.dp)
                                        .clip(RoundedCornerShape(15))
                                        .background(
                                            Color.White
                                        )
                                    else Modifier
                                        .background(
                                            Color(
                                                0xffEFEFEF
                                            )
                                        ),
                                    selected = selected,
                                    selectedContentColor = Color.Black,
                                    onClick = { },
                                    text = { Text(text = title) },


                                    )
                            }
                        }
                        HorizontalPager(
                            count = tabItems.size,
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color(0xffF8F8F8)
                                )
                        ) { page ->
                            when (page) {
                                0 -> dictionaryPage()
                                1 -> Text(text = page.toString())
                            }


                        }


                    }

                }
            }
        }
    }

    fun wordsList(): ArrayList<Word> {
        val wordsList: ArrayList<Word> = ArrayList()
        for (i in 0..30) {
            wordsList.add(
                Word(
                    "$i",
                    "test_uz$i",
                    "test_ru$i",
                    "test_en$i",
                    "test_en_reading$i",
                )
            )
        }

        return wordsList
    }

    @Composable
    fun dictionaryPage() {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(wordsList()) { lazyPosition, lazyItem ->
                ItemWord(word = lazyItem)

            }
        }
    }


}

