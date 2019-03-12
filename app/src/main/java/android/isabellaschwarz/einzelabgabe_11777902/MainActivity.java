package android.isabellaschwarz.einzelabgabe_11777902;

import android.os.AsyncTask;
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
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText matrikelnr;
    private TextView serverantw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrikelnr = findViewById(R.id.matrikelnummer);
        serverantw = findViewById(R.id.serverAntwort);
    }


    class ServerANF extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            //benötigt Matrikelnummer
            String matrikelNr = matrikelnr.getText().toString();

          try {
              //stellt verbindung zum Server her
              Socket serverS = new Socket("se2-isys.aau.at", 53212);
              DataOutputStream zS = new DataOutputStream(serverS.getOutputStream());
              BufferedReader vS = new BufferedReader(new InputStreamReader(serverS.getInputStream()));

              //an server senden
              zS.writeBytes(matrikelNr + "\n");
              //antwort vom server
              String antwvomServer = vS.readLine();
              serverS.close();

              return antwvomServer;
          } catch (IOException e){
              e.printStackTrace();
          }
          return null;
        }

    }

    public void senden(View view){
        TextView serverantwort = findViewById(R.id.serverAntwort);
        String matrikelnummer = ((EditText)findViewById(R.id.matrikelnummer)).getText().toString();

        ServerANF anf = new ServerANF();
        anf.execute((Void) null );
        try {
            String antw = anf.get();
            serverantwort.setText(antw);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void berechnen(View view){
        TextView serverantwort = findViewById(R.id.serverAntwort);
        String matrikelnummer = ((EditText)findViewById(R.id.matrikelnummer)).getText().toString();
        String ergebnis = "";
        char[] matrikelNummerChars = matrikelnummer.toCharArray();
        for(int i = 0; i < matrikelNummerChars.length; i++){
            if(primzahl(matrikelNummerChars[i])){
                ergebnis += matrikelNummerChars[i];
            }
        }
        serverantwort.setText(ergebnis);

    }

    private boolean primzahl(char matrikelNummerChar) {
        int zahl = Integer.parseInt(""+matrikelNummerChar);
        if(zahl == 1)
            return true;
        for (int i = 2; i < Math.sqrt(zahl); i++) {
            if(zahl % i == 0){
                return false;
            }
        }
        return true;
    }




}
