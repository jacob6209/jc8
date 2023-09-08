package com.example.jc8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jc8.model.MockData
import com.example.jc8.ui.theme.AppDark
import com.example.jc8.ui.theme.AppLight
import com.example.jc8.ui.theme.AppRed
import com.example.jc8.ui.theme.Jc8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jc8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}

@Composable
fun MainView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppDark)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopHeader()
            Text(
                text = "Collections",
                color = AppLight,
                fontSize = 28.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 25.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(MockData.list.size) {
                    var liked by remember {
                        mutableStateOf(MockData.list[it].liked)
                    }
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        elevation = CardDefaults.cardElevation(0.dp),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(230.dp)
                            ) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(170.dp)
                                        .padding(15.dp)
                                        .clip(
                                            RoundedCornerShape(25.dp)
                                        )
                                        .background(AppLight)
                                        .align(Alignment.BottomCenter)
                                ) {
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .background(AppLight)
                                            .padding(15.dp)
                                    ) {
                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .align(Alignment.BottomCenter),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Row() {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.star),
                                                    contentDescription = "",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(15.dp)
                                                )
                                                Spacer(modifier = Modifier.width(5.dp))
                                                Text(
                                                    text = MockData.list[it].rate.toString(),
                                                    fontSize = 12.sp, color = Color.White
                                                )
                                            }
                                            IconButton(
                                                onClick = { liked = !liked },
                                                modifier = Modifier.size(28.dp)
                                            ) {

                                                Icon(
                                                    painter = if (liked) painterResource(
                                                        id = R.drawable.heart_fill
                                                    ) else painterResource(id = R.drawable.heart_empty),
                                                    contentDescription = "",
                                                    tint = if (liked) AppRed else Color.White,
                                                    modifier = Modifier
                                                        .size(40.dp)
                                                        .padding(5.dp)
                                                        .clip(
                                                            RoundedCornerShape(5.dp)
                                                        )
                                                )

                                            }
                                        }

                                    }

                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(70.dp)
                                            .height(40.dp)
                                            .align(Alignment.BottomCenter)
                                            .shadow(
                                                15.dp, CircleShape
                                            )
                                    )
                                    Image(
                                        painter = painterResource(MockData.list[it].image),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .width(160.dp)
                                            .rotate(45f)
                                            .align(Alignment.TopCenter)
                                            .padding(top = 65.dp, start = 25.dp)
                                    )
                                }
                            }

                            Text(
                                text = MockData.list[it].name,
                                color = Color.White,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "${MockData.list[it].price}$", color = AppLight,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }


                    }
                }
            }

        }

    }
}

@Composable
private fun TopHeader() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            Modifier.size(40.dp),
            tint = Color.White
        )

        Icon(
            painter = painterResource(id = R.drawable.menu_dots),
            contentDescription = "",
            Modifier.size(30.dp),
            tint = Color.White
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Jc8Theme {
        MainView()
    }
}