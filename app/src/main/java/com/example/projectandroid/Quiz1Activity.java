package com.example.projectandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectandroid.R;

import java.util.ArrayList;
import java.util.List;

public class Quiz1Activity extends AppCompatActivity {

    private TextView tvQuestion;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnNext;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);

        // สร้างคำถาม
        loadQuestions();
        setQuestion();

        // ตั้งค่าคลิกตัวเลือก
        View.OnClickListener answerListener = view -> {
            Button selectedButton = (Button) view;
            String answer = selectedButton.getText().toString();

            if (answer.equals(correctAnswer)) {
                selectedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                Toast.makeText(Quiz1Activity.this, "ถูกต้อง!", Toast.LENGTH_SHORT).show();
            } else {
                selectedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                Toast.makeText(Quiz1Activity.this, "ผิด!", Toast.LENGTH_SHORT).show();
            }
        };

        btnOption1.setOnClickListener(answerListener);
        btnOption2.setOnClickListener(answerListener);
        btnOption3.setOnClickListener(answerListener);
        btnOption4.setOnClickListener(answerListener);

        // ปุ่ม Next
        btnNext.setOnClickListener(view -> {
            if (currentQuestionIndex < questionList.size() - 1) {
                currentQuestionIndex++; // ไปคำถามถัดไป
                setQuestion(); // อัปเดต UI
            } else {
                Toast.makeText(Quiz1Activity.this, "จบแบบทดสอบแล้ว!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("What is the capital of Japan?", "Tokyo", "Beijing", "Seoul", "Bangkok", "Tokyo"));
        questionList.add(new Question("What is the capital of France?", "London", "Berlin", "Paris", "Rome", "Paris"));
        questionList.add(new Question("What is the capital of Germany?", "Madrid", "Berlin", "Lisbon", "Athens", "Berlin"));
    }

    private void setQuestion() {
        Question q = questionList.get(currentQuestionIndex);
        tvQuestion.setText(q.getQuestion());
        btnOption1.setText(q.getOption1());
        btnOption2.setText(q.getOption2());
        btnOption3.setText(q.getOption3());
        btnOption4.setText(q.getOption4());
        correctAnswer = q.getCorrectAnswer();

        // รีเซ็ตสีปุ่มเป็นค่าเดิม
        btnOption1.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnOption2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnOption3.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnOption4.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    // คลาสเก็บคำถาม
    private static class Question {
        private final String question, option1, option2, option3, option4, correctAnswer;

        public Question(String question, String option1, String option2, String option3, String option4, String correctAnswer) {
            this.question = question;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.option4 = option4;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String getOption1() {
            return option1;
        }

        public String getOption2() {
            return option2;
        }

        public String getOption3() {
            return option3;
        }

        public String getOption4() {
            return option4;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
