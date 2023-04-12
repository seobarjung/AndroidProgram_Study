package kr.co.hanbit.intentotherdataactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        String name = getIntent().getStringExtra("name");

        TextView nameText = findViewById(R.id.name);
        nameText.setText(name);
    }
}
