package com.supprema.listobjects;

import java.text.DecimalFormat;

public enum MetricasBits {

	BYTE		("B", 1),
	KILOBYTE	("KB", 1024^1),
	MEGABYTE	("MB", 1024^2),
	GIGABYTE	("GB", 1024^3),
	TERABYTE	("TB", 1024^4),
	PETABYTE	("PB", 1024^5),
	EXABYTE		("EB", 1024^6),
	ZETTABYTE	("ZB", 1024^7),
	YOTTABYTE	("YB", 1024^8);
	
	private long tamanhoEmBits;
	private String sigla;

	private MetricasBits(String sigla, long tamanhoEmBits) {
		this.tamanhoEmBits = tamanhoEmBits;
		this.sigla = sigla;
	}

	public long getTamanhoEmBits() {
		return tamanhoEmBits;
	}

	public static MetricasBits getPorTamanho (long tamanho) {
		MetricasBits metrica = BYTE;
		long unit = metrica.getTamanhoEmBits();
		if (tamanho < unit) return metrica;
		int exp = (int) (Math.log10(tamanho) / Math.log10(1024));
		return values()[exp];
	}
	
	public static String getTamanhoEmFormatoLegivel (long tamanho) {
		MetricasBits metrica = getPorTamanho(tamanho);
		if (tamanho <= 0) return "0 " + metrica.getSigla();
		int exp = (int) (Math.log10(tamanho)/Math.log10(1024));
		String pre = metrica.getSigla(); //("KMGTPE").charAt(exp-1) + ("i");
		return new DecimalFormat("#,##0.#").format(tamanho/Math.pow(1024, exp)) + " " + pre;
	}

	public String getSigla() {
		return sigla;
	}
}
