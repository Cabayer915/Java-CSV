package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void gravaArquivoCSV (List<Cachorro> listaDog, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
            System.exit(1);
        }

        try {
            for (Cachorro dog : listaDog) {
                saida.format("%d;%s;%s;%.2f\n", dog.getId(),
                dog.getNome(), dog.getPorte(), dog.getPeso());
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leExibeArquivoCSV (String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            erro.printStackTrace();
            System.exit(1);
        }

        try {
            System.out.printf("%4s %-15s %-9s %4s\n", "ID", "NOME", "PORTE", "PESO");
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();
                String porte = entrada.next();
                Double peso = entrada.nextDouble();
                System.out.printf("%4s %-15s %-9s %4s\n", id, nome, porte, peso);
            }
        }
        catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            erro.printStackTrace();
            deuRuim = true;
        }
        catch (IllegalStateException erro) {
            System.out.println("Arquivo com problemas");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            entrada.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        List<Cachorro> listaDog = new ArrayList<>();

        listaDog.add(new Cachorro(1,"Thiago", "Pequeno", 3.0));
        listaDog.add(new Cachorro(2,"Bob", "Medio", 5.0));
        listaDog.add(new Cachorro(3,"Bruce", "Grande", 22.0));
        listaDog.add(new Cachorro(4,"Teddy", "Pequeno", 4.0));
        listaDog.add(new Cachorro(5,"Cristal", "Pequeno", 7.0));
        listaDog.add(new Cachorro(6,"Tico", "Pequeno", 2.5));

        for (Cachorro dog : listaDog) {
            System.out.println(dog);
        }

        gravaArquivoCSV(listaDog, "dogs");

        leExibeArquivoCSV("dogs");
    }
}