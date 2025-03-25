package com.example.hackerton;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {

    // 3가지 레시피 정의 (예제)
    private String[][] recipeSteps_button2 = {
            {"원두 추출", "18g"},
            {"얼음", "80%"},
            {"물", "찬물"},
            {"샷 담기", "샷 담기"}
    };
    private String[][] recipeSteps_button1 = {
            {"원두 추출", "18g"},
            {"우유", "스티밍 200ml"},
            {"샷 담기", "샷 담기"},
            {"우유", "스티밍 우유 넣기"}
    };
    private String[][] recipeSteps_button3 = {
            {"초코", "38g"},
            {"원두 추출", "18g"},
            {"얼음", "80%"},
            {"우유", "80%"},
            {"샷 담기", "샷 담기"}
    };
    private String[][] recipeSteps_button4 = {
            {"초코", "38g"},
            {"원두 추출", "18g"},
            {"얼음", "80%"},
            {"우유", "80%"},
            {"샷 담기", "샷 담기"}
    };

    private String[][] recipeSteps_button5 = {
            {"초코", "19g"},
            {"원두 추출", "20g"},
            {"얼음", "70%"},
            {"우유", "100%"},
            {"샷 담기", "샷 담기"}
    };

    private String[][] recipeSteps_button6 = {
            {"시나몬", "o"},
            {"초코", "40g"},
            {"원두 추출", "18g"},
            {"얼음", "60%"},
            {"우유", "50%"},
            {"샷 담기", "샷 담기"}
    };

    private String[][] recipeSteps_button7 = {
            {"카라멜", "38g"},
            {"초코", "25g"},
            {"원두 추출", "22g"},
            {"얼음", "80%"},
            {"우유", "70%"},
            {"샷 담기", "샷 담기"}
    };

    private String[][] recipeSteps_button8 = {
            {"원두 추출", "18g"},
            {"얼음", "100%"},
            {"샷 담기", "샷 담기"},
            {"우유", "60%"},
            {"카라멜", "19g"}
    };

    private String[][] recipeSteps_button9 = {
            {"초코", "35g"},
            {"원두 추출", "18g"},
            {"얼음", "75%"},
            {"우유", "90%"},
            {"샷 담기", "샷 담기"},
            {"시나몬", "x"}
    };

    private String[][] recipeSteps_button10 = {
            {"초코", "40g"},
            {"카라멜", "19g"},
            {"원두 추출", "18g"},
            {"얼음", "80%"},
            {"우유", "100%"},
            {"샷 담기", "샷 담기"}
    };

    private String[][] recipeSteps_button11 = {
            {"카라멜", "25g"},
            {"원두 추출", "18g"},
            {"얼음", "90%"},
            {"우유", "70%"},
            {"샷 담기", "샷 담기"},
            {"초코", "30g"}
    };

    private String[][] recipeSteps_button12 = {
            {"초코", "38g"},
            {"원두 추출", "18g"},
            {"얼음", "85%"},
            {"우유", "60%"},
            {"샷 담기", "샷 담기"},
            {"시나몬", "o"}
    };

    // 현재 사용할 레시피 (이전 페이지에서 전달받은 버튼에 따라 결정)
    private String[][] currentRecipeSteps;

    // 각 재료별 양 배열 (예시)
    private String[] coffeebean_Amounts = {"15g", "18g", "20g"};
    private String[] ice_Amounts = {"50%", "70%", "80%"};
    private String[] water_Amounts = {"찬물", "뜨거운물"};
    private String[] shot_Amounts = {"샷 담기"};
    private String[] milk_Amounts = {"스티밍 200ml", "200ml", "스티밍 우유 넣기", "80%"};
    private String[] choco_Amounts = {"19g", "38g"};
    private String[] vanilla_Amounts = {"o", "x"};
    private String[] vanilla_powder_Amounts = {"0", "10", "20"};
    private String[] cinarmon_Amounts = {"x", "o"};
    private String[] caramel_Amounts = {"19", "38"};

    // 진행 단계 상태 (동적 길이: 현재 레시피 단계 수)
    private String[] processSteps;
    private int currentStep = 0;

    private TextView processStatus;
    private ImageView currentDrink;
    // 10개 재료 버튼 (GridLayout에 배치된 버튼 10개)
    private Button[] ingredientButtons = new Button[10];

    // 재료 이름 배열 (버튼에 표시할 텍스트; 오른쪽 영역 GridLayout에 표시)
    private String[] ingredients = {"물", "우유", "샷 담기", "초코", "원두 추출", "얼음", "바닐라", "바닐라 파우더", "시나몬", "카라멜"};

    private boolean[] stepMistakes;  // 각 단계에서 실수 여부를 추적하는 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // 진행 상태 TextView와 음료 이미지 ImageView 연결 (왼쪽 영역)
        processStatus = findViewById(R.id.process_status);
        currentDrink = findViewById(R.id.current_drink);

        // 이전 페이지에서 전달받은 버튼 정보에 따라 레시피 설정
        String recipeType = getIntent().getStringExtra("RECIPE_TYPE");
        if (recipeType != null) {
            switch (recipeType) {
                case "button1":
                    currentRecipeSteps = recipeSteps_button1;
                    break;
                case "button2":
                    currentRecipeSteps = recipeSteps_button2;
                    break;
                case "button3":
                    currentRecipeSteps = recipeSteps_button3;
                    break;
                case "button4":
                    currentRecipeSteps = recipeSteps_button4;
                    break;
                case "button5":
                    currentRecipeSteps = recipeSteps_button5;
                    break;
                case "button6":
                    currentRecipeSteps = recipeSteps_button6;
                    break;
                case "button7":
                    currentRecipeSteps = recipeSteps_button7;
                    break;
                case "button8":
                    currentRecipeSteps = recipeSteps_button8;
                    break;
                case "button9":
                    currentRecipeSteps = recipeSteps_button9;
                    break;
                case "button10":
                    currentRecipeSteps = recipeSteps_button10;
                    break;
                case "button11":
                    currentRecipeSteps = recipeSteps_button11;
                    break;
                case "button12":
                    currentRecipeSteps = recipeSteps_button12;
                    break;
                default:
                    // 기본값 (예제에서는 button1)
                    currentRecipeSteps = recipeSteps_button1;
                    break;
            }
        } else {
            // 인텐트 Extra가 없을 경우 기본 레시피 설정
            currentRecipeSteps = recipeSteps_button1;
        }

        int stepCount = currentRecipeSteps.length;
        processSteps = new String[stepCount];

        // 각 단계에서 실수 여부를 추적하는 배열 초기화
        stepMistakes = new boolean[stepCount];
        for (int i = 0; i < stepCount; i++) {
            processSteps[i] = String.valueOf(i + 1);
            stepMistakes[i] = false;
        }
        currentStep = 0;

        // 재료 버튼들(GridLayout 내) 연결 및 리스너 설정
        setupIngredientButtons();

        // 초기 진행 상태 업데이트
        updateProcessStatus();
    }

    // 각 재료 버튼을 동적으로 연결하고, 클릭 시 양 선택 다이얼로그 표시
    private void setupIngredientButtons() {
        // 버튼 id 배열 (10개 버튼; activity_recipe.xml에 정의되어 있어야 함)
        int[] buttonIds = {
                R.id.ingredient1, R.id.ingredient2, R.id.ingredient3,
                R.id.ingredient4, R.id.ingredient5, R.id.ingredient6,
                R.id.ingredient7, R.id.ingredient8, R.id.ingredient9,
                R.id.ingredient10
        };

        for (int i = 0; i < buttonIds.length; i++) {
            ingredientButtons[i] = findViewById(buttonIds[i]);
            ingredientButtons[i].setText(ingredients[i]);
            final int index = i;
            // 재료 버튼 클릭 시
            ingredientButtons[i].setOnClickListener(v -> {
                // 현재 단계의 재료와 일치하는 경우에만 다이얼로그 표시
                if (currentStep < currentRecipeSteps.length &&
                        ingredients[index].equals(currentRecipeSteps[currentStep][0])) {
                    showAmountDialog(ingredients[index]);
                } else {
                    // 이 재료는 사용할 수 없을 때 실수로 처리하고 세모로 바꾸기
                    stepMistakes[currentStep] = true;
                    processSteps[currentStep] = "△";  // 실수로 세모 표시
                    Toast.makeText(this, "이 재료는 지금 사용할 수 없어요!", Toast.LENGTH_SHORT).show();
                    updateProcessStatus();
                }
            });
        }
    }

    // 재료에 따른 양 선택 다이얼로그 생성
    private void showAmountDialog(String ingredient) {
        // 재료에 해당하는 양 배열 가져오기
        String[] ingredientAmounts = getAmountsForIngredient(ingredient);

        // 잘못된 재료가 클릭되었을 때 처리
        if (ingredientAmounts.length == 0) {
            Toast.makeText(this, "이 재료는 사용하지 않아요!", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(ingredient + " 양 선택");

        builder.setItems(ingredientAmounts, (dialog, which) -> {
            // 정답 결정: 현재 단계의 정답 값이 두 번째 요소라면 이를 정답으로 간주
            String correctAnswer = currentRecipeSteps[currentStep].length > 1 ?
                    currentRecipeSteps[currentStep][1] : currentRecipeSteps[currentStep][0];

            // 선택한 양이 정답인지 확인
            if (ingredientAmounts[which].equals(correctAnswer)) {
                updateProcess(true);  // 정답일 경우
            } else {
                updateProcess(false);  // 오답일 경우
            }
        });

        builder.show();
    }

    // 재료명에 따른 양 배열 반환
    private String[] getAmountsForIngredient(String ingredient) {
        switch (ingredient) {
            case "물":
                return water_Amounts;
            case "우유":
                return milk_Amounts;
            case "샷 담기":
                return shot_Amounts;
            case "초코":
                return choco_Amounts;
            case "원두 추출":
                return coffeebean_Amounts;
            case "얼음":
                return ice_Amounts;
            case "바닐라":
                return vanilla_Amounts;
            case "바닐라 파우더":
                return vanilla_powder_Amounts;
            case "시나몬":
                return cinarmon_Amounts;
            case "카라멜":
                return caramel_Amounts;
            default:
                return new String[]{};
        }
    }

    // 프로세스 상태 업데이트
    private void updateProcess(boolean isCorrect) {
        if (isCorrect) {
            // 만약 이전에 (재료 선택이나 재료 양 등 어떤 이유로든) 실수한 적이 있다면 세모, 아니면 동그라미
            if (!stepMistakes[currentStep]) {
                processSteps[currentStep] = "⭕";
            } else {
                processSteps[currentStep] = "△";
            }

            Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show();
            updateDrinkImage(currentStep + 1);  // 단계별 이미지 업데이트
            currentStep++;  // 다음 단계로 진행
            updateProcessStatus();

            if (currentStep >= currentRecipeSteps.length) {
                Toast.makeText(this, "음료 완성!", Toast.LENGTH_LONG).show();
                // finish();  // 앱이 갑자기 종료되지 않도록 finish() 호출을 제거합니다.
            }
        } else {
            // 재료의 양뿐만 아니라 재료 선택이 틀린 경우도 여기에 해당합니다.
            // 해당 단계에서 실수했다고 기록한 후 세모로 표시합니다.
            stepMistakes[currentStep] = true;
            processSteps[currentStep] = "△";
            Toast.makeText(this, "틀렸어요! 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            updateProcessStatus();
            // currentStep은 그대로 유지하여 올바른 답이 나올 때까지 재시도
        }
    }

    // 진행 상태 텍스트뷰 갱신 (예: "⭕ ➡️ 2 ➡️ 3 ➡️ 4")
    private void updateProcessStatus() {
        processStatus.setText(String.join(" ➡️ ", processSteps));
    }

    // 단계별 음료 이미지 업데이트
    private void updateDrinkImage(int step) {
        // 인텐트로부터 레시피 타입 받아오기
        String recipeType = getIntent().getStringExtra("RECIPE_TYPE");

        int[] drinkImages;

        // 레시피 타입에 따라 이미지 배열 선택
        switch (recipeType) {
            case "button1":
                drinkImages = new int[]{
                        R.drawable.im12, R.drawable.im13,
                        R.drawable.im14, R.drawable.im15
                };
                break;

            case "button2":
                drinkImages = new int[]{
                        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
                        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background
                };
                break;

            case "button3":
                drinkImages = new int[]{
                        R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
                        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground
                };
                break;

            default:
                drinkImages = new int[]{R.drawable.ic_launcher_background};
                break;
        }

        // 단계에 따른 이미지 변경
        if (step <= drinkImages.length && step > 0) {
            currentDrink.setImageResource(drinkImages[step - 1]);
        }
    }
}


