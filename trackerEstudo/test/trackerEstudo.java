package meus.projetos.trackerEstudo.test;

import meus.projetos.trackerEstudo.dominio.registrarEstudo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class trackerEstudo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String tema, comando, escolhaInicial;
        LocalDateTime inicio, fim;
        boolean arquivoexiste = registrarEstudo.verificarArquivo();
        registrarEstudo registrarEstudo = new registrarEstudo();

        if (arquivoexiste) {
            System.out.println("Já foi encontrada um sessão de estudo anterior. Deseja iniciar uma nova ou finalizar a anterior?");
            System.out.println("Digite 'n' para iniciar uma nova sessão");
            System.out.println("Digite 'f' para finalizar a sessão anterior");
            escolhaInicial = scanner.nextLine();
            if (escolhaInicial.equals("f")) {
                fim = LocalDateTime.now();
                System.out.println("Estudo finalizado em: "+ fim.format(formatter));
                System.out.println("#####################################");
                registrarEstudo.registroEstudoFinal(fim);
                registrarEstudo.calculoTempodeEstudo();

            }
            if (escolhaInicial.equals("n")) {
                System.out.println("Digite o tema do estudo de hoje:");
                tema = scanner.nextLine();
                System.out.println("Digite 'i' para iniciar o estudo:");
                comando = scanner.nextLine();
                if (comando.equalsIgnoreCase("i")) {
                    inicio = LocalDateTime.now();
                    System.out.println("Estudo iniciado em: "+ inicio.format(formatter));
                    System.out.println("#####################################");
                    registrarEstudo.registroEstudoInicio(tema,inicio);

            }}


        }
        else {
            System.out.println("Digite o tema do estudo de hoje:");
            tema = scanner.nextLine();
            System.out.println("Digite 'i' para iniciar o estudo:");
            comando = scanner.nextLine();
            if (comando.equalsIgnoreCase("i")) {
                inicio = LocalDateTime.now();
                System.out.println("Estudo iniciado em: "+ inicio.format(formatter));
                System.out.println("#####################################");
                registrarEstudo.registroEstudoInicio(tema,inicio);
        }

        }
    }
}
