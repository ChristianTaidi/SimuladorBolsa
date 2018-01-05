package simulador.bolsa.poo.funcionalidades.comparadores;

import simulador.bolsa.poo.modelo.bolsa.Empresa;

import java.util.Comparator;

public class ComparadorEmpresas implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Empresa empresa1 = (Empresa)o1;
        Empresa empresa2 = (Empresa)o2;
        if (empresa1.getRentabilidad() <empresa2.getRentabilidad()){
            return -1;
        }if(empresa1.getRentabilidad() > empresa2.getRentabilidad()){
            return 1;
        }else{
            return 0;
        }
    }
}
