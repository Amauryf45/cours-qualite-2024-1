package fr.imt.coffee.machine;

import fr.imt.coffee.machine.component.BeanTank;
import fr.imt.coffee.machine.component.SteamPipe;
import fr.imt.coffee.storage.cupboard.coffee.type.CoffeeType;
import fr.imt.coffee.storage.cupboard.container.Cup;
import fr.imt.coffee.storage.cupboard.exception.CupNotEmptyException;
import fr.imt.coffee.machine.exception.LackOfWaterInTankException;
import fr.imt.coffee.machine.exception.MachineNotPluggedException;
import fr.imt.coffee.machine.exception.CoffeeTypeCupDifferentOfCoffeeTypeTankException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EspressoCoffeeMachineTest {

    private EspressoCoffeeMachine espressoMachineUnderTest;

    /**
     * Initialise une instance de EspressoCoffeeMachine avant chaque test.
     */
    @BeforeEach
    public void beforeTest() {
        espressoMachineUnderTest = new EspressoCoffeeMachine(0, 10, 0, 10, 700);
    }

        /**
     * Teste si une exception est levée pour un manque d'eau dans le réservoir.
     */
    @Test
    public void testMakeACoffeeLackOfWaterException() {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(true);

        Assertions.assertThrows(LackOfWaterInTankException.class, () -> {
            espressoMachineUnderTest.makeACoffee(mockCup, CoffeeType.ARABICA);
        });
    }

    /**
     * Teste si la machine lève une exception lorsque le contenant n'est pas vide.
     */
    @Test
    public void testMakeACoffeeCupNotEmptyException() {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(false);

        espressoMachineUnderTest.plugToElectricalPlug();
        espressoMachineUnderTest.addCoffeeInBeanTank(5, CoffeeType.ARABICA);

        Assertions.assertThrows(CupNotEmptyException.class, () -> {
            espressoMachineUnderTest.makeACoffee(mockCup, CoffeeType.ARABICA);
        });
    }

    /**
     * Teste si une exception est levée pour un manque d'eau dans le réservoir.
     */
    // @Test
    // public void testMakeACoffeeLackOfWaterException() {
    //     Cup mockCup = Mockito.mock(Cup.class);
    //     Mockito.when(mockCup.isEmpty()).thenReturn(true);

    //     espressoMachineUnderTest.plugToElectricalPlug();
    //     espressoMachineUnderTest.addCoffeeInBeanTank(5, CoffeeType.ARABICA);

    //     Assertions.assertThrows(LackOfWaterInTankException.class, () -> {
    //         espressoMachineUnderTest.makeACoffee(mockCup, CoffeeType.ARABICA);
    //     });
    // }

    /**
     * Teste si une exception est levée pour un type de café incompatible.
     */
    @Test
    public void testMakeACoffeeTypeMismatchException() {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(true);

        espressoMachineUnderTest.plugToElectricalPlug();
        espressoMachineUnderTest.addCoffeeInBeanTank(5, CoffeeType.ARABICA);

        Assertions.assertThrows(CoffeeTypeCupDifferentOfCoffeeTypeTankException.class, () -> {
            espressoMachineUnderTest.makeACoffee(mockCup, CoffeeType.ROBUSTA);
        });
    }

    /**
     * Teste le fonctionnement nominal de la machine pour préparer un café.
     */
    @Test
    public void testMakeACoffeeNominalCase() throws Exception {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(true);
        Mockito.when(mockCup.getCapacity()).thenReturn(0.2);

        espressoMachineUnderTest.plugToElectricalPlug();
        espressoMachineUnderTest.addWaterInTank(1);
        espressoMachineUnderTest.addCoffeeInBeanTank(5, CoffeeType.ARABICA);

        Assertions.assertNotNull(espressoMachineUnderTest.makeACoffee(mockCup, CoffeeType.ARABICA));
    }

    /**
     * Réinitialise les paramètres après chaque test.
     */
    @AfterEach
    public void afterTest() {
        espressoMachineUnderTest.reset();
    }
}
