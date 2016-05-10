package com.supprema.listobjects;

public enum ArquivoTipo {
	
	IMAGEM_JPEG("jpeg"),
	IMAGEM_JPG("jpg"),
	IMAGEM_PNG("png"),
	IMAGEM_GIF("gif"),
	
	MUSICA_MP3("mp3"),
	MUSICA_WMA("wma"),
	MUSICA_WAV("wav"),
	MUSICA_WAVE("wave"),
	
	VIDEO_MP4("mp4"),
	VIDEO_WMV("wmv"),
	VIDEO_MKV("mkv"),
	
	PASTA("");
	
	private String extensao;

	private ArquivoTipo(String extensao) {
		this.extensao = extensao;
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
}
