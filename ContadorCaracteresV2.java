package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class ContadorCaracteresV2 {

    public static LinkedList<Caractere> caracteres;
    private static long  inicioTempo;
    private static long  fimTempo;

    public static LinkedList<Caractere> contarCaracteres(String txt){
        //Instantiate the list.
        LinkedList<Caractere> chars = new LinkedList<>();

        //List of chars, could use char array but im bored.
        ArrayList<Character> jaAdicionados = new ArrayList<>();

        //Reads EACH char on the string...
        for(int i=0; i<txt.length(); i++){

            //Stores charAt string on a variable.
            char c = txt.charAt(i);

            //Instance of a new object of 'Caractere'
            Caractere letra = new Caractere(c,1);

            //Checks if the char is a repeat or a new one by referencing the char List;
            if(jaAdicionados.contains(c)){
                System.out.println("\nCaractere '"+c+"' JA EXISTE na lista.");

                //Maps the current index of on the 'Caractere' List that contains that char.
                int indexOfCurrentLetra = chars.indexOf(new Caractere(c));
                letra = chars.get(indexOfCurrentLetra);
                System.out.println("Letra encontrada: "+letra);

                //Updates the current object found of 'Caractere' on the List.
                letra.setOcorrencias(letra.getOcorrencias()+1);
                System.out.println("Letra atualizada: "+ letra);
                chars.set(indexOfCurrentLetra, letra);

            }else{
                System.out.println("\nCaractere '"+c+"' NAO existe na lista.");

                //Adds the new character to the 'Caracteres' List and the repeat List.
                chars.add(letra);
                jaAdicionados.add(c);

                System.out.println("Adicionei "+letra.toString()+" para lista...");
            }
        }

        //Return list with the detected chars.
        return chars;
    }

    public static void main(String[] args) {
        //BugFix: Fixes the charset to read special character correctly.
        System.setProperty("file.encoding", "UTF-8");

        //Greets the user and asks for the specific string of text.
        System.out.println("Contador caracteres V2");
        String texto = JOptionPane.showInputDialog("Digite a string de texto:");

        if(texto.isEmpty()){
            System.out.println("Texto vazio.");
            return;
        }

        //Start the clock and start processing the given String.
        inicioTempo = System.currentTimeMillis();
        caracteres = contarCaracteres(texto);

        //Orders the list by the numbers of characters by ascending order and then reversing.
        Collections.sort(caracteres, Comparator.comparing(Caractere::getOcorrencias));
        Collections.reverse(caracteres);

        //Stops the clock and show results, as well of the List of characters mapped.
        fimTempo = System.currentTimeMillis();
        JOptionPane.showMessageDialog(null, "Tempo de processamento da lista:\n"+(fimTempo - inicioTempo) / 1000F+" segundos");
        System.out.println("\n\n----------Lista de Caracteres----------");
        System.out.println("Texto: "+texto+"\n");
        for(Caractere c : caracteres){
            System.out.println(c.toString());
        }
        System.out.println("---------------------------------------\n\n");
    }
}
