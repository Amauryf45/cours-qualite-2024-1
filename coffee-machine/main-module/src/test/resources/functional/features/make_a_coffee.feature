Feature: Make coffee with a coffee machine and an espresso machine
  As a user
  I want to use a coffee machine and an espresso machine
  So that I can prepare coffee with different configurations

  # Test avec la machine à café classique
  Scenario: Prepare a coffee using a regular coffee machine
    Given a coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
    And a "mug" with a capacity of 0.25
    When I plug the machine to electricity
    And I add 1 liter of water in the water tank
    And I add 0.5 liter of "ARABICA" in the bean tank
    And I made a coffee "ARABICA"
    Then the coffee machine return a coffee mug not empty
    And a coffee volume equals to 0.25
    And a coffee "mug" containing a coffee type "ARABICA"

  Scenario: Prepare a coffee using a cup with a regular coffee machine
    Given a coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
    And a "cup" with a capacity of 0.15
    When I plug the machine to electricity
    And I add 1 liter of water in the water tank
    And I add 0.5 liter of "ROBUSTA" in the bean tank
    And I made a coffee "ROBUSTA"
    Then the coffee machine return a coffee mug not empty
    And a coffee volume equals to 0.15
    And a coffee "cup" containing a coffee type "ROBUSTA"

  # Test avec la machine à expresso
  Scenario: Prepare a coffee using an espresso coffee machine and a mug
    Given an espresso coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
    And an espresso "mug" with a capacity of 0.30
    When I plug the espresso machine to electricity
    And I add 1 liter of water to the espresso machine tank
    And I add 0.5 liter of "ARABICA" to the espresso machine bean tank
    And I make a coffee with the espresso machine "ARABICA"
    Then the espresso machine returns a coffee "mug" not empty
    And a coffee volume for espresso equals to 0.30
    And a coffee "mug" containing a coffee type "ARABICA" for espresso

  Scenario: Prepare a coffee using an espresso coffee machine and a cup
    Given an espresso coffee machine with 0.10 l of min capacity, 3.0 l of max capacity, 600.0 l per h of water flow for the pump
    And an espresso "cup" with a capacity of 0.15
    When I plug the espresso machine to electricity
    And I add 1 liter of water to the espresso machine tank
    And I add 0.5 liter of "ROBUSTA" to the espresso machine bean tank
    And I make a coffee with the espresso machine "ROBUSTA"
    Then the espresso machine returns a coffee "cup" not empty
    And a coffee volume for espresso equals to 0.15
    And a coffee "cup" containing a coffee type "ROBUSTA" for espresso
