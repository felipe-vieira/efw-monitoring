package br.com.fiap.coleta;

import br.com.fiap.coleta.cgt.GerenciadorFila;

public class Run {
	
	public static void main(String[] args) {
		
		System.out.println("Iniciando o coletor...");
		GerenciadorFila gerenciador = new GerenciadorFila();
		gerenciador.iniciaGerenciador();
		
	}
	
}
