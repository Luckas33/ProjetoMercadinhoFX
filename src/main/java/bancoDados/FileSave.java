package bancoDados;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileWriter;
import java.io.IOException;


public class FileSave {
    public static boolean gravarObjetos(Object obj, String caminho){
        File arquivo = new File(caminho);

        // Verifica se arquivo existe. Se não retorna falso e imprime a pilha de execução
        if(!arquivo.exists()){
            try {
                arquivo.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            // Criando um fluxo de saída, para definir o canal de comunicação com arquivo
            FileOutputStream gravador = new FileOutputStream(arquivo);

            //Criando um fluxo para lançar um obj na memória para depois ir para o arquivo
            ObjectOutputStream conversor = new ObjectOutputStream(gravador);

            // Lançando objeto na memória
            conversor.writeObject(obj);

            // Confirmação da operação
            conversor.flush();
            gravador.flush();

            // Fechando canais de comunicação
            conversor.close();
            conversor.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;    
        }
    }

    public static Object recuperarObjetos(String caminho){
        File arquivo = new File(caminho);

        if(arquivo.length() == 0) {
            System.out.println("O arquivo está vazio para recuperação. Caminho: "+caminho);
            return null;
        }

        try {
            // Criando um fluxo de saída, para definir o canal de comunicação com arquivo
            FileInputStream leitor = new FileInputStream(arquivo);

            //Criando um fluxo para lançar um obj na memória para depois ir para o arquivo
            ObjectInputStream conversor = new ObjectInputStream(leitor);

            Object retorno = conversor.readObject();

            conversor.close();
            leitor.close();

            return retorno;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void apagarObjetos(String caminho){
        File arquivo = new File(caminho);
        if(arquivo.length() > 0) {
            try {
                FileWriter writer = new FileWriter(arquivo);
                writer.write(""); // Escreve uma string vazia no arquivo
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo está vazio. Caminho: "+caminho);
        }
    }
}
