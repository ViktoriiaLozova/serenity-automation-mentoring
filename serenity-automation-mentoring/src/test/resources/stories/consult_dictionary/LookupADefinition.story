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

Scenario: Looking up the definition of 'searchCriteria'
Given the user is on the Wikionary home page
When the user looks up the definition of the word 'wiki.searchCriteria1'
Then they should see the definition 'wiki.definition1'

Scenario: Looking up the definition of 'searchCriteria'
Meta:
@ignore
Given the user is on the Wikionary home page
When the user looks up the definition of the word 'wiki.searchCriteria2'
Then they should see the definition 'wiki.definition2'

Scenario: Looking up the definition of 'pear' and 'apple'
Given the user is on the Wikionary home page
When the user looks up the definition of the word in table
|word |definition                                                                                    |
|pear |An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.|
|apple|A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates. |
Then they should see the definition in table

Scenario: Looking up the definition of words list 'student', 'table', 'desk'
Given the user is on the Wikionary home page
When the user looks up the definition of the word list <word>
Then they should see the definition list <definition>
Examples:
|word   |definition                                                                                             |
|student|A person who studies or learns about a particular subject.                                             |
|table  |Furniture with a top surface to accommodate a variety of uses.                                         |
|desk   |A table, frame, or case, in past centuries usually with a sloping top but now usually with a flat top  |