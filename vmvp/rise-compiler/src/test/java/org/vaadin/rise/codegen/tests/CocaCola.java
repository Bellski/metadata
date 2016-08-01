package org.vaadin.rise.codegen.tests;

/**
 * Created by Aleksandr on 30.07.2016.
 */
public class CocaCola {
    public static class BottleOfCocaCola {
        private final String name = "CocaCola";
        private final double cost = 2.0;

        public double getCost() {
            return cost;
        }

        @Override
        public String toString() {
            return "BottleOfCocaCola{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {
        getCocaCola(1);
    }

    public static BottleOfCocaCola getCocaCola(double money) {
        final BottleOfCocaCola bottleOfCocaCola = new BottleOfCocaCola();

        if (money != bottleOfCocaCola.getCost()) {
            throw new IllegalStateException("Внесите еще денег");
        }

        return bottleOfCocaCola;
    }
}
