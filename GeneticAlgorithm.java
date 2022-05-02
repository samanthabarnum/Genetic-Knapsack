//Samantha Barnum
//CS 1181L-07
//Project 1
//2/3/22

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GeneticAlgorithm {

    public static ArrayList<Item> readData(String filename) throws FileNotFoundException{
        //reads in a data file with the format shown below and creates and returns an ArrayList of item objects
        //item1_label, item1_weight, item1_value
        //item2_label, item2_weight, item2_value
        ArrayList<Item> itemObjects = new ArrayList<Item>();
        File file = new File(filename);
        Scanner input = new Scanner(file);
        String fileLine = "";
        while(input.hasNextLine()){
            fileLine = input.nextLine();
            Scanner splitString = new Scanner(fileLine);
            splitString.useDelimiter(", ");
            String name = splitString.next();
            Double weight = splitString.nextDouble();
            int value = splitString.nextInt();
            Item currentItem = new Item(name, weight, value);
            itemObjects.add(currentItem);
        }
      return itemObjects;
    }

    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize){
        //creates and returns an ArrayList of *populationSizeChromosome* objects that each contain
        //the *items* with their *included* field randomly set to true or false
        ArrayList<Chromosome> populationSizeChromosome = new ArrayList<Chromosome>();
        for(int i = 0; i < populationSize; i++){
            Chromosome currentChromosome = new Chromosome(items);
            populationSizeChromosome.add(currentChromosome);
        }
        return populationSizeChromosome;
    }

    public static void main(String[] args) throws FileNotFoundException{
        Random rng = new Random();

        ArrayList<Item> arrayList = readData("src/items.txt");

        //1. Create a set of ten random individuals to serve as the initial population
        //I have it running with 1000 initial population because I was making sure it worked with moreitems.txt
        ArrayList <Chromosome> initialPopulation = initializePopulation(arrayList, 100);
        ArrayList<Chromosome> currentPopulation = new ArrayList<Chromosome>();
        ArrayList<Chromosome> nextGeneration = new ArrayList<Chromosome>();

        //2. Add each of the individuals in the current population to the next generation 
        for(int i = 0; i< initialPopulation.size(); i++){
            currentPopulation.add(initialPopulation.get(i));
        }

        int o = 0;
        //7. Repeat steps 2 through 6 twenty times 
        //It's running 5000 times instead of 20 times to make sure it worked with moreitems.txt
        while(o < 5000){

        for(int i = 0; i< currentPopulation.size(); i++){
            nextGeneration.add(currentPopulation.get(i));
        }

        //3. Randomly pair off the individuals and perform crossover to create a child and add it to the next generation as well.  
        Collections.shuffle(currentPopulation);
        for(int i = 0; i < currentPopulation.size(); i+=2){
            Chromosome currentParent = currentPopulation.get(i);
            Chromosome otherParent = currentPopulation.get(i+1);
            Chromosome child = currentParent.crossover(otherParent);
            nextGeneration.add(child);
    }

        //4. Randomly choose ten percent of the individuals in the next generation and expose them to mutation.
        //I chose 7 but it could be any number between 0-9
        for(int i = 0; i < nextGeneration.size(); i++){
        if(rng.nextInt(10) == 7){
            nextGeneration.get(i).mutate();
        }
    }

        //5. Sort the individuals in the next generation according to their fitness
        Collections.sort(nextGeneration);

        // 6. Clear out the current population and add the top ten of the next generation back into the population.
        currentPopulation.clear();
        for(int i = 0; i < 10; i++){
            currentPopulation.add(nextGeneration.get(i));
        }
        nextGeneration.clear();
        o++;
    }

     Collections.sort(currentPopulation);
     System.out.println("The combination of items you should take with you in a zombie apocolypse are: ");
     System.out.println();
     System.out.println(currentPopulation.get(0));
    }
}
