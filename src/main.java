import java.util.List;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        Population p = new Population();

        int pocetEvoluci = 3000;
        int pocetMutaci = 25;
        int pocetKrizeni = 25;
        for(int i = 0; i < pocetEvoluci; i++){
            p.mutate(pocetMutaci);
            p.krizeni(pocetKrizeni);
            p.selekce(50);
        }

        for(int i = 0; i < 50; i++){
            List<Chromozome> list = p.getChromozomes();
            Chromozome ch = list.get(i);
            ShowChromozome.show(ch, "populace 30");
        }
    }

}