package simulador.bolsa.poo.modelo.bolsa;

import simulador.bolsa.poo.interfaces.Imprimible;

import java.io.Serializable;

public class Empresa implements Imprimible,Serializable {
    private String nomEmpresa;
    private int numAcciones;
    private float precioAcciones;
    private float precioAntiguoAcc;


    public Empresa (String nomEmpresa, float precioAcciones)throws IllegalArgumentException{
        if (nomEmpresa != null) {
            this.setNomEmpresa(nomEmpresa);
            this.setNumAcciones(0);
            this.setPrecioAcciones(precioAcciones);
        }else{
            throw new IllegalArgumentException("El nombre de la empresa no puede ser nulo");
        }
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getNomEmpresa (){
        return nomEmpresa;
    }

    public void setNumAcciones(int numAcciones) {
        this.numAcciones = numAcciones;
    }

    public int getNumAcciones(){
        return numAcciones;
    }

    public void setPrecioAcciones(float precioAcciones) {
        this.precioAcciones = precioAcciones;
    }

    public float getPrecioAcciones(){
        return precioAcciones;
    }

    public void setPrecioAntiguoAcc(float precioAntiguoAcc) {
        this.precioAntiguoAcc = precioAntiguoAcc;
    }

    public float getPrecioAntiguoAcc (){
        return precioAntiguoAcc;
    }

    public float getIncrementoNum(){
        return this.getPrecioAcciones()-this.getPrecioAntiguoAcc();
    }

    public float getIncrementoPorcentaje() {
        if(this.getPrecioAntiguoAcc()>0) {
            return ((this.getPrecioAcciones() / this.getPrecioAntiguoAcc()) * 100);
        }else{
            return 0;
        }
    }

    public void actualizarValor (int numAcc){
       this.setPrecioAntiguoAcc(this.getPrecioAcciones());
       this.setPrecioAcciones((float) ((numAcc*0.1)+this.getPrecioAcciones()));

    }

    public void imprimir (){
        System.out.println("Datos de la empresa: ");
        System.out.println();
        System.out.println("Nombre de la empresa: " + nomEmpresa);
        System.out.println("Acciones en mercado: " + numAcciones);
        System.out.println("Valor actual de las acciones: " + precioAcciones);
        System.out.println("Valor antiguo de las acciones: " + precioAntiguoAcc);
        if (precioAcciones > precioAntiguoAcc){
            System.out.println("↑ " + this.getIncrementoNum() + " / " + this.getIncrementoPorcentaje()+"%");
        }
        else {
            System.out.println("↓ " + this.getIncrementoNum() + " / " + this.getIncrementoPorcentaje()+"%");
        }

    }
}
