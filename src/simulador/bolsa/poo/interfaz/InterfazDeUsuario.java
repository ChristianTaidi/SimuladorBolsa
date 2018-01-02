package simulador.bolsa.poo.interfaz;

import simulador.bolsa.poo.excepciones.*;

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
                        System.out.println("Introduzca los datos del cliente(Formato -> nombre/dni/saldo inicial)");
                        String cadena = ent.leerCadena();
                        if (cadena!=null) {
                            String[] div = cadena.split("/");
                            if (div.length==3) {
                                String nombre = div[0];
                                String dni = div[1];
                                Float saldo = Float.parseFloat(div[2]);
                                sim.addCliente(nombre, dni, saldo);
                            }else{
                                throw new StringFormatException("El formato de la cadena es erroneo");
                            }
                        }else{
                            throw new IllegalArgumentException("Cadena vacia");
                        }
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
                    System.out.println("Introduzca el dni: ");
                    String dni = ent.leerCadena();
                    try {
                        sim.recomendacion(dni);
                    }catch(NotPremiumClientException e){
                        System.out.println(e.getMessage());
                    }catch (InexistentClientException e){
                        System.out.println(e.getMessage());
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    finally {
                        break;
                    }

                case 9:
                    try {
                        System.out.print("Introduzca el numero de empresas desea añadir: ");
                        int nEmpresas = ent.leerEntero();
                        while (nEmpresas > 0) {
                            nEmpresas -= 1;
                            System.out.print("Introduzca el nombre de la empresa: ");
                            String nomEmpresa = ent.leerCadena();
                            System.out.print("Introduzca el precio inicial de las acciones de la empresa: ");
                            float precio = ent.leerFloat();
                            sim.addEmpresa(nomEmpresa, precio);
                            System.out.println();
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
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
                    System.out.println("Introduzca los datos necesarios para la compra (dni / dinero a invertir / empresa)");
                    String cadena=ent.leerCadena();
                    String[] partes = cadena.split("/");
                    try {
                        sim.solicitarCompra( partes[0], Float.parseFloat(partes[1]), partes[2]);
                    }catch(NoSuchEnterpriseException e){
                        System.out.println(e.getMessage());
                    }catch (NotEnoughMoneyException e){
                        System.out.println(e.getMessage());
                    }catch (NotEnoughActionsException e){
                        System.out.println("Fallo interno a la hora de realizar la solicitud, intentelo de nuevo");
                    }catch(InexistentClientException e){
                        System.out.println(e.getMessage());
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    finally {
                        break;
                    }
                case 15:

                    System.out.println("Introduzca los datos necesarios para la venta (dni / número de acciones / empresa)");
                    cadena=ent.leerCadena();
                    partes = cadena.split("/");
                    try {
                        sim.solicitarVenta( partes[0], Integer.parseInt(partes[1]),  partes[2]);
                    }catch(NoSuchEnterpriseException e){
                        System.out.println(e.getMessage());
                    }catch (NotEnoughActionsException e){
                        System.out.println(e.getMessage());
                    }catch (NotEnoughMoneyException e){
                        System.out.println("Fallo interno a la hora de realizar la solicitud, intentelo de nuevo");
                    }catch(InexistentClientException e){
                        System.out.println(e.getMessage());
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    finally {
                        break;
                    }

                case 16:

                    sim.solicitarActualizacion();
                    break;

                case 17:

                    sim.printOperaciones();
                    break;

                case 18:

                    sim.ejecutarOperaciones();
                    break;

                default:
                    System.out.println("Opción no válida: " + option);


            }
        }while (option != 0);
    }
}
