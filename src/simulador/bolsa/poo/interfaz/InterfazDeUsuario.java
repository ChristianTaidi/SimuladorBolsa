package simulador.bolsa.poo.interfaz;

import simulador.bolsa.poo.excepciones.*;

import java.util.InputMismatchException;

public class InterfazDeUsuario {

    private Simulador sim;
    private EntradaDeDatos ent= new EntradaDeDatos();
    private int option;

    public InterfazDeUsuario(){
        sim = new Simulador();
    };



    public void Ejecutar(){
        do {
            sim.printMenu();
            try{
            option = ent.leerEntero();
            switch (option) {

                case 1:

                    sim.printClients();
                    break;

                case 2:

                    sim.printBolsa();
                    break;

                case 3:
                    try {

                        String nombre;
                        String dni;
                        Float saldo;
                        do{
                            System.out.println("Introduzca los datos del cliente(Formato -> nombre/dni/saldo inicial)");
                            String cadena = ent.leerCadena();
                            if (cadena!=null) {
                                String[] div = cadena.split("/");
                                if (div.length==3) {
                                    nombre = div[0];
                                    dni = div[1];
                                    saldo = Float.parseFloat(div[2]);
                                }else{
                                    throw new StringFormatException("El formato de la cadena es erroneo");
                                }
                            }else{
                                throw new IllegalArgumentException("Cadena vacia");
                            }
                        }while (saldo <0);
                            sim.addCliente(nombre, dni, saldo);
                    }catch(NumberFormatException e){
                        System.out.println("Formato de entrada incorrecto");
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    } catch (StringFormatException e) {
                        System.out.println(e.getMessage());
                    }
                    break;


                case 4:
                    try {
                        System.out.println("Introduzca el dni del cliente a borrar:");
                        String dni = ent.leerCadena();

                            sim.deleteCliente(dni);
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                        break;


                case 5:

                    try {
                        sim.backupSave("banc");
                    }catch (InvalidBackupElementException e){
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

                        break;


                case 6:

                    try {
                        sim.backupLoad("banc");
                    }catch(InvalidBackupElementException e){
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    finally {
                        break;
                    }

                case 7:
                    try {
                        System.out.println("Introduzca el dni del cliente a mejorar:");
                        String dni = ent.leerCadena();

                            sim.mejorarCliente(dni);
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    finally {
                    break;
                }

                case 8:

                    try {
                        System.out.println("Introduzca el dni: ");
                        String dni = ent.leerCadena();
                        System.out.println("Introduzca el número de empresas que desea ver: ");
                        int numEmpresas = ent.leerEntero();
                        System.out.println("Imprimiendo las "+numEmpresas+" mejores empresas...");
                        sim.recomendacion(dni,numEmpresas);
                    }catch(NotPremiumClientException e){
                        System.out.println(e.getMessage());
                    }catch (InexistentClientException e){
                        System.out.println(e.getMessage());
                    }catch(NumberFormatException e){
                        System.out.println("Formato de entrada erroneo");
                    } catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    finally {
                        break;
                    }

                case 9:
                    try {
                        float precio=-1;

                            System.out.print("Introduzca el numero de empresas desea añadir: ");
                            int nEmpresas = ent.leerEntero();
                            while (nEmpresas > 0) {
                                nEmpresas -= 1;
                                System.out.print("Introduzca el nombre de la empresa: ");
                                String nomEmpresa = ent.leerCadena();
                                do {
                                System.out.print("Introduzca el precio inicial de las acciones de la empresa: ");
                                precio = ent.leerFloat();

                                System.out.println();
                                }while (precio<0);
                                sim.addEmpresa(nomEmpresa, precio);
                            }

                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }catch (InputMismatchException e){
                        System.out.println("Formato de entrada incorrecto");
                    }
                    finally {
                    break;
                }
                case 10:
                    sim.printBolsa();
                    System.out.println("Introduzca el nombre de la empresa a borrar:");
                    try {
                        String nomEmpresa = ent.leerCadena();
                        sim.deleteEmpresa(nomEmpresa);
                    }catch(NoSuchEnterpriseException e){
                        System.out.println(e.getMessage());
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                }
                    finally {
                        break;
                    }
                case 11:

                    sim.updateValores();
                    break;

                case 12:

                    try {
                        sim.backupSave("bolsa");
                    }catch(InvalidBackupElementException e){
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    finally {
                        break;
                    }
                case 13:

                    try {
                        sim.backupLoad("bolsa");
                    }catch(InvalidBackupElementException e){
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    finally {
                        break;
                    }
                case 14:
                    try {
                        Float dinero;
                        String empresa;
                        String dni;
                        do {
                            System.out.println("Introduzca los datos necesarios para la compra (dni / dinero a invertir / empresa--El dinero debe ser positivo)");
                            String cadena = ent.leerCadena();
                            String[] partes = cadena.split("/");
                            if(partes.length==3) {
                                dinero = Float.parseFloat(partes[1]);
                                dni = partes[0];
                                empresa = partes[2];
                            }else{
                                throw new StringFormatException("Formato erroneo");
                            }
                        }while(dinero<0);

                            sim.solicitarCompra(dni, dinero,empresa);
                            System.out.println("Solicitud almacenada");
                    }catch(NoSuchEnterpriseException e){
                        System.out.println(e.getMessage());
                    }catch (NotEnoughMoneyException e){
                        System.out.println(e.getMessage());
                    }catch (NotEnoughActionsException e){
                        System.out.println("Fallo interno a la hora de realizar la solicitud, intentelo de nuevo");
                    }catch(InexistentClientException e){
                        System.out.println(e.getMessage());
                    } catch(NumberFormatException e){
                        System.out.println("Formato erroneo");
                    } catch(IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (StringFormatException e) {
                        System.out.println(e.getMessage());
                    } finally{
                        break;
                    }
                case 15:
                    try {
                        int nAcciones;
                        String empresa;
                        String dni;
                        do {
                            System.out.println("Introduzca los datos necesarios para la venta (dni / número de acciones / empresa)");
                            String cadena = ent.leerCadena();
                            String[] partes = cadena.split("/");
                            dni = partes[0];
                            nAcciones = Integer.parseInt(partes[1]);
                            empresa = partes[2];
                        }while(nAcciones <0);

                        sim.solicitarVenta(dni, nAcciones,  empresa);
                        System.out.println("Solicitud almacenada");
                    }catch(NoSuchEnterpriseException e){
                        System.out.println(e.getMessage());
                    }catch (NotEnoughActionsException e){
                        System.out.println(e.getMessage());
                    }catch (NotEnoughMoneyException e){
                        System.out.println("Fallo interno a la hora de realizar la solicitud, intentelo de nuevo");
                    }catch(InexistentClientException e){
                        System.out.println(e.getMessage());
                    }catch(NumberFormatException e){
                        System.out.println("Formato erroneo");
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    finally {
                        break;
                    }

                case 16:

                    sim.solicitarActualizacion();
                    System.out.println("Solicitud almacenada");
                    break;

                case 17:

                    sim.printOperaciones();
                    break;

                case 18:
                    System.out.println("Ejecutando solicitudes...");
                    sim.ejecutarOperaciones();
                    break;

                default:
                    if (option != 0) {
                        System.out.println("Opción no válida: " + option);
                    }

            }
            }catch(InputMismatchException e){
                e.printStackTrace();
                System.out.println("Formato de opción no válido");
                option = 404;
            }
            System.out.println("Pulsa enter para seguir");
            ent.leerCadena();
        }while (option != 0);
    }
}
