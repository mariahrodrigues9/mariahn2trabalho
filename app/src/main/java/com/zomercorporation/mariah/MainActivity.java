package com.zomercorporation.mariah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nome;
    private TextView ano;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadPessoa download = new DownloadPessoa ();

        nome = (TextView)findViewById(R.id.formulario_nome);
        ano = (TextView)findViewById(R.id.formulario_ano);

        download.execute();
    }

    private class DownloadPessoa extends AsyncTask<Void, Void, Pessoa> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Pessoa doInBackground(Void... params){
            Conversor util = new Conversor();
            return util.getInformacao("https://reqres.in/api/user/1");
        }

        @Override
        protected void onPostExecute(Pessoa pessoa){
            nome.setText(pessoa.getNome());
            ano.setText(pessoa.getAno());
            load.dismiss();

        }
    }
}
