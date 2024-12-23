package fr.imt.coffee.machine.cucumber.steps;

import fr.imt.coffee.machine.EspressoCoffeeMachine;
import fr.imt.coffee.machine.exception.CannotMakeCremaWithSimpleCoffeeMachine;
import fr.imt.coffee.machine.exception.CoffeeTypeCupDifferentOfCoffeeTypeTankException;
import fr.imt.coffee.machine.exception.LackOfWaterInTankException;
import fr.imt.coffee.machine.exception.MachineNotPluggedException;
import fr.imt.coffee.storage.cupboard.coffee.type.CoffeeType;
import fr.imt.coffee.storage.cupboard.container.*;
import fr.imt.coffee.storage.cupboard.exception.CupNotEmptyException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class CucumberStepsExpressoCoffeeMachineTest {

    public EspressoCoffeeMachine espressoMachine;
    public Mug mug;
    public Cup cup;
    public CoffeeContainer containerWithCoffee;

    @Given("an espresso coffee machine with {double} l of min capacity, {double} l of max capacity, {double} l per h of water flow for the pump")
    public void givenAnEspressoCoffeeMachine(double minimalWaterCapacity, double maximalWaterCapacity, double pumpWaterFlow){
        espressoMachine = new EspressoCoffeeMachine(minimalWaterCapacity, maximalWaterCapacity, minimalWaterCapacity, maximalWaterCapacity, pumpWaterFlow);
    }

    @And("an espresso {string} with a capacity of {double}")
    public void aWithACapacityOfEspresso(String containerType, double containerCapacity) {
        if ("mug".equals(containerType))
            mug = new Mug(containerCapacity);
        if ("cup".equals(containerType))
            cup = new Cup(containerCapacity);
    }

    @When("I plug the espresso machine to electricity")
    public void iPlugTheEspressoMachineToElectricity() {
        espressoMachine.plugToElectricalPlug();
    }

    @And("I add {double} liter of water to the espresso machine tank")
    public void iAddLitersOfWaterEspresso(double waterVolume) {
        espressoMachine.addWaterInTank(waterVolume);
    }

    @And("I add {double} liter of {string} to the espresso machine bean tank")
    public void iAddLitersOfCoffeeBeansEspresso(double beanVolume, String coffeeType) {
        espressoMachine.addCoffeeInBeanTank(beanVolume, CoffeeType.valueOf(coffeeType));
    }

    @And("I make a coffee with the espresso machine {string}")
    public void iMadeACoffeeWithTheEspressoMachine(String coffeeType) throws InterruptedException, CupNotEmptyException, LackOfWaterInTankException, MachineNotPluggedException, CoffeeTypeCupDifferentOfCoffeeTypeTankException, CannotMakeCremaWithSimpleCoffeeMachine {
        Random randomMock = Mockito.mock(Random.class, Mockito.withSettings().withoutAnnotations());
        Mockito.when(randomMock.nextGaussian()).thenReturn(0.6);
        espressoMachine.setRandomGenerator(randomMock);

        if (mug != null)
            containerWithCoffee = espressoMachine.makeACoffee(mug, CoffeeType.valueOf(coffeeType));
        if (cup != null)
            containerWithCoffee = espressoMachine.makeACoffee(cup, CoffeeType.valueOf(coffeeType));
    }

    @Then("the espresso machine returns a coffee {string} not empty")
    public void theEspressoMachineReturnsACoffeeMugNotEmpty(String containerType) {
        Assertions.assertFalse(containerWithCoffee.isEmpty());
        if ("mug".equals(containerType))
            assertThat(containerWithCoffee, instanceOf(CoffeeMug.class));
        if ("cup".equals(containerType))
            assertThat(containerWithCoffee, instanceOf(CoffeeCup.class));
    }


    @And("a coffee volume for espresso equals to {double}")
    public void aCoffeeVolumeEqualsToEspresso(double coffeeVolume) {
        assertThat(coffeeVolume, is(containerWithCoffee.getCapacity()));
    }

    @And("a coffee {string} containing a coffee type {string} for espresso")
    public void aCoffeeMugContainingACoffeeTypeExpresso(String containerType, String coffeeType) {
        if ("mug".equals(containerType))
            assertThat(containerWithCoffee, instanceOf(CoffeeMug.class));
        if ("cup".equals(containerType))
            assertThat(containerWithCoffee, instanceOf(CoffeeCup.class));

        assertThat(containerWithCoffee.getCoffeeType(), is(CoffeeType.valueOf(coffeeType)));
    }



    // @Given("an espresso coffee machine with {double} l of min capacity, {double} l of max capacity, {double} l per h of water flow for the pump")
    // public void givenAnEspressoCoffeeMachine(double minCapacity, double maxCapacity, double pumpFlow) {
    //     espressoMachine = new EspressoCoffeeMachine(minCapacity, maxCapacity, minCapacity, maxCapacity, pumpFlow);
    // }

    // @When("I plug the espresso machine to electricity")
    // public void iPlugTheEspressoMachineToElectricity() {
    //     espressoMachine.plugToElectricalPlug();
    // }

    // @And("I made a coffee with the espresso machine {string}")
    // public void iMadeACoffeeWithTheEspressoMachine(String coffeeType) throws InterruptedException, CupNotEmptyException, LackOfWaterInTankException, MachineNotPluggedException, CoffeeTypeCupDifferentOfCoffeeTypeTankException {
    //     if (mug != null)
    //         containerWithCoffee = espressoMachine.makeACoffee(mug, CoffeeType.valueOf(coffeeType));
    //     if (cup != null)
    //         containerWithCoffee = espressoMachine.makeACoffee(cup, CoffeeType.valueOf(coffeeType));
    // }


}


