package kr.co.hanbit.mywidget3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    CheckBox check1;
    CheckBox check2;
    CheckBox check3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);

        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);

        check1.setOnClickListener(this);
        check2.setOnClickListener(this);
        check3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.check1 || id == R.id.check2 || id == R.id.check3) {
            checkText();
        }
    }

    private void checkText() {
        String checkStr = "";

        if (check1.isChecked()) {
            checkStr = check1.getText().toString();
        }

        if (check2.isChecked()) {
            checkStr += "\n" + check2.getText().toString();
        }

        if (check3.isChecked()) {
            checkStr += "\n" + check3.getText().toString();
        }

        text.setText(checkStr);
    }
}