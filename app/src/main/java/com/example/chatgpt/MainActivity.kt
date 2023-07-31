package com.example.chatgpt

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatgpt.ui.theme.ChatGPTTheme

class MainActivity : ComponentActivity() {

    lateinit var sendBtn: ImageButton;
    lateinit var resultTv: TextView;
    lateinit var editText: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendBtn = findViewById(R.id.imageButton);
        resultTv = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextText);

        sendBtn.setOnClickListener {
            performAction(editText.text.toString());
        }
    }

    fun performAction(vararg input: String){
        println("performAction");
    }
}
