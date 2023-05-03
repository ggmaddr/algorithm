package greedy;

import java.util.ArrayList;


public class Knapsack {

    static class Item {
        String name;
        int value;
        int weight;
        double costPerPound; //keeping track of this for sorting purposes;

        Item(String name,int value,int weight) {
            this.name = name;
            this.value = value;
            this.weight = weight;
            costPerPound = (double)value/weight;

        }

        public String toString() {
            return name +", value: "+value + ", weight: " + weight;
        }

    }

    static ArrayList<Double> fractionalKnapsack(ArrayList<Item> items,double maxWeight){
        //initialize
        ArrayList<Double> fractions = new ArrayList<>();
        double totalWeight = 0;

        for (int i = 0; i < items.size(); i++) {
            if (totalWeight == maxWeight) {
                fractions.add(0.0);
                continue;
            }
            Item currentItem = items.get(i);
            double remainingWeight = maxWeight - totalWeight;

            if (currentItem.weight <= remainingWeight) {
                fractions.add(1.0);
                totalWeight += currentItem.weight;
            } else {
                double fraction = remainingWeight / currentItem.weight;
                fractions.add(fraction);
                totalWeight = maxWeight;
            }
        }

        return fractions;
    }

    public static void main(String[] args) {
        ArrayList<Item> itemsSet1 = new ArrayList<>();
        itemsSet1.add(new Item("bag", 210,3 ));
        itemsSet1.add(new Item("case", 200, 4));
        itemsSet1.add(new Item("pen", 300,10));
        System.out.println(fractionalKnapsack(itemsSet1,14));

        ArrayList<Item> itemsSet2 = new ArrayList<>();
        int [] values = {6, 10, 18, 15, 3, 5, 7};
        int [] weights = {1, 2, 4, 5, 1, 3, 7};
        String[] names = {"a1", "a2", "a3", "a4", "a5", "a6", "a7"};
        for (int i = 0; i < values.length;i++)
        {
            itemsSet2.add(new Item(names[i], values[i], weights[i]));
        }
        ArrayList<Double> fractions = fractionalKnapsack(itemsSet2, 15);
        System.out.println(fractions);
        //calculate the maximized profit.
        double totalProfit = 0;
        for (int i = 0; i < values.length;i++)
        {
            totalProfit+=fractions.get(i)* values[i];
            System.out.println(fractions.get(i) +" * "+values[i]);
        }
        System.out.println(totalProfit);
    }


}

