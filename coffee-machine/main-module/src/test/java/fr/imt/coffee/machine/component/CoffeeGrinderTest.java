package fr.imt.coffee.machine.component;

import fr.imt.coffee.storage.cupboard.coffee.type.CoffeeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import  fr.imt.coffee.storage.cupboard.coffee.type.CoffeeType;

public class CoffeeGrinderTest {

    /**
     * Fonction permettant de calculer le temps de broyage attendu.
     * @param grindingTime Temps de broyage configuré dans le moulin.
     */
    public double computeExpectedGrindingTime(int grindingTime) {
        return grindingTime;
    }

    /**
     * Test permettant de vérifier le fonctionnement du moulin à café dans un cas nominal avec JUnit.
     * @throws InterruptedException
     */
    @Test
    public void testGrindCoffeeWithJUnit() throws InterruptedException {
        int grindingTime = 2000;
        BeanTank beanTank = new BeanTank(0.5, 0, 1.0, CoffeeType.ARABICA);

        CoffeeGrinder coffeeGrinder = new CoffeeGrinder(grindingTime);
        double actualGrindingTime = coffeeGrinder.grindCoffee(beanTank);

        // Assertion avec JUnit
        Assertions.assertEquals(computeExpectedGrindingTime(grindingTime), actualGrindingTime);
    }

    /**
     * Test permettant de vérifier le fonctionnement du moulin à café dans un cas nominal avec Hamcrest.
     * @throws InterruptedException
     */
    @Test
    public void testGrindCoffeeWithHamcrest() throws InterruptedException {
        int grindingTime = 2000;
        BeanTank beanTank = new BeanTank(0.5, 0, 1.0, CoffeeType.ARABICA);

        CoffeeGrinder coffeeGrinder = new CoffeeGrinder(grindingTime);
        double actualGrindingTime = coffeeGrinder.grindCoffee(beanTank);

        // Assertion avec Hamcrest
        assertThat(computeExpectedGrindingTime(grindingTime), is(actualGrindingTime));
    }
}
