package com.example.todolistkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MainActivity : AppCompatActivity() {
    lateinit var flutterEngine : FlutterEngine
    lateinit var btnClickNavigate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         btnClickNavigate = findViewById(R.id.button)
        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put("my_engine_id", flutterEngine)
        btnClickNavigate.setOnClickListener {
            startActivity(
                FlutterActivity
                    .withCachedEngine("my_engine_id")
                    .build(this)
            )
        }
    }
}