package com.example.hackerton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView;
    private TextView xpTextView;
    private ImageView rankProfileImageView;
    private TextView rankTextView;
    private ProgressBar xpProgressBar;
    private TextView nextRankXPTextView;
    private Button menuButton;

    // SharedPreferences key 이름
    private static final String PREFS_NAME = "XP_PREFS";
    private static final String KEY_TOTAL_XP = "total_xp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.resultTextView);
        xpTextView = findViewById(R.id.xpTextView);
        rankProfileImageView = findViewById(R.id.rankProfileImageView);
        rankTextView = findViewById(R.id.rankTextView);
        xpProgressBar = findViewById(R.id.xpProgressBar);
        nextRankXPTextView = findViewById(R.id.nextRankXPTextView);
        menuButton = findViewById(R.id.menuButton);

        // RecipeActivity에서 전달한 processSteps 배열 받기
        String[] processSteps = getIntent().getStringArrayExtra("processSteps");
        int xpEarned = 0;
        if (processSteps != null && processSteps.length > 0) {
            int totalSteps = processSteps.length;
            int correctCount = 0;
            for (String step : processSteps) {
                if ("⭕".equals(step)) {
                    correctCount++;
                }
            }
            // 경험치 = (정답 개수 / 전체 단계) * 100
            xpEarned = (int)(((double) correctCount / totalSteps) * 100);

            resultTextView.setText("총 " + totalSteps + "단계 중 " + correctCount + "단계 정답!");
            xpTextView.setText("이번 세션 획득 XP: " + xpEarned + " XP");
        } else {
            resultTextView.setText("결과 데이터가 없습니다.");
            xpTextView.setText("");
        }

        // 누적 경험치 업데이트 (SharedPreferences 이용)
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int totalXP = prefs.getInt(KEY_TOTAL_XP, 0);
        totalXP += xpEarned;

        // 누적 경험치 저장
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_TOTAL_XP, totalXP);
        editor.apply();

        // 랭크 결정 (bronze: 0~100, silver: 100~250, gold: 250~500)
        String rank;
        int rankCurrentXP; // 현재 랭크 내 누적 XP
        int rankMaxXP;     // 해당 랭크에 필요한 XP (다음 랭크까지)
        int nextRankThreshold; // 다음 랭크의 최소 XP

        if (totalXP < 100) {
            rank = "Bronze";
            rankCurrentXP = totalXP;
            rankMaxXP = 100;
            nextRankThreshold = 100;
        } else if (totalXP < 250) {
            rank = "Silver";
            rankCurrentXP = totalXP - 100;
            rankMaxXP = 150; // 250 - 100
            nextRankThreshold = 250;
        } else if (totalXP < 500) {
            rank = "Gold";
            rankCurrentXP = totalXP - 250;
            rankMaxXP = 250; // 500 - 250
            nextRankThreshold = 500;
        } else {
            // 최대 랭크 고정 (500 이상이면 gold 유지)
            rank = "Gold";
            rankCurrentXP = rankMaxXP = 250;
            nextRankThreshold = 500;
        }

        // UI 업데이트
        rankTextView.setText("현재 랭크: " + rank);
        xpProgressBar.setMax(rankMaxXP);
        xpProgressBar.setProgress(rankCurrentXP);
        int remainingXP = rankMaxXP - rankCurrentXP;
        nextRankXPTextView.setText("다음 랭크까지: " + remainingXP + " XP");

        // 랭크에 따른 프로필 원 색상 설정
        int circleColor;
        switch (rank) {
            case "Silver":
                circleColor = 0xFFC0C0C0; // 실버 (회색)
                break;
            case "Gold":
                circleColor = 0xFFFFD700; // 골드 (노란색)
                break;
            case "Bronze":
            default:
                circleColor = 0xFFCD7F32; // 브론즈 (청동색)
                break;
        }
        // 원 모양 drawable 생성 후 ImageView에 적용
        GradientDrawable circleDrawable = new GradientDrawable();
        circleDrawable.setShape(GradientDrawable.OVAL);
        circleDrawable.setColor(circleColor);
        circleDrawable.setSize(120, 120);
        rankProfileImageView.setImageDrawable(circleDrawable);

        // 메뉴 버튼 클릭 시 MenuListActivity로 이동
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MenuListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
