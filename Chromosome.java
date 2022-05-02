//Samantha Barnum
//CS 1181L-07
//Project 1
//2/3/22

import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome>{
    
    private static Random rng = new Random();
        //used for random number generation

    public Chromosome(){
        //this no-arg constructor is empty
    }

    public Chromosome(ArrayList<Item> items){
        //adds a copy of each of the items passed in to this Chromosome.
        //uses a random number to decide whether each item's included field is set to true or false.
        for(int i = 0; i < items.size(); i++){
            Item copy = new Item (items.get(i));
            int flag = rng.nextInt(10);
            if(flag == 1){
                copy.setIncluded(true);
            }
            add(copy);
        }
    }

    public Chromosome crossover(Chromosome other){
        //creates and returns a new child chromosome by performing the crossover operation on *this* chromosome and the *other* one
        //that is passed in (ie for each item, use a random number to decide which parent's item should be copied and added to the
        //child)
        Chromosome child = new Chromosome();
        for(int i = 0; i < this.size(); i++){
            Item currentItem = this.get(i);
            Item otherItem = other.get(i);
            int randomInt = rng.nextInt(10);
            if(randomInt > 5){
                Item newChild = new Item(otherItem);
                child.add(newChild);
            }else{
                Item newChild = new Item(currentItem);
                child.add(newChild);
            }
        }
        return child;
    }

    public void mutate(){
        //performs the mutation operation on this chromosome (ie for each item in this chromosome, use a random number to decide
        //whether or not to flip it's included field from true to false or vice versa)
        for(int i = 0; i < this.size(); i++){
            int randomInt = rng.nextInt(10);
            Item currentItem = this.get(i);
            if(randomInt == 0){
                currentItem.setIncluded(!currentItem.isIncluded());
            }
        }
    }

    public int getFitness(){
        //returns the fitness of this chromosome.  if the sum of all the included items' weights are greater than 10, the fitness
        //is zero.  otherwise, the fitness is equal to the sum of all of the included items' values.
        int valueTotal = 0;
        double weightTotal = 0;
        int fitness = 0;
        for(int i = 0; i < this.size(); i++){
            Item currentItem = this.get(i);
            if(currentItem.isIncluded()){
            int currentValue = currentItem.getValue();
            double currentWeight = currentItem.getWeight();
            weightTotal = weightTotal + currentWeight;
            valueTotal = valueTotal + currentValue;
        }
    }
        if(weightTotal > 10){
            fitness = 0;
        }else{
            fitness = valueTotal;
        }
        return fitness;
    }

    public int compareTo(Chromosome other){
        //returns -1 if *this* chromosome's fitness is greater than the *other*'s fitness, +1 if *this* chromosome's fitness is less
        //than the *other* one's, and 0 if their fitness is the same
        if(this.getFitness() < other.getFitness()){
            return 1;
        }
        else if (this.getFitness() > other.getFitness()){
            return -1;
        }
        else{
            return 0;
        }
    }

    public String toString(){
        //displays the name, weight, and value of all the items in this chromosome whose included value is true, followed by the fitness of this chromosome
        String returnString = "";
        for(int i = 0; i < this.size(); i++){
            Item currentItem = this.get(i);
            if(currentItem.isIncluded() == true){
            returnString += (currentItem.toString()) + " ";
            }
        }
        returnString += ("\nThe combined value for these items is: $" + this.getFitness() + ".\nGood luck with the zombies!\n");
        return returnString;
    }

}