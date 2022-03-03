import java.io.*;
import java.net.Socket;
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
        leghidegebbMa();
        legmelegebbMa();
        legmelegebbHolnap();
        leghidegebbHolnap();
    }

    private String leghidegebbMa() {
        Object elso = elorejelzesek.keySet().toArray()[0];
        int minMa = elorejelzesek.get(elso).getMai().getMin();

        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            if(minMa > entry.getValue().getMai().getMin()){
                minMa = entry.getValue().getMai().getMin();
            }
        }
        return String.format("%d volt a leghidegebb a mai nap.", minMa);
    }
    private String legmelegebbMa() {
        int maxMa = 0;
        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            if(maxMa < entry.getValue().getMai().getMax()){
                maxMa = entry.getValue().getMai().getMax();
            }
        }
        return String.format("%d volt a legmelegebb a mai nap.", maxMa);
    }
    private String legmelegebbHolnap() {
        int maxHolnap = 0;
        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            if(maxHolnap < entry.getValue().getHolnapi().getMax()){
                maxHolnap = entry.getValue().getHolnapi().getMax();
            }
        }
        return String.format("%d lesz a legmelegebb holnap.", maxHolnap);
    }
    private String leghidegebbHolnap() {
        Object elso = elorejelzesek.keySet().toArray()[0];
        int minHolnap = elorejelzesek.get(elso).getHolnapi().getMin();

        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            if(minHolnap > entry.getValue().getHolnapi().getMin()){
                minHolnap = entry.getValue().getHolnapi().getMin();
            }
        }
        return String.format("%d lesz a leghidegebb holnap.", minHolnap);

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
