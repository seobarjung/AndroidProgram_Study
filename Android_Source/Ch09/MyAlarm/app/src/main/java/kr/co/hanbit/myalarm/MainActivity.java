package kr.co.hanbit.myalarm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButton();
    }

    private void setButton() {
        findViewById(R.id.alarm1).setOnClickListener(this);
        findViewById(R.id.alarm2).setOnClickListener(this);
        findViewById(R.id.alarm3).setOnClickListener(this);
        findViewById(R.id.alarm4).setOnClickListener(this);
        findViewById(R.id.alarm5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.alarm1) {
            Toast.makeText(this, "TOAST!!!", Toast.LENGTH_SHORT).show();

        } else if (v.getId() == R.id.alarm2) {
            Snackbar.make(v, "Snackbar!!!", Snackbar.LENGTH_SHORT).show();

        } else if (v.getId() == R.id.alarm3) {
            Snackbar.make(v, "Snackbar!!!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("닫기", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }).setActionTextColor(Color.RED).show();

        } else if (v.getId() == R.id.alarm4) {
            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("Dialog")
                    .setMessage("메시지를 보여줍니다!!")
                    .setNeutralButton("닫기", null)
                    .setPositiveButton("네", null)
                    .setNegativeButton("아니오", null)
                    .show();

        } else if (v.getId() == R.id.alarm5) {
            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("Dialog")
                    .setMessage("메시지를 보여줍니다!!")
                    .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this,
                                    "다이얼로그를 닫습니다.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        }
    }
}