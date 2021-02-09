package kg.abdusatarov.collab2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SECOND = 47;
    TextView helloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloTextView = findViewById(R.id.hello_text);

        helloTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(MainActivity.this,SecondActivity.class); //создали конверт с адресатом
                myIntent.putExtra("key", "value"); // добавили данные
                startActivityForResult(myIntent, REQUEST_CODE_SECOND);
            }
        });
    }

    //вызывается каждый раз когда закрывается запущенное с помощью startActivityForResult() активити
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SECOND) { // значит закрылся SecondActivity
            if (resultCode == RESULT_OK) { //значит все получилось
                String message = data.getStringExtra("resultKey"); //message теперь имеет значение "resultValue"
                helloTextView.setText(message);// textview теперь отображает "resultValue"

                //теперь запишем пришедшее сообщение в SharedPrefs
                writeToShared(message);
                //и сразу прочесть эти данные
                readFromSharedPrefs();
            }
        }
    }

    private void writeToShared(String message) {
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("key", message);
        editor.commit();
    }

    private void readFromSharedPrefs() {
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        //editor не нужен для считывания
        String value = preferences.getString("key", "default_value");//второй параметр - значение по умолчанию
        helloTextView.setText(value);// textview теперь отображает "resultValue"
    }
}