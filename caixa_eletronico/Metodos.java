import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Metodos {
    public Banco[] carregaNotas(Banco[] b){
        for (int i = 0; i < b.length; i++){
            b[i].qtd_cedulas = 100;
        }
        b[0].cedulas = 2; b[1].cedulas = 5; b[2].cedulas = 10; b[3].cedulas = 20;
        b[4].cedulas = 50; b[5].cedulas = 100; b[6].cedulas = 200;

        return b;
    }

    public void retirarNotas(Banco[] b){
        int vl_retirada = 0;
        int vl_aux = 0;
        boolean saque_realizado = false;
        int verif = 0;

        int total_caixa = 0;

        for (int i = 0; i < b.length; i++) {
            total_caixa = total_caixa + (b[i].qtd_cedulas * b[i].cedulas);
        }


        while(!saque_realizado){
            vl_retirada = Integer.parseInt(JOptionPane.showInputDialog("Insira o valor a retirar\n\nTotal do caixa: " + total_caixa));
            vl_aux = vl_retirada;

            if(vl_retirada <= total_caixa) {
                if (vl_retirada > 0 && vl_retirada != 3) {
                    if (vl_retirada >= 2 && vl_retirada <= 10000) {

                        for (int i = b.length - 1; i >= 0; i--) {

                            while (vl_aux >= b[i].cedulas && b[i].qtd_cedulas > 0) {
                                verif = vl_aux - b[i].cedulas;

                                if(i != 0) {
                                    if (vl_aux == 6 || vl_aux == 8) {
                                        while (vl_aux > 0) {
                                            vl_aux = vl_aux - b[i - 1].cedulas;
                                            b[i - 1].qtd_cedulas--;
                                            b[i - 1].retiradas++;
                                        }
                                    }
                                    else if (verif == 1 || verif == 3) {
                                        vl_aux = vl_aux - b[i - 1].cedulas;
                                        b[i - 1].qtd_cedulas--;
                                        b[i - 1].retiradas++;
                                    }
                                    else {
                                        vl_aux = vl_aux - b[i].cedulas;
                                        b[i].qtd_cedulas--;
                                        b[i].retiradas++;
                                    }
                                }
                                else {
                                    vl_aux = vl_aux - b[i].cedulas;
                                    b[i].qtd_cedulas--;
                                    b[i].retiradas++;
                                }
                            }

                        }

                        saque_realizado = true;

                    }
                    else {
                        JOptionPane.showMessageDialog(null, "VALOR SOLICITADO INVÁLIDO");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "VALOR SOLICITADO INVÁLIDO");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "EXCEDEU O LIMITE DO CAIXA");
            }
        }

        exibe(b, vl_retirada, vl_aux);
    }

    public void stats(int total_saques, int retiradas, int valor_final) throws IOException {
        BufferedWriter gravar = new BufferedWriter(new FileWriter("Resumo.txt"));

        double media = (double) total_saques / retiradas;
        int valor_inicial = total_saques + valor_final;

        gravar.write("Valor Inicial do Caixa"); gravar.newLine();
        gravar.write(Integer.toString(valor_inicial)); gravar.newLine();

        gravar.write("Media dos Saques"); gravar.newLine();
        gravar.write(Double.toString(media)); gravar.newLine();

        gravar.write("Valor Total dos Saques"); gravar.newLine();
        gravar.write(Integer.toString(total_saques)); gravar.newLine();

        gravar.write("Quantidade de Saques"); gravar.newLine();
        gravar.write(Integer.toString(retiradas)); gravar.newLine();

        gravar.write("Valor Final do Caixa"); gravar.newLine();
        gravar.write(Integer.toString(valor_final)); gravar.newLine();

        gravar.close();

    }

    public void exibe(Banco[] b, int retirada, int aux){
        System.out.println("Valor: " + retirada+"\n");
        System.out.println("Qtd. | Valor | Retiradas");

        for (int i = 0; i < b.length; i++){
            System.out.println(b[i].qtd_cedulas+"  :  "+b[i].cedulas+"         "+b[i].retiradas);
        }
        //System.out.println("Resto: " + aux);
        System.out.println("");
    }
}
