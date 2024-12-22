package fr.imt.coffee.machine.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TankTest {

    /**
     * Fonction permettant de calculer le volume attendu après ajout dans le réservoir.
     * @param initialVolume Volume initial du réservoir.
     * @param addedVolume Volume ajouté au réservoir.
     */
    public double computeExpectedVolume(double initialVolume, double addedVolume) {
        return initialVolume + addedVolume;
    }

    /**
     * Test permettant de vérifier l'augmentation du volume dans un cas nominal avec JUnit.
     */
    @Test
    public void testIncreaseVolumeInTankWithJUnit() {
        double initialVolume = 0.5;
        double addedVolume = 0.3;

        double expectedVolume = computeExpectedVolume(initialVolume, addedVolume);

        Tank tank = new Tank(initialVolume, 0, 1.0);
        tank.increaseVolumeInTank(addedVolume);

        // Assertion avec JUnit
        Assertions.assertEquals(expectedVolume, tank.getActualVolume());
    }

    /**
     * Test permettant de vérifier l'augmentation du volume dans un cas nominal avec Hamcrest.
     */
    @Test
    public void testIncreaseVolumeInTankWithHamcrest() {
        double initialVolume = 0.5;
        double addedVolume = 0.3;

        double expectedVolume = computeExpectedVolume(initialVolume, addedVolume);

        Tank tank = new Tank(initialVolume, 0, 1.0);
        tank.increaseVolumeInTank(addedVolume);

        // Assertion avec Hamcrest
        assertThat(expectedVolume, is(tank.getActualVolume()));
    }
}
