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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.chatgpt.ui.theme.ChatGPTTheme
import org.json.JSONArray
import org.json.JSONObject

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
            editText.text.clear();
        }

    }

    fun performAction(input: String){
        resultTv.text = input.toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openai.com/v1/chat/completions"

        val jsonObject = JSONObject()
        val message = JSONObject().apply {
            put("role", "user")
            put("content", input)
        }
        val messages = JSONArray().put(message)

        jsonObject.put("messages", messages)
        jsonObject.put("model", "gpt-3.5-turbo")
        jsonObject.put("temperature", 0)
        jsonObject.put("top_p", 1)
        jsonObject.put("frequency_penalty", 0.0)
        jsonObject.put("presence_penalty", 0.0)
        val stringRequest = object: JsonObjectRequest(
            Request.Method.POST, url,jsonObject,
            Response.Listener<JSONObject> { response ->
                // Display the first 500 characters of the response string.
                response.getJSONArray("choices").getJSONObject(0).getString("finish_reason")
                resultTv.text = "Response is: ${response.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content")}"
            },
            Response.ErrorListener { error ->resultTv.text = messages.toString() }){
            override fun getHeaders(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                // adding headers on below line.
                params["Content-Type"] = "application/json"
                params["Authorization"] =
                    "Bearer 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"
                return params;
            }
        }

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}
