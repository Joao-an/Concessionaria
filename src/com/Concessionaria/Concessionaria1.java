/*Trabalho de POO
 * Fundamentos da Programação Orientada a Objetos (11100010550_20212_02)
 * Membros do grupo
 * JOAO ANDRE MARCOS ALEX SANDER BUENO
 * GUILHERME DE ALMEIDA MENEZES
 * ISABELLI CRISTINA PEREIRA DA SILVA
 */
package com.Concessionaria;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Concessionaria1 {

	private ArrayList<Veiculo> veiculo;


	public Concessionaria1( ) {
		this.veiculo = new ArrayList<Veiculo>();
	}

	public void adicionaVeiculo(Veiculo mani) {
		this.veiculo.add(mani);
	}

	public void listarVeiculo() {
		for(Veiculo mani:veiculo) {
			System.out.println(mani.toString());
		}
		System.out.println("Total = " + this.veiculo.size() + " veiculos listados com sucesso!\n");
	}
	
	public void excluirVeiculo(Veiculo mani) {
		if (this.veiculo.contains(mani)) {
			this.veiculo.remove(mani);
			System.out.println("[Veiculo " + mani.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Veiculo inexistente!\n");
	}

	public void excluirVeiculo() {
		veiculo.clear();
		System.out.println("Veiculos excluidos com sucesso!\n");
	}
	public void gravarVeiculo()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\veiculo.dat"));
			for(Veiculo mani:veiculo) {
				outputStream.writeObject(mani);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarVeiculo() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\veiculo.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Moto)
					this.veiculo.add((Moto)obj);
				else if (obj instanceof Carro)
					this.veiculo.add((Carro)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Veiculos recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		Concessionaria1 car  = new Concessionaria1();

		Moto CG125    = new Moto("CG125","Honda", 2003, "125");
		Moto ybr = new Moto("YBR","Yamaha", 2010, "125");
		Carro  astra      = new Carro ("Astra","Chevrolet", 2000, "1.8");
		Carro  gol     = new Carro ("Gol","Volkswagen", 2021,"1.0");
		car.adicionaVeiculo(CG125);
		car.adicionaVeiculo(ybr);
		car.adicionaVeiculo(astra);
		car.adicionaVeiculo(gol);
		car.listarVeiculo();
		car.gravarVeiculo();
		car.excluirVeiculo(CG125);
		car.listarVeiculo();
		car.excluirVeiculo();
		car.listarVeiculo();
		car.recuperarVeiculo();
		car.listarVeiculo();
	}

}
