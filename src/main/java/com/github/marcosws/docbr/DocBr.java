package com.github.marcosws.docbr;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class DocBr {
	
	public static String generateCpf(){
		
		String numberCpf = generateNumbers(9);
		int[] multiplierWeight1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] multiplierWeight2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
		int totalSum = 0;
		int restOfDivision = 0;

		for(int i = 0; i < numberCpf.length(); i++)
			 totalSum += Integer.parseInt(String.valueOf(numberCpf.charAt(i))) * multiplierWeight1[i];
		do{
			restOfDivision = totalSum % 11;
			if(restOfDivision < 2)
				numberCpf = numberCpf.concat("0");
			else
				numberCpf = numberCpf.concat(String.valueOf((11 - restOfDivision)));
			totalSum = 0;
			if(numberCpf.length() == 11) break;
			for(int i = 0; i < numberCpf.length(); i++)
				 totalSum += Integer.parseInt(String.valueOf(numberCpf.charAt(i))) * multiplierWeight2[i];
		}
		while(true);
		
		return numberCpf;
	}

	public static String generateCnpj(){
		
		String numberCnpj = generateNumbers(8).concat("0001");
		int[] multiplierWeight1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] multiplierWeight2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int totalSum = 0;
		int restOfDivision = 0;
		
		for(int i = 0; i < numberCnpj.length(); i++)
			 totalSum += Integer.parseInt(String.valueOf(numberCnpj.charAt(i))) * multiplierWeight1[i];
		
		do{
			restOfDivision = totalSum % 11;
			if(restOfDivision < 2)
				numberCnpj = numberCnpj.concat("0");
			else
				numberCnpj = numberCnpj.concat(String.valueOf((11 - restOfDivision)));
			totalSum = 0;
			if(numberCnpj.length() == 14) break;
			for(int i = 0; i < numberCnpj.length(); i++)
				 totalSum += Integer.parseInt(String.valueOf(numberCnpj.charAt(i))) * multiplierWeight2[i];
		}
		while(true);
		
		return numberCnpj;
	}
	
	
	public static boolean isCpf(String cpf) {
		
		cpf = cpf.replaceAll("[^0-9]", "");
		if(cpf.length() != 11)
			return false;
		
		int[] multiplierWeight1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] multiplierWeight2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
		int totalSum = 0;
		int restOfDivision = 0;
		String cpfWithoutDigit = cpf.substring(0, 9);
		
		for(int i = 0; i < 9; i++)
			totalSum += Integer.parseInt(String.valueOf(cpfWithoutDigit.charAt(i))) * multiplierWeight1[i];
			
		do {
			restOfDivision = totalSum % 11;
			if(restOfDivision < 2)
				cpfWithoutDigit += "0";
			else
				cpfWithoutDigit += String.valueOf((11 - restOfDivision));
			totalSum = 0;
			if(cpfWithoutDigit.length() >= 11) break;
			for(int i = 0; i < 10; i++)
				totalSum += Integer.parseInt(String.valueOf(cpfWithoutDigit.charAt(i))) * multiplierWeight2[i];
		}
		while(true);
		
		if(cpfWithoutDigit.substring(9).equals(cpf.substring(9)))
			return true;
		
		return false;
	}

	public static boolean isCnpj(String cnpj) {
		
		cnpj = cnpj.replaceAll("[^0-9]", "");
		if(cnpj.length() != 14)
			return false;
		
		int[] multiplierWeight1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] multiplierWeight2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int totalSum = 0;
		int restOfDivision = 0;
		String cnpjWithoutDigit = cnpj.substring(0, 12);
		
		for(int i = 0; i < 12; i++)
			totalSum += Integer.parseInt(String.valueOf(cnpjWithoutDigit.charAt(i))) * multiplierWeight1[i];
		
		do {
			restOfDivision = totalSum % 11;
			if(restOfDivision < 2)
				cnpjWithoutDigit += "0";
			else
				cnpjWithoutDigit += String.valueOf((11 - restOfDivision));
			totalSum = 0;
			if(cnpjWithoutDigit.length() >= 14) break;
			for(int i = 0; i < 13; i++)
				totalSum += Integer.parseInt(String.valueOf(cnpjWithoutDigit.charAt(i))) * multiplierWeight2[i];
		}
		while(true);
		
		if(cnpjWithoutDigit.substring(12).equals(cnpj.substring(12)))
			return true;
		
		return false;
	}
	
	public static String maskCpfCnpj(String number){
		
		try{
			String format = "";
			if(number.length() == 11)
				format = "AAA.AAA.AAA-AA";
			else if(number.length() == 14)
				format = "AA.AAA.AAA/AAAA-AA";
			else
				return number;
			MaskFormatter maskFormatter = new MaskFormatter(format);
			maskFormatter.setValueContainsLiteralCharacters(false);
			return maskFormatter.valueToString(number);
		}
		catch(ParseException e){}
		return number;

	}
	
	private static String generateNumbers(int numberOfDigits) {
		String number = "";
		for(int i = 0; i < numberOfDigits; i++)
			number += String.valueOf(Math.round(Math.random() * 9));
		return number;
	}

	

}
