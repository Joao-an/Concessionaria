/*Trabalho de POO
 * Fundamentos da Programação Orientada a Objetos (11100010550_20212_02)
 * Membros do grupo
 * JOAO ANDRE MARCOS ALEX SANDER BUENO
 * GUILHERME DE ALMEIDA MENEZES
 * ISABELLI CRISTINA PEREIRA DA SILVA
 */
package com.Concessionaria;

public class Moto extends Veiculo {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "2 Rodas";
	}
	public Moto(String modelo, String marca, int ano,String cilindradas) {
		super(modelo, marca, ano,cilindradas);
		this.tipo = "Moto";
	}
}
