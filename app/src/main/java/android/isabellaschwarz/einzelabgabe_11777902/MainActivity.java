package android.isabellaschwarz.einzelabgabe_11777902;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void senden(View view){
        TextView serverantwort = findViewById(R.id.serverAntwort);
        serverantwort.setText(R.string.TestAntwort);
    }
}
