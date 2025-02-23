import javax.swing.*;
import java.io.IOException;

public class
Main {
    public static void main(String[] args) throws IOException {
        //references
        Metodos m = new Metodos();
        Banco[] banco = new Banco[7];

        for (int i = 0; i < banco.length; i++){
            banco[i] = new Banco();
        }
        //

        int select = 0;

        int retiradas = 0;

        while(select != 9){
            select = Integer.parseInt(JOptionPane.showInputDialog("Menu Principal\n" +
                    "1 – Carregar Notas\n" +
                    "2 – Retirar Notas\n" +
                    "3 – Estatística\n" +
                    "9 – Fim\n"));

            switch (select){
                case 1:
                    banco = m.carregaNotas(banco);
                    break;
                case 2:
                    if (retiradas < 100) {
                        m.retirarNotas(banco);
                        retiradas++;
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "NUMERO MAXIMO DE SAQUES EXCEDIDO");
                    }
                    break;
            }
        }
    }
}
