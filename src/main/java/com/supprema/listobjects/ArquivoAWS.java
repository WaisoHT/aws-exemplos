package com.supprema.listobjects;

public class ArquivoAWS {

	private String nome;
	private String tamanho;
	private String url;
	private ArquivoTipo tipo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ArquivoTipo getTipo() {
		return tipo;
	}
	public void setTipo(ArquivoTipo tipo) {
		this.tipo = tipo;
	}
}
