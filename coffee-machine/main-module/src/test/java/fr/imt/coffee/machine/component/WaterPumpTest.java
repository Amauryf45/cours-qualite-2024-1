package fr.imt.coffee.machine.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WaterPumpTest {

    /**
     * Fonction permettant de calculer le temps de pompage attendu.
     * @param waterVolume Volume d'eau à pomper.
     * @param pumpingCapacity Capacité de pompage en litres par seconde.
     */
    public double computePumpingTime(double waterVolume, double pumpingCapacity) {
        return (waterVolume / pumpingCapacity) * 1000 * 2;
    }

    /**
     * Test permettant de vérifier le fonctionnement de la pompe dans un cas nominal avec JUnit.
     * @throws InterruptedException
     */
    @Test
    public void testPumpWaterWithJUnit() throws InterruptedException {
        double waterVolume = 0.5;
        double pumpingCapacity = 0.5;

        double expectedPumpingTime = computePumpingTime(waterVolume, pumpingCapacity);

        WaterTank waterTank = new WaterTank(1.0, 0, 1.0);
        WaterPump pump = new WaterPump(pumpingCapacity);

        double actualPumpingTime = pump.pumpWater(waterVolume, waterTank);

        // Assertion avec JUnit
        Assertions.assertEquals(expectedPumpingTime, actualPumpingTime);
    }

    /**
     * Test permettant de vérifier le fonctionnement de la pompe dans un cas nominal avec Hamcrest.
     * @throws InterruptedException
     */
    @Test
    public void testPumpWaterWithHamcrest() throws InterruptedException {
        double waterVolume = 0.5;
        double pumpingCapacity = 0.5;

        double expectedPumpingTime = computePumpingTime(waterVolume, pumpingCapacity);

        WaterTank waterTank = new WaterTank(1.0, 0, 1.0);
        WaterPump pump = new WaterPump(pumpingCapacity);

        double actualPumpingTime = pump.pumpWater(waterVolume, waterTank);

        // Assertion avec Hamcrest
        assertThat(expectedPumpingTime, is(actualPumpingTime));
    }
}
