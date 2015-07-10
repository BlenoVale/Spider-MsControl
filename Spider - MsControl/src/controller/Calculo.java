package controller;

import java.util.List;
import java.util.Objects;
import model.Coleta;
import model.Procedimentodecoleta;

/**
 *
 * @author BlenoVale
 */
public class Calculo {

    public double media(List<Coleta> lista) {
        double soma = 0;
        for (int i = 0; i < lista.size(); i++) {
            soma += lista.get(i).getValorDaColeta();
        }
        return soma / lista.size();
    }

    public double mediana(List<Coleta> lista) {
        int posicao = 0;
        if (lista.size() % 2 == 0) {
            posicao = lista.size() / 2;
        } else {
            posicao = (lista.size() + 1) / 2;
        }
        posicao--;
        return lista.get(posicao).getValorDaColeta();
    }

    public double moda(List<Coleta> lista) {
        int numVezes = 0;
        int compara = 0;
        double moda = 0;
        for (int i = 0; i < lista.size(); i++) {
            numVezes = 1;
            for (int j = i + 1; j < lista.size(); j++) {
                if (Objects.equals(lista.get(i).getValorDaColeta(), lista.get(j).getValorDaColeta())) {
                    numVezes++;
                }
            }
            if (numVezes > compara) {
                moda = lista.get(i).getValorDaColeta();
                compara = numVezes;
            } else if (numVezes == compara) {
                if (lista.get(i).getValorDaColeta() > moda) {
                    moda = lista.get(i).getValorDaColeta();
                }
            }
        }
        return moda;
    }

    public double soma(List<Coleta> lista) {
        double soma = 0;
        for (int i = 0; i < lista.size(); i++) {
            soma += lista.get(i).getValorDaColeta();
        }
        return soma;
    }

    public boolean porcentagemMinimaAtingida(Procedimentodecoleta procedimentodecoleta) {
        double qtdColetados = procedimentodecoleta.getContadorColeta();
        double total = procedimentodecoleta.getFrequencia();
        double percentual = qtdColetados / total;
        System.out.println(">>>>>>>persentual: " + percentual);

        if (percentual >= procedimentodecoleta.getPorcentagem()) {
            return true;
        } else {
            return false;
        }
    }
}
