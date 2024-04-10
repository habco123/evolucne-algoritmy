import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
    private List<Chromozome> chromozomes;
    private Random random;
    private Fitness eval = new Fitness("src/duha.jpg");

    public Population() throws IOException {
        this.chromozomes = new ArrayList<>();
        this.random = new Random();
        initializePopulation();
    }

    private void initializePopulation() {
        for(int i = 0; i < 50; i++){
            Chromozome ch = new Chromozome();
            ch.mutateAll();
            chromozomes.add(ch);
        }
    }
    public void mutate(int pocetMutaci) {
        double probability = 0.5;
        for(int i = 0; i < pocetMutaci; i++){
            if(random.nextDouble(1) < probability) {
                Chromozome ch = chromozomes.get(random.nextInt(chromozomes.size()));
                Chromozome chClone = ch.cloneChromozome();
                chClone.mutateAll();
                chromozomes.add(chClone);
            }
        }
    }
    public void krizeni(int pocetKrizeni){
        for(int i = 0; i < pocetKrizeni; i++){
            Chromozome parent1 = chromozomes.get(random.nextInt(chromozomes.size()));
            Chromozome parent2 = chromozomes.get(random.nextInt(chromozomes.size()));

            Chromozome child = parent1.cloneChromozome();
            child.crossOver(parent2);
            chromozomes.add(child);
        }
    }

    public void selekce(int turnajPocet){
        List<Chromozome> newPopulation = new ArrayList<>();
        for(int i = 0; i < turnajPocet; i++){
            int idx1 = random.nextInt(chromozomes.size());
            int idx2 = random.nextInt(chromozomes.size());

            Chromozome ch1 = chromozomes.get(idx1);
            chromozomes.remove(idx1);
            Chromozome ch2 = chromozomes.get(idx2);
            chromozomes.remove(idx2);

            int fitness1 = eval.getFitness(ch1);
            int fitness2 = eval.getFitness(ch2);
            if(fitness1 < fitness2){
                newPopulation.add(ch1);
            }else{
                newPopulation.add(ch2);
            }
        }
        chromozomes = newPopulation;
    }
    public List<Chromozome> getChromozomes() {
        return chromozomes;
    }
}