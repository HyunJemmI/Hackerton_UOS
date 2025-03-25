package com.example.hackerton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MenuListActivity extends AppCompatActivity {

    // 버튼 id들을 배열에 담습니다.
    private int[] buttonIds = {
            R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.button10, R.id.button11, R.id.button12
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        // 각 버튼에 대해 클릭 리스너 등록
        for (int id : buttonIds) {
            ImageButton button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // XML에서 선언한 id 이름을 가져옴 (예: "button1")
                    String name = getResources().getResourceEntryName(v.getId());
                    Intent intent = new Intent(MenuListActivity.this, RecipeActivity.class);
                    intent.putExtra("RECIPE_TYPE", name);
                    startActivity(intent);
                }
            });
        }
    }
}
