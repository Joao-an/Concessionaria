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

import javax.swing.JOptionPane;


public class Concessionaria {
	private ArrayList<Veiculo> veiculo;

	public Concessionaria() {
		this.veiculo = new ArrayList<Veiculo>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Moto leMoto (){

		String [] valores = new String [3];
		String [] nomeVal = {"Modelo", "Marca", "Ano","Cilindradas/Potencia"};
		valores = leValores (nomeVal);

		int ano = this.retornaInteiro(valores[2]);

		Moto moto = new Moto (valores[0],valores[1],ano,valores[3]);
		return moto;
	}

	public Carro leCarro (){

		String [] valores = new String [3];
		String [] nomeVal = {"Modelo", "Marca","Ano","Cilindradas/Potencia"};
		valores = leValores (nomeVal);

		int ano = this.retornaInteiro(valores[2]);

		Carro carro = new Carro (valores[0],valores[1],ano,valores[3]);
		return carro;
	}

	public Utilitario leUtilitario (){

		String [] valores = new String [3];
		String [] nomeVal = {"Modelo", "Marca","Ano","Cilindradas/Potencia"};
		valores = leValores (nomeVal);

		int ano = this.retornaInteiro(valores[2]);

		Utilitario utilitario = new Utilitario (valores[0],valores[1],ano,valores[3]);
		return utilitario;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto n�o for poss�vel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaVeiculo (ArrayList<Veiculo> veiculo){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\concessionaria.dados"));
			for (int i=0; i < veiculo.size(); i++)
				outputStream.writeObject(veiculo.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Veiculo> recuperaVeiculo (){
		ArrayList<Veiculo> veiculoTemp = new ArrayList<Veiculo>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\concessionaria.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Veiculo) {
					veiculoTemp.add((Veiculo) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com veiculos NãO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return veiculoTemp;
		}
	}

	public void menuConcessionaria (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Concessionaria\n" +
					"Opções:\n" +
					"1. Entrar Veiculo\n" +
					"2. Exibir Veiculo\n" +
					"3. Limpar Veiculo\n" +
					"4. Gravar Veiculo\n" +
					"5. Recuperar Veiculo\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Veiculo\n" +
						"Opções:\n" +
						"1. Carro\n" +
						"2. Moto\n" +
						"3. Utilitario\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: veiculo.add((Veiculo)leCarro());
				break;
				case 2: veiculo.add((Veiculo)leMoto());
				break;
				case 3: veiculo.add((Veiculo)leUtilitario());
				default: 
					JOptionPane.showMessageDialog(null,"Veiculo para entrada NãO escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (veiculo.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com veiculo primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < veiculo.size(); i++)	{
					dados += veiculo.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (veiculo.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com veiculo primeiramente");
					break;
				}
				veiculo.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (veiculo.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com veiculo primeiramente");
					break;
				}
				salvaVeiculo(veiculo);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				veiculo = recuperaVeiculo();
				if (veiculo.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Concessionaria");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Concessionaria car = new Concessionaria ();
		car.menuConcessionaria();

	}

}
