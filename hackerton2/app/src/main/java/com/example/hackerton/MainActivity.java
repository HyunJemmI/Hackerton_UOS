package com.example.hackerton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃 XML에 있는 버튼 가져오기


        // 루트 레이아웃(ConstraintLayout) 전체에 클릭 리스너 추가
        ConstraintLayout mainLayout = findViewById(R.id.main);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuListActivity.class);
                startActivity(intent);
            }
        });
    }
}
