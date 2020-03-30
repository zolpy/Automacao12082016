package com.example.alander.automacao12082016;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnLed1, btnLed2, btnLed3, btnAutomatico;
    TextView txtResultado;
    String comando = "";
    boolean test1=true;
    boolean test2=true;
    boolean test3=true;
    boolean test4=true;
    String teste10= "";
    String teste11= "";
    String teste12= "";
    String teste13= "";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    //final Handler handler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLed1 = (Button) findViewById(R.id.btnLed1);
        btnLed2 = (Button) findViewById(R.id.btnLed2);
        btnLed3 = (Button) findViewById(R.id.btnLed3);
        btnAutomatico = (Button) findViewById(R.id.btnAutomatico);

        txtResultado = (TextView) findViewById(R.id.txtResultado);

        solicita("");

        //handler.postDelayed(atualizaStatus,0);
/*
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute("http://177.105.38.229:8090/teste.html");
        } else {
            txtResultado.setText("Num encontro poha nenhuma");
        }
*/


        btnLed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(test1==true){
                    teste10="led1";
                    test1=false;
                } else{
                    teste10="teta";
                    test1=true;
                }

                solicita("led1");
            }
        });

        btnLed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(test2==true){
                    teste11="led2";
                    test2=false;
                } else{
                    teste11="teta2";
                    test2=true;
                }

                solicita("led2");
            }
        });

        btnLed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(test3==true){
                    teste12="led3";
                    test3=false;
                } else{
                    teste12="teta3";
                    test3=true;
                }

                solicita("led3");
            }
        });


        btnAutomatico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(test4==true){
                    teste13="automatico";
                    test4=false;

                }else{
                    teste13="teta4";
                    test4=true;
                }

                solicita("automatico");
            }
        });




        /*btnLed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Led2 acinado", Toast.LENGTH_SHORT).show();
            }
        });

        btnLed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Led3 acinado", Toast.LENGTH_SHORT).show();
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void solicita(String comando) {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        String url = "http://177.105.38.240:8090/" + comando ;

        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(url);
        } else {
            txtResultado.setText("nenhuma conexao detectada");
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alander.tt/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alander.tt/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


   /* private Runnable atualizaStatus= new Runnable() {
        @Override
        public void run() {
            solicita("");
            handler.postDelayed(this,2000);
        }
    };*/

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            Conexao conexao = new Conexao();

            return conexao.getArduino(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.

        @Override
        protected void onPostExecute(String result) {

            if (result != null) {

                txtResultado.setText(result);

                if(result.contains("Led 1 - ")&&(teste10.contains("led1"))  ){
                    btnLed1.setText("LED 1 - ON ");
                }

                if(result.contains("Led 1 - ")&&(teste10.contains("teta"))  ){
                    btnLed1.setText("LED 1 - OFF ");
                }



                if(result.contains("Led 2 - ")&&(teste11.contains("led2"))  ){
                    btnLed2.setText("LED 2 - ON ");
                }

                if(result.contains("Led 2 - ")&&(teste11.contains("teta2"))  ){
                    btnLed2.setText("LED 2 - OFF ");
                }


                if(result.contains("Led 3 - ")&&(teste12.contains("led3"))  ){
                    btnLed3.setText("LED 3 - ON ");
                }

                if(result.contains("Led 3 - ")&&(teste12.contains("teta3"))  ){
                    btnLed3.setText("LED 3 - OFF ");
                }

                if(result.contains("Automatico")&&(teste13.contains("automatico"))  ){
                    btnAutomatico.setText("Automatico");
                }

                if(result.contains("Manual")&&(teste13.contains("teta4"))  ){
                    btnAutomatico.setText("Manual");
                }

                /*if(result.contains("Led 1 - ")  ){
                    if( teste10.contains("led1")){
                        btnLed1.setText("LED 1 - ON ");
                    }else {
                        test1=true;
                        btnLed1.setText("LED 1 - OFF ");
                    }
                }

                if(result.contains("Led 2 - ")  ){
                    if( comando.contains("led2")){
                        test2=false;
                        btnLed2.setText("LED 2 - ON ");
                    }else {
                        test2=true;
                        btnLed2.setText("LED 2 - OFF ");
                    }
                }*/

            } else {
                txtResultado.setText("Ocorreu Erro");

            }
        }
    }


}
