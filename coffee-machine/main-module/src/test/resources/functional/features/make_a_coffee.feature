Feature: Make a coffee with a complete coffee machine
  A user want a coffee
  Scenario: A user plug the coffee machine and make a coffee Arabica
    Given a coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
    And a "mug" with a capacity of 0.25
    When I plug the machine to electricity
    And I add 1 liter of water in the water tank
    And I add 0.5 liter of "ARABICA" in the bean tank
    And I made a coffee "ARABICA"
    Then the coffee machine return a coffee mug not empty
    And a coffee volume equals to 0.25
    And a coffee "mug" containing a coffee type "ARABICA"


  Scenario: A user plug the coffee machine and make a coffee
    Given a coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
    And a "cup" with a capacity of 0.15
    When I plug the machine to electricity
    And I add 1 liter of water in the water tank
    And I add 0.5 liter of "ROBUSTA" in the bean tank
    And I made a coffee "ROBUSTA"
    Then the coffee machine return a coffee mug not empty
    And a coffee volume equals to 0.15
    And a coffee "cup" containing a coffee type "ROBUSTA"


Scenario: A user tries to make a coffee with an unplugged coffee machine
  Given a coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
  And a "mug" with a capacity of 0.25
  And I add 1 liter of water in the water tank
  And I add 0.5 liter of "ROBUSTA" in the bean tank
  When I try to make a coffee "ROBUSTA"
  Then an exception "MachineNotPluggedException" is thrown


Scenario: A user tries to make a coffee with an empty water tank
  Given a coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
  And a "mug" with a capacity of 0.25
  When I plug the machine to electricity
  And I add 0.5 liter of "ARABICA" in the bean tank
  And I try to make a coffee "ARABICA"
  Then an exception "LackOfWaterInTankException" is thrown

Scenario: A user tries to make a coffee with an incompatible coffee type
  Given a coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
  And a "cup" with a capacity of 0.15
  When I plug the machine to electricity
  And I add 1 liter of water in the water tank
  And I add 0.5 liter of "ROBUSTA" in the bean tank
  And I try to make a coffee "ARABICA"
  Then an exception "CoffeeTypeCupDifferentOfCoffeeTypeTankException" is thrown



Feature: Make a coffee with an espresso coffee machine
  Scenario: A user plugs the espresso machine and makes a coffee Arabica
    Given an espresso coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
    And an espresso "mug" with a capacity of 0.25
    When I plug the espresso machine to electricity
    And I add 1 liter of water to the espresso machine tank
    And I add 0.5 liter of "ARABICA" to the espresso machine bean tank
    And I make a coffee with the espresso machine "ARABICA"
    Then the espresso machine returns a coffee "mug" not empty
    And a coffee volume equals to 0.25



  Scenario: A user prepares an espresso coffee with the espresso machine
    Given an espresso coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
    And a "mug" with a capacity of 0.25
    When I plug the espresso machine to electricity
    And I add 1 liter of water in the water tank
    And I add 0.5 liter of "ARABICA" in the bean tank
    And I made a coffee with the espresso machine "ARABICA"
    Then the espresso machine returns a coffee mug not empty
    And a coffee volume equals to 0.25
    And a coffee "mug" containing a coffee type "ARABICA"



