package com.supprema.listobjects;

public enum ArquivoTipo {
	
	jpeg("jpeg", TipoDeMidia.Imagem),
	jpg("jpg", TipoDeMidia.Imagem),
	png("png", TipoDeMidia.Imagem),
	gif("gif", TipoDeMidia.Imagem),
	
	mp3("mp3", TipoDeMidia.Musica),
	wma("wma", TipoDeMidia.Musica),
	wav("wav", TipoDeMidia.Musica),
	wave("wave", TipoDeMidia.Musica),
	
	mp4("mp4", TipoDeMidia.Video),
	wmv("wmv", TipoDeMidia.Video),
	mkv("mkv", TipoDeMidia.Video),
	
	pasta("", TipoDeMidia.Pasta);
	
	private String extensao;
	private TipoDeMidia tipo;

	private ArquivoTipo(String extensao, TipoDeMidia tipo) {
		this.extensao = extensao;
		this.tipo = tipo;
	}
	
	public static ArquivoTipo getPorNomeDoArquivo (String nome) {
		for (ArquivoTipo tipo : values()) {
			boolean temExtensao = nome.contains(tipo.getExtensao());
			if (temExtensao) {
				return tipo;
			}
		}
		return null;
	}

	public String getExtensao() {
		return extensao;
	}	
	
	public TipoDeMidia getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return getExtensao();
	}
}
