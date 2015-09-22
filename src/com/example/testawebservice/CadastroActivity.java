package com.example.testawebservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_cadastro);
		
		final EditText edNome = (EditText) findViewById(R.id.editNome);
		final EditText edIdade = (EditText) findViewById(R.id.editIdade);
		Button btnCadastro = (Button) findViewById(R.id.btnCadastrar);
		
		btnCadastro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UsuarioDAO dao = new UsuarioDAO();
				boolean resultado = dao.inserirUsuario(new Usuario(0, edNome.getText().toString(), Integer.parseInt(edIdade.getText().toString())));
				if(resultado){
					finish();
				}else{
					Toast.makeText(CadastroActivity.this, "Error no cadastro", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
