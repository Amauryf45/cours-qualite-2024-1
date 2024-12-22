package fr.imt.coffee.machine.component;

import fr.imt.coffee.storage.cupboard.coffee.type.CoffeeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import  fr.imt.coffee.storage.cupboard.coffee.type.CoffeeType;

public class BeanTankTest {

    /**
     * Fonction permettant de calculer le volume attendu après ajout dans le réservoir de grains de café.
     * @param initialVolume Volume initial du réservoir.
     * @param addedVolume Volume ajouté au réservoir.
     */
    public double computeExpectedVolume(double initialVolume, double addedVolume) {
        return initialVolume + addedVolume;
    }

    /**
     * Test permettant de vérifier l'ajout de grains dans le réservoir de grains avec JUnit.
     */
    @Test
    public void testIncreaseCoffeeVolumeInTankWithJUnit() {
        double initialVolume = 0.5;
        double addedVolume = 0.2;
        CoffeeType coffeeType = CoffeeType.ARABICA;

        double expectedVolume = computeExpectedVolume(initialVolume, addedVolume);

        BeanTank beanTank = new BeanTank(initialVolume, 0, 1.0, coffeeType);
        beanTank.increaseCoffeeVolumeInTank(addedVolume, coffeeType);

        // Assertion avec JUnit
        Assertions.assertEquals(expectedVolume, beanTank.getActualVolume());
        Assertions.assertEquals(coffeeType, beanTank.getBeanCoffeeType());
    }

    /**
     * Test permettant de vérifier l'ajout de grains dans le réservoir de grains avec Hamcrest.
     */
    @Test
    public void testIncreaseCoffeeVolumeInTankWithHamcrest() {
        double initialVolume = 0.5;
        double addedVolume = 0.2;
        CoffeeType coffeeType = CoffeeType.MOKA;

        double expectedVolume = computeExpectedVolume(initialVolume, addedVolume);

        BeanTank beanTank = new BeanTank(initialVolume, 0, 1.0, coffeeType);
        beanTank.increaseCoffeeVolumeInTank(addedVolume, coffeeType);

        // Assertion avec Hamcrest
        assertThat(expectedVolume, is(beanTank.getActualVolume()));
        assertThat(coffeeType, is(beanTank.getBeanCoffeeType()));
    }
}
