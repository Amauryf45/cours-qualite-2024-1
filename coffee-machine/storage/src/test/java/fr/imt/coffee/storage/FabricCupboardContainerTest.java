package fr.imt.coffee.storage.cupboard;

import fr.imt.coffee.storage.cupboard.container.Container;
import fr.imt.coffee.storage.cupboard.container.Cup;
import fr.imt.coffee.storage.cupboard.container.Mug;
import fr.imt.coffee.storage.cupboard.exception.ExceptionContainerCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FabricCupboardContainerTest {

    /**
     * Test pour vérifier que l'instance retournée est unique (Singleton)
     */
    @Test
    public void testSingletonInstance() {
        FabricCupboardContainer instance1 = FabricCupboardContainer.getFabricContainerInstance();
        FabricCupboardContainer instance2 = FabricCupboardContainer.getFabricContainerInstance();

        Assertions.assertNotNull(instance1, "L'instance ne doit pas être nulle.");
        Assertions.assertSame(instance1, instance2, "Les deux instances doivent être identiques.");
    }

    /**
     * Test pour vérifier la création d'un Mug valide
     */
    @Test
    public void testCreateMug() throws ExceptionContainerCreation {
        FabricCupboardContainer fabric = FabricCupboardContainer.getFabricContainerInstance();
        double capacity = 0.35;

        Container mug = fabric.getContainer("mug", capacity);

        Assertions.assertNotNull(mug, "Le mug ne doit pas être null.");
        Assertions.assertTrue(mug instanceof Mug, "Le contenant doit être une instance de Mug.");
        Assertions.assertEquals(capacity, mug.getCapacity(), "La capacité du mug doit correspondre.");
    }

    /**
     * Test pour vérifier la création d'une Cup valide
     */
    @Test
    public void testCreateCup() throws ExceptionContainerCreation {
        FabricCupboardContainer fabric = FabricCupboardContainer.getFabricContainerInstance();
        double capacity = 0.15;

        Container cup = fabric.getContainer("cup", capacity);

        Assertions.assertNotNull(cup, "La cup ne doit pas être null.");
        Assertions.assertTrue(cup instanceof Cup, "Le contenant doit être une instance de Cup.");
        Assertions.assertEquals(capacity, cup.getCapacity(), "La capacité de la cup doit correspondre.");
    }

    /**
     * Test pour vérifier la levée d'exception pour un type de contenant inconnu
     */
    @Test
    public void testInvalidContainerType() {
        FabricCupboardContainer fabric = FabricCupboardContainer.getFabricContainerInstance();
        String invalidType = "plate";

        ExceptionContainerCreation thrown = Assertions.assertThrows(
                ExceptionContainerCreation.class,
                () -> fabric.getContainer(invalidType, 0.2),
                "Une exception doit être levée pour un type de contenant invalide."
        );

        Assertions.assertTrue(
                thrown.getMessage().contains("Container not available in the storage cupboard"),
                "Le message d'exception doit mentionner que le type de contenant est invalide."
        );
    }
}
