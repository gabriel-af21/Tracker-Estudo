package meus.projetos.trackerEstudo.dominio;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class registrarEstudo {
    public static void registroEstudoInicio(String tema, LocalDateTime inicio) {
        try(FileWriter writer = new FileWriter("registro_estudos.txt",true)){
            writer.write("Tema: " + tema + "\n");
            writer.write("Início: " + inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
            System.out.println("Registro de início salvo com sucesso!");
        } catch (IOException e) {
         System.out.println("Erro ao gravar registro estudo: "+ e.getMessage());
        }

    }
        public static void registroEstudoFinal(LocalDateTime finalizacao) {
            try(FileWriter writer = new FileWriter("registro_estudos.txt",true)){
                writer.write("Fim: " + finalizacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");

                System.out.println("Registro de fim salvo com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao gravar registro estudo: " + e.getMessage());
            }}
        public static void calculoTempodeEstudo(){
            // Variáveis para armazenar as datas de início e fim
            LocalDateTime dataInicio = null;
            LocalDateTime dataFim = null;
            String ultimaLinhaFim = null;
            String ultimaLinhaInicio = null;
            // Formato da data/hora
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            try (BufferedReader br = new BufferedReader(new FileReader("registro_estudos.txt"))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    if (linha.startsWith("Início:")) {
                        ultimaLinhaInicio = linha; // Pega a última linha que se começa com Inicio.
                    } else if (linha.startsWith("Fim:")) {
                        ultimaLinhaFim = linha; // Pega a última linha que se começa com Fim.
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Verifica se as datas foram lidas corretamente
            if (ultimaLinhaInicio != null && ultimaLinhaFim != null) {
                LocalDateTime inicio = LocalDateTime.parse(ultimaLinhaInicio.substring(8), formato);
                LocalDateTime fim = LocalDateTime.parse(ultimaLinhaFim.substring(5), formato);
                // Calcula a diferença entre início e fim
                Duration duracao = Duration.between(inicio,fim);

                // Converte a duração para horas, minutos e segundos
                long horas = duracao.toHours();
                long minutos = duracao.toMinutes() % 60;
                long segundos = duracao.getSeconds() % 60;

                // Exibe o tempo total de estudo
                System.out.println("Tempo total de estudo: " + horas + " horas, " + minutos + " minutos e " + segundos + " segundos.");
                try(FileWriter writer = new FileWriter("registro_estudos.txt",true)) {
                    writer.write("Tempo total de estudo: " + horas + " horas, " + minutos + " minutos e " + segundos + " segundos." + "\n");
                    writer.write("------------------------------------------\n");
                    System.out.println("Registro de fim salvo com sucesso!");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else {
                System.out.println("Não foi possível calcular o tempo. Verifique o formato do arquivo.");
            }



        }

    public static boolean verificarArquivo(){
        // Obtém o diretório atual onde o programa está rodando
            String diretorioAtual = System.getProperty("user.dir");

        // Concatena o diretório atual com o nome do arquivo
            String caminhoDoArquivo = diretorioAtual + File.separator + "registro_estudos.txt";

        // Cria um objeto File para o arquivo
            File arquivo = new File(caminhoDoArquivo);

        // Retorna true se o arquivo existe, false se não existe
        return arquivo.exists();
    }}






