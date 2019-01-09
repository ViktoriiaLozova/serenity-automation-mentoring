Advanced:
Extend current test LookupADefinition.story:
Add new step which uses tabular parameters (table as parameter, ExamplesTable type)
Create DDT scenario using `Examples:`
Add meta tag @ignore for some Scenario. Run tests using jbehave metafilter and ignore tagged scenario.

Lookup a definition
Narrative:
In order to talk better
As an English student
I want to look up word definitions

Scenario: Looking up the definition of 'apple'
Given the user is on the Wikionary home page
When the user looks up the definition of the word 'apple'
Then they should see the definition 'A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.'

Scenario: Looking up the definition of 'pear'
Meta:
@ignore
Given the user is on the Wikionary home page
When the user looks up the definition of the word 'pear'
Then they should see the definition 'An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.'

Scenario: Looking up the definition of words list 'student', 'table', 'desk'
Given the user is on the Wikionary home page
When the user looks up the definition of the word list <word>
Then they should see the definition list <definition>
Examples:
|word   |definition                                                                                             |
|student|A person who studies or learns about a particular subject.                                             |
|table  |Furniture with a top surface to accommodate a variety of uses.                                         |
|desk   |A table, frame, or case, in past centuries usually with a sloping top but now usually with a flat top  |