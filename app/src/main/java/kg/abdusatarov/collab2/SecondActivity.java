package kg.abdusatarov.collab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView messageTextView;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        messageTextView = findViewById(R.id.message);
        exitButton = findViewById(R.id.exit);

        Intent myIntent = getIntent();
        String myValue = myIntent.getStringExtra("key"); //myValue принял значение "value"
        messageTextView.setText(myValue); //textView отображает текст "value"

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();//пустой конверт
                resultIntent.putExtra("resultKey", "resultValue");
                setResult(RESULT_OK, resultIntent);//обязательно передать интент в результате если хотите вернуть данные
                finish();
            }
        });
    }
}