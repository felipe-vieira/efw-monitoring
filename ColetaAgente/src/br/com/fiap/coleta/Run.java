package br.com.fiap.coleta;

import java.util.List;

import br.com.fiap.coleta.bo.SlaBO;
import br.com.fiap.coleta.bo.UsuarioBO;
import br.com.fiap.coleta.cgt.GerenciadorFila;
import br.com.fiap.coleta.entities.Usuario;

public class Run {
	
	public static void main(String[] args) {
		System.out.println("Iniciando o coletor...");

		GerenciadorFila gerenciador = new GerenciadorFila();
		gerenciador.iniciaGerenciador();
		
	}
	
}
