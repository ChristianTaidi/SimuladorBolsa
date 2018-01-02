package simulador.bolsa.poo.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteFile<T> implements IOStream {
    FileOutputStream stream;
    ObjectOutputStream objectOut;


    public WriteFile(File fich) throws IOException{
        stream=new FileOutputStream(fich);
        objectOut = new ObjectOutputStream(stream);

    }

    public void write(T ent) throws IOException{
        objectOut.writeObject(ent);
    }

    @Override
    public void cerrar(File fich) throws IOException{
       objectOut.close();
    }
}
