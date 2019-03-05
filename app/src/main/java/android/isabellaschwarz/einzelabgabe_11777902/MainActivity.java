package android.isabellaschwarz.einzelabgabe_11777902;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void senden(View view){
        TextView serverantwort = findViewById(R.id.serverAntwort);
        String matrikelnummer = ((EditText)findViewById(R.id.matrikelnummer)).getText().toString();

        try {
            Socket clientSocket = new Socket("se2-isys.aau.at",53212);
            DataOutputStream zumServervomClient = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader vomServerzumClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            zumServervomClient.writeBytes(matrikelnummer);
            String Antwort = vomServerzumClient.readLine();
            serverantwort.setText(Antwort);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
