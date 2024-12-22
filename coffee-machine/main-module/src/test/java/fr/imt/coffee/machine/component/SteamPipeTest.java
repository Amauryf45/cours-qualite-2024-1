package fr.imt.coffee.machine.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SteamPipeTest {

    /**
     * Test permettant de vérifier que la buse de vapeur peut être allumée avec JUnit.
     */
    @Test
    public void testTurnOnSteamPipeWithJUnit() {
        SteamPipe steamPipe = new SteamPipe();
        steamPipe.setOn();

        // Assertion avec JUnit
        Assertions.assertTrue(steamPipe.isOn());
    }

    /**
     * Test permettant de vérifier que la buse de vapeur peut être éteinte avec Hamcrest.
     */
    @Test
    public void testTurnOffSteamPipeWithHamcrest() {
        SteamPipe steamPipe = new SteamPipe();
        steamPipe.setOn();
        steamPipe.setOff();

        // Assertion avec Hamcrest
        assertThat(false, is(steamPipe.isOn()));
    }
}
