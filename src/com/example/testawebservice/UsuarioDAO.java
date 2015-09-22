package com.example.testawebservice;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class UsuarioDAO {

	private static final String URL = "http://192.168.0.29:8080/ExemploWS/services/UsuarioDAO?wsdl";
	private static final String NAME_SPACE = "http://exemplows.videoaula.com.br";

	private static final String INSERIR = "inserirUsuario";
	private static final String EXCLUIR = "excluirUsuario";
	private static final String ATUALIZAR = "atualizarUsuario";
	private static final String BUSCAR_TODOS = "buscarTodosUsuario";
	private static final String BUSCAR_POR_ID = "buscarUsuarioPorID";

	public boolean inserirUsuario(Usuario usuario) {
		
		SoapObject inserirUsuario = new SoapObject(NAME_SPACE, INSERIR);
		SoapObject usr = new SoapObject(NAME_SPACE, "usuario");
		usr.addProperty("id", usuario.getId());
		usr.addProperty("idade", usuario.getIdade());
		usr.addProperty("nome", usuario.getNome());
		
		inserirUsuario.addSoapObject(usr);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirUsuario);
		
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn:" + INSERIR, envelope);
			
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
			return Boolean.parseBoolean(resposta.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean atualizarUsuario(Usuario usuario) {

		SoapObject atualizarUsuario = new SoapObject(NAME_SPACE, ATUALIZAR);
		SoapObject usr = new SoapObject(NAME_SPACE, "usuario");
		usr.addProperty("id", usuario.getId());
		usr.addProperty("idade", usuario.getIdade());
		usr.addProperty("nome", usuario.getNome());
		
		atualizarUsuario.addSoapObject(usr);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(atualizarUsuario);
		
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn:" + ATUALIZAR, envelope);
			
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
			return Boolean.parseBoolean(resposta.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluirUsuario(Usuario usuario) {

		SoapObject excluirUsuario = new SoapObject(NAME_SPACE, EXCLUIR);
		SoapObject usr = new SoapObject(NAME_SPACE, "usuario");
		usr.addProperty("id", usuario.getId());
		usr.addProperty("idade", usuario.getIdade());
		usr.addProperty("nome", usuario.getNome());
		
		excluirUsuario.addSoapObject(usr);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirUsuario);
		
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn:" + EXCLUIR, envelope);
			
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
			return Boolean.parseBoolean(resposta.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Usuario> buscarTodosUsuario() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		SoapObject buscarTodosUsuario = new SoapObject(NAME_SPACE, BUSCAR_TODOS);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodosUsuario);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
		try {
			http.call("urn:" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				Usuario usr = new Usuario();
				usr.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
				usr.setIdade(Integer.parseInt(soapObject.getProperty("idade").toString()));
				usr.setNome(soapObject.getProperty("nome").toString());
				
				lista.add(usr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return lista;
	}

	public Usuario buscarUsuarioPorID(int id) {
		Usuario usr = null;

		SoapObject buscarUsuarioID = new SoapObject(NAME_SPACE, BUSCAR_POR_ID);
		buscarUsuarioID.addProperty("id", id);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarUsuarioID);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
		try {
			http.call("urn:" + BUSCAR_POR_ID, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
			usr = new Usuario();
			usr.setId(Integer.parseInt(resposta.getProperty("id").toString()));
			usr.setIdade(Integer.parseInt(resposta.getProperty("idade").toString()));
			usr.setNome(resposta.getProperty("nome").toString());
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return usr;
	}

	public boolean excluirUsuario(int id) {
		return excluirUsuario(new Usuario(id, "", 0));
	}
}