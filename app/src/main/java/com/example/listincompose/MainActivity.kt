package com.example.listincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listincompose.ui.theme.ListInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {
                    val messages = listOf(
                        Message("Alice", "Hello, Bob!"),
                        Message("Bob", "Hi, Alice!"),
                        Message("Charlie", "What's up, guys?"),
                        Message("Alice", "Hello, Bob!"),
                        Message("Bob", "Hi, Alice!"),
                        Message("Charlie", "What's up, guys?"),
                        Message("Alice", "Hello, Bob!"),
                        Message("Bob", "Hi, Alice!"),
                        Message("Charlie", "What's up, guys?"),
                        Message("Alice", "Hello, Bob!"),
                        Message("Bob", "Hi, Alice!"),
                        Message("Charlie", "What's up, guys?")
                    )

                    MessageList(messages = messages)
                }
            }
        }
    }
}

data class Message(val sender: String, val content: String)

@Composable
fun MessageList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageListItem(message)
        }
    }
}

@Composable
fun MessageListItem(message: Message) {
    Row(modifier = Modifier
        .background(MaterialTheme.colors.background)
        .padding(all = 8.dp)) {
        Image(painter = painterResource(id = R.mipmap.one),
            contentDescription = null,
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(100.dp).border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape) )
        Spacer(modifier = Modifier.width(30.dp))
        var isExpanded by remember {
            mutableStateOf(false)
        }
        //用remember来保存状态
        val surfaceColor:Color by animateColorAsState(if(isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
        //一个动画,当按的时候颜色是MaterialTheme.colors.primary
        //当不按的时候是MaterialTheme.colors.secondary
        Column (modifier = Modifier.clickable{isExpanded=!isExpanded}){
            Surface(
//                shape = MaterialTheme.shapes.medium,
//                shape = MaterialTheme.shapes.large,
                shape = MaterialTheme.shapes.small,
                //large直接没有弧度,medium有一点弧度,small的弧度最大
                elevation = 100.dp,
                //阴影
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = message.content,
                    style = MaterialTheme.typography.body2,
                    fontSize = 30.sp,
                    maxLines = if(isExpanded)Int.MAX_VALUE else 1,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text(text = message.sender,
            fontSize = 30.sp)
        }
    }
}
