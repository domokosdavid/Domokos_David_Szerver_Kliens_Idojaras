import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class Ugyfelkiszolgalo implements  Runnable{
    private HashMap<String, Idojaras> elorejelzesek;

    public Ugyfelkiszolgalo(){
        elorejelzesek = new HashMap<>();
        Beolvas();
    }


    @Override
    public void run() {

    }

    public void Beolvas(){
        try{
            BufferedReader br= new BufferedReader(new FileReader("weather.txt"));
            br.readLine();
            String sor = br.readLine();
            while(sor != null){
                Idojaras i = new Idojaras(sor);
                String megye = i.getMegye();
                elorejelzesek.put(megye, i);
                sor = br.readLine();
            }
            for(Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
                System.out.println(entry.getValue());
            }

        }
        catch (FileNotFoundException ex){
            System.out.println(ex);
        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }




}
