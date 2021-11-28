/*Trabalho de POO
 * Fundamentos da Programação Orientada a Objetos (11100010550_20212_02)
 * Membros do grupo
 * JOAO ANDRE MARCOS ALEX SANDER BUENO
 * GUILHERME DE ALMEIDA MENEZES
 * ISABELLI CRISTINA PEREIRA DA SILVA
*/
package com.Concessionaria;

import java.io.Serializable;

public abstract class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String cilindradas;
	private   String modelo;
	private   String marca;
	private   int ano;
	protected String tipo;
	
	public Veiculo(String marca,String modelo,int ano,String cilindradas ) {
		this.marca = modelo;
		this.modelo = marca;
		this.ano = ano;
		this.cilindradas = cilindradas;
	}
	public String toString() {
		String retorno = "";
		retorno += "Modelo: "     + this.modelo     + "\n";
		retorno += "Marca: "    + this.marca    + "\n";
		retorno += "Ano: "     + this.ano     + "\n";
		retorno += "Tipo: "  + this.tipo  + "\n";
		retorno += "Cilindradas/Potencia" + this.cilindradas +"\n";
		retorno += "Caracteristica: "  + soar()        + "\n";
		return retorno;
	}
	public abstract String soar();
}
