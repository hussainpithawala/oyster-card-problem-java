## Oyster Card Problem

### Problem

Model the following fare card system which is a limited version of London’s Oyster card system.

A user should be able to load a card with £30, and taking the following trips, and then viewing the balance.
- Tube Holborn to Earl’s Court
- 328 bus from Earl’s Court to Chelsea
- Tube Earl’s court to Hammersmith
  
Operation:

  When the user passes through the inward barrier at the station, their oyster card is charged
  the maximum fare. When they pass out of the barrier at the exit station, the fare is calculated and the maximum
  fare transaction removed and replaced with the real transaction (in this way, if the user doesn’t swipe out, 
  they are charged the maximum fare).
  
  All bus journeys are charged at the same price.
  The system should favour the customer where more than one fare is possible for a given
  journey. E.g. Holburn to Earl’s Court is charged at £2.50.
  
For the purposes of this problem use the following data:

  #### Stations and zones:

  **Station Zone(s):**
  * Holborn 1
  * Earl’s Court 1, 2
  * Wimbledon 3
  * Hammersmith 2

  Fares:

  **Journey Fares:**

  * Anywhere in Zone 1 £2.50
  * Any one zone outside zone 1 £2.00
  * Any two zones including zone 1 £3.00
  * Any two zones excluding zone 1 £2.25
  * Any three zones £3.20
  * Any bus journey £1.80
  * The maximum possible fare is therefore £3.20.

### Solution

- #### Logical flow for card application
  Following is the logical flow
  - Prepare an instance of RuleContext. This ruleContext contains all the rules which are used to 
  compute the fares between any two stations.
  - Set a maximum fare between any two stations.
  - Start writing the rules.
    - There is a fare amount associated with a rule.
    - A rule involves supplying all the combinations which qualify for rule execution
      - Provide a start-zone and end-zone in Combination's constructor and add this combination to the rule.
      - While a rule would be evaluated all possible combinations of this rule would be tested against the given journey
        and if any of them apply the rule would be deemed applicable
  - Load the rule (after adding any combinations to it) to the ruleContext
  - RuleContext is in turn used by FareCalculator to calculate the fare for a Journey.
  - A Journey is travel between two stations using a mode (Tube or Bus)
  - A card is used to keep track of the journey.
  - When swiped, it is checked whether a journey is in progress or not
    - In case, a journey is not in progress. We prepare an instance of journey and associate it with this card.
        - Here card is charged for the maximum fare.
  - When swiped again, and a journey is in progress. 
    - Current station is set as end station of the journey.
    - Card is credited back with the maximum amount charged.
    - For this journey a fareAmount is calculated on the basis of applied rules.
    - Card is charged for this fareAmount        

### Example
Complete example is available in AppTest.java class. Please refer to it.

### Build
./gradlew build

### Running test suite
./gradlew test
