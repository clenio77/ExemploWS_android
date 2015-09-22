package com.example.testawebservice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listViewUsuarios;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		if(VERSION.SDK_INT > 9){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		Button btnAbriCadastro = (Button) findViewById(R.id.btnTelaCadastro);
		listViewUsuarios = (ListView) findViewById(R.id.listUsuarios);
		
		btnAbriCadastro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainActivity.this, CadastroActivity.class);
				startActivity(it);
			}
		});
		
		UsuarioDAO dao = new UsuarioDAO();
//		SÓ TESTES
//		INSERIR
//		boolean resultado = dao.inserirUsuario(new Usuario(0, "Jose", 15));
//		Log.d("ExemploWS", resultado + "");
		
//		BUSCAR TODOS
//		ArrayList<Usuario> lista = dao.buscarTodosUsuario();
//		Log.d("ExemploWS", lista.toString() + "");
		
//		BUSCAR POR ID
//		Usuario user = dao.buscarUsuarioPorID(6);
//		Log.d("ExemploWS", user.toString() + "");
		
//		ATUALIZAR USUARIO
//		boolean user = dao.atualizarUsuario(new Usuario(6, "teste22", 30));
//		Log.d("ExemploWS", user + "");
		
//		EXCLUIR USUARIO
//		boolean user = dao.excluirUsuario(new Usuario(4, "", 0));
//		Log.d("ExemploWS", user + "");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		UsuarioDAO dao = new UsuarioDAO();
		ArrayList<Usuario> lista = dao.buscarTodosUsuario();
		
		ArrayAdapter<Usuario> adpUsr = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, lista);
		listViewUsuarios.setAdapter(adpUsr);
	}
}
