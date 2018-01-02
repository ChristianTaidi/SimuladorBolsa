package simulador.bolsa.poo.modelo.banco.personas.clientes;

import simulador.bolsa.poo.excepciones.NoSuchEnterpriseException;
import simulador.bolsa.poo.interfaces.Imprimible;
import simulador.bolsa.poo.modelo.banco.personas.Persona;

import java.io.Serializable;
import java.util.TreeMap;

/**
 *
 */
public class Cliente extends Persona implements Serializable,Imprimible{

    private float saldo;
    private TreeMap<String,PaqueteAcciones> paquetesDeAcciones;

    /**
     *
     * @param nomb
     * @param dni
     * @param saldo
     */

    public Cliente(String nomb, String dni, float saldo){
        if((nomb!=null)&&(dni!=null)) {
            this.setDni(dni);
            this.setNombre(nomb);
            this.saldo = saldo;
            this.paquetesDeAcciones = new TreeMap<>();
        }else{
            throw new IllegalArgumentException("Nombre o Dni nulos");
        }
    }

    public Cliente(Cliente c){
        this.setDni(c.getDni());
        this.setNombre(c.getNombre());
        this.setSaldo(c.getSaldo());
    }

    public int tieneAcciones(String emp) throws NoSuchEnterpriseException{
        if (paquetesDeAcciones.containsKey(emp)){
            return paquetesDeAcciones.get(emp).getnAcciones();
        }else{
            throw new NoSuchEnterpriseException("El cliente no tiene acciones de esa empresa");
        }
    }

    public void setSaldo(float s){
        this.saldo=s;
    }

    public float getSaldo(){
        return this.saldo;
    }

    public void addStockPackage(int numbStock,String enterpriseName,float stockValue){
        if (numbStock>0) {
            this.paquetesDeAcciones.put(enterpriseName, new PaqueteAcciones(numbStock, enterpriseName, stockValue));
        }else{
            this.paquetesDeAcciones.remove(enterpriseName);
        }
    }

    public void updanteStockPackage(String nomEmpresa,float precioAcciones ){

            this.paquetesDeAcciones.get(nomEmpresa).actualizarValor(precioAcciones);
    }

    public boolean estaEmpresa(String NomEmpresa){
        return (paquetesDeAcciones.get(NomEmpresa)!=null);
    }


    public void imprimir() {
        System.out.println("Cliente: "+this.getNombre()+" con DNI: "+this.getDni()+" y saldo de: "+this.getSaldo()+"€");
        for (PaqueteAcciones paq: paquetesDeAcciones.values()){
            paq.imprimir();
        }
    }

    public void actualizarSaldo(float saldoRestante) {
        this.setSaldo(saldoRestante);
    }
}
