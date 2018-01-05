package simulador.bolsa.poo.funcionalidades.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFile implements IOStream {
    private  FileInputStream stream;
    private  ObjectInputStream  objectIn;

    public ReadFile(String fich) throws IOException {
        stream= new FileInputStream(fich);
        objectIn = new ObjectInputStream(stream);
    }
    public ReadFile(File fich) throws IOException {
        stream= new FileInputStream(fich);
        objectIn = new ObjectInputStream(stream);
    }

    public Object read() throws IOException, ClassNotFoundException {

            return  objectIn.readObject();

    }


    public void cerrar() {
        try {
            objectIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
