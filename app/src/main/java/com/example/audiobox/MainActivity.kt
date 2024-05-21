package com.example.audiobox

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobox.ui.theme.AudioBoxTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AudioBoxTheme {
                Surface {
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login() {

    var roomId by remember {
        mutableStateOf("")
    }
    
    var username by remember {
        mutableStateOf("")
    }

    var context=LocalContext.current
    
    Column(modifier = Modifier.fillMaxSize().background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Audio Box", fontSize = 32.sp, fontFamily = FontFamily.Monospace,
            color = Color.White)
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = roomId, onValueChange ={
            roomId=it
        }, label = { Text(text = "Room ID")} )
        
        OutlinedTextField(value = username, onValueChange ={
            username=it
        }, label = { Text(text = "Username")} )

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(onClick = {
            roomId= generateRoomId()
            val intent= Intent(context,AudioRoomActivity::class.java)

            intent.putExtra("roomId",roomId)
            intent.putExtra("username",username)
            intent.putExtra("isHost",true)

            context.startActivity(intent)

        }) {
            Text(text = "Start AudioBox")
        }
        Button(onClick = {
            val intent= Intent(context,AudioRoomActivity::class.java)

            intent.putExtra("roomId",roomId)
            intent.putExtra("username",username)
            intent.putExtra("isHost",true)

            context.startActivity(intent)
        }) {
            Text(text = "Join AudiBox")
        }
    }
}

fun generateRoomId():String{
    var id=StringBuilder()
    while(id.length<5){
        var temp= Random.nextInt(10)
        id.append(temp)
    }
    return id.toString()
}