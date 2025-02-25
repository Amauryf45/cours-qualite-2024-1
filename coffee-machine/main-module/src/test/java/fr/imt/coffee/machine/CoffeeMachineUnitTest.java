package fr.imt.coffee.machine;

import fr.imt.coffee.storage.cupboard.coffee.type.CoffeeType;
import fr.imt.coffee.storage.cupboard.container.Cup;
import fr.imt.coffee.storage.cupboard.exception.CupNotEmptyException;
import fr.imt.coffee.machine.exception.LackOfWaterInTankException;
import fr.imt.coffee.machine.exception.MachineNotPluggedException;
import fr.imt.coffee.machine.exception.CoffeeTypeCupDifferentOfCoffeeTypeTankException;
import fr.imt.coffee.machine.exception.CannotMakeCremaWithSimpleCoffeeMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CoffeeMachineUnitTest {

    private CoffeeMachine coffeeMachineUnderTest;

    /**
     * Initialise une instance de CoffeeMachine avant chaque test.
     */
    @BeforeEach
    public void beforeTest() {
        coffeeMachineUnderTest = new CoffeeMachine(0, 10, 0, 10, 700);
    }

    /**
     * Teste si la machine se met en défaut lorsque le tirage aléatoire dépasse la valeur limite.
     */
    @Test
    public void testMachineFailureTrue() {
        Random randomMock = Mockito.mock(Random.class, Mockito.withSettings().withoutAnnotations());
        Mockito.when(randomMock.nextGaussian()).thenReturn(1.0);
        coffeeMachineUnderTest.setRandomGenerator(randomMock);

        Assertions.assertFalse(coffeeMachineUnderTest.isOutOfOrder());
        coffeeMachineUnderTest.coffeeMachineFailure();
        Assertions.assertTrue(coffeeMachineUnderTest.isOutOfOrder());
    }

    /**
     * Teste si la machine ne se met pas en défaut pour une valeur aléatoire acceptable.
     */
    @Test
    public void testMachineFailureFalse() {
        Random randomMock = Mockito.mock(Random.class, Mockito.withSettings().withoutAnnotations());
        Mockito.when(randomMock.nextGaussian()).thenReturn(0.6);
        coffeeMachineUnderTest.setRandomGenerator(randomMock);

        Assertions.assertFalse(coffeeMachineUnderTest.isOutOfOrder());
        coffeeMachineUnderTest.coffeeMachineFailure();
        Assertions.assertFalse(coffeeMachineUnderTest.isOutOfOrder());
    }

    /**
     * Vérifie que la machine se branche correctement au réseau électrique.
     */
    @Test
    public void testPlugMachine() {
        Assertions.assertFalse(coffeeMachineUnderTest.isPlugged());
        coffeeMachineUnderTest.plugToElectricalPlug();
        Assertions.assertTrue(coffeeMachineUnderTest.isPlugged());
    }

    /**
     * Vérifie qu'une exception est levée si le contenant n'est pas vide.
     */
    @Test
    public void testMakeACoffeeCupNotEmptyException() {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(false);

        coffeeMachineUnderTest.plugToElectricalPlug();
        coffeeMachineUnderTest.addCoffeeInBeanTank(5, CoffeeType.MOKA);

        Assertions.assertThrows(CupNotEmptyException.class, () -> {
            coffeeMachineUnderTest.makeACoffee(mockCup, CoffeeType.MOKA);
        });
    }

    /**
     * Teste le cas où la machine n'est pas branchée au réseau électrique.
     */
    @Test
    public void testMakeACoffeeMachineNotPluggedException() {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(true);

        Assertions.assertThrows(MachineNotPluggedException.class, () -> {
            coffeeMachineUnderTest.makeACoffee(mockCup, CoffeeType.MOKA);
        });
    }

    /**
     * Vérifie qu'une exception est levée si le type de café demandé diffère de celui du réservoir.
     */
    @Test
    public void testMakeACoffeeTypeMismatchException() {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(true);

        coffeeMachineUnderTest.plugToElectricalPlug();
        coffeeMachineUnderTest.addCoffeeInBeanTank(5, CoffeeType.ARABICA);

        Assertions.assertThrows(CoffeeTypeCupDifferentOfCoffeeTypeTankException.class, () -> {
            coffeeMachineUnderTest.makeACoffee(mockCup, CoffeeType.MOKA);
        });
    }

    /**
     * Teste le fonctionnement nominal pour préparer un café.
     */
    @Test
    public void testMakeACoffeeNominalCase() throws Exception {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(true);
        Mockito.when(mockCup.getCapacity()).thenReturn(0.2);

        coffeeMachineUnderTest.plugToElectricalPlug();
        coffeeMachineUnderTest.addWaterInTank(1);
        coffeeMachineUnderTest.addCoffeeInBeanTank(5, CoffeeType.MOKA);

        Assertions.assertNotNull(coffeeMachineUnderTest.makeACoffee(mockCup, CoffeeType.MOKA));
    }

    /**
     * Teste si une exception est levée pour un manque d'eau dans le réservoir.
     */
    @Test
    public void testMakeACoffeeLackOfWaterException() {
        Cup mockCup = Mockito.mock(Cup.class);
        Mockito.when(mockCup.isEmpty()).thenReturn(true);
        Mockito.when(mockCup.getCapacity()).thenReturn(0.2);

        coffeeMachineUnderTest.plugToElectricalPlug();
        coffeeMachineUnderTest.addCoffeeInBeanTank(5, CoffeeType.MOKA);

        Assertions.assertThrows(LackOfWaterInTankException.class, () -> {
            coffeeMachineUnderTest.makeACoffee(mockCup, CoffeeType.MOKA);
        });
    }

    /**
     * Réinitialise les paramètres après chaque test.
     */
    @AfterEach
    public void afterTest() {
        coffeeMachineUnderTest.reset();
    }
}
