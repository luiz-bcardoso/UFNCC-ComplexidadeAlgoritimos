/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;

/**
 *
 * @author laboratorio
 */
public class JAppSomaListas {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<Integer> listaSomasA;
    private static ArrayList<Integer> listaSomasB;
    private static ArrayList<Integer> listaResultado;
    
    private static long  inicioTempo;
    private static long  fimTempo;
    private static float diferencaTempo;
    
    private static MemoryUsage memoriaAlocada;
    
    public static void main(String[] args) {
        
        init();
        
        listaSomasA = populaListas(listaSomasA, "ASC", 100);
        listaSomasB = populaListas(listaSomasB, "DESC", 100);
        
        System.out.println(listaSomasA.toString());
        System.out.println(listaSomasB.toString());
        
        multiplicaListaPorSoma(listaSomasA, listaSomasB);
        
        System.out.println("\n\n\n");
        System.out.println("Feito multiplicacao por SOMA das duas listas.");
        System.out.println("Tempo empregeado  :  "+diferencaTempo);
        System.out.println("Memoria Utilizada :  "+memoriaAlocada);
        System.out.println(listaResultado.toString());
        
        listaResultado.clear();
        diferencaTempo = 0;
        
        multiplicaListaPorMult(listaSomasA, listaSomasB);
        
        System.out.println("\n\n\n");
        System.out.println("Feito multiplicacao por OPERACAO das duas listas.");
        System.out.println("Tempo empregeado  :  "+diferencaTempo);
        System.out.println("Memoria Utilizada :  "+memoriaAlocada);
        System.out.println(listaResultado.toString());
        
        listaResultado.clear();
        diferencaTempo = 0;
    }
    
    private static void init(){
        listaResultado = new ArrayList<>();
        listaSomasA = new ArrayList<>();
        listaSomasB = new ArrayList<>();
    }
    
    private static ArrayList populaListas(ArrayList<Integer> lista, String opt, int qtd){
        switch (opt) {
            case "ASC" -> {
                for(int i = 0; i <= qtd; i++){
                    lista.add(i);
                }
            }
            case "DESC" -> {
                for (int i=qtd; i>0; i--){
                    lista.add(i);
                }
            }
            default -> throw new AssertionError();
        }
        return lista;
    }
    
    private static void multiplicaListaPorSoma(ArrayList<Integer> listaA, ArrayList<Integer> listaB){
        inicioTempo = System.currentTimeMillis();
        
        for(int i = 0; i <= listaB.size()-1; i++){
            int resultado = 0;
            for(int j = 0; j<= listaB.get(i)-1; j++){
                resultado += listaA.get(i)+listaB.get(i);
            }
            listaResultado.add(resultado);
        }
        
        fimTempo = System.currentTimeMillis();
        diferencaTempo = (fimTempo - inicioTempo) / 1000F;
        
        memoriaAlocada = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        memoriaAlocada.getUsed();
    }
    
    private static void multiplicaListaPorMult(ArrayList<Integer> listaA, ArrayList<Integer> listaB){
        inicioTempo = System.currentTimeMillis();
        for(int i = 0; i <= listaB.size()-1; i++){
            listaResultado.add( listaA.get(i) * listaB.get(i) );
        }
        
        fimTempo = System.currentTimeMillis();
        diferencaTempo = (fimTempo - inicioTempo) / 1000F;
        
        memoriaAlocada = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        memoriaAlocada.getUsed();
    }
}
