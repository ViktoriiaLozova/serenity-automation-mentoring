Pet Store

Narrative:
As a user
I want to perform next actions:
- add a new pet to the store
- find pets by status
- delete a Pet
During all these actions I want to receive 200 response code

Scenario: Pet creation with name 'Kitty/Tom' and status 'available'
Given create pet with pet.name1 and pet.status
Then check response code response.code.ok

Scenario: Find Pet by status 'available'
Given create pet with pet.name2 and pet.status
When find pet by status pet.status
Then check response code response.code.ok

Scenario: Delete Pet by current Id
Given create pet with pet.name3 and pet.status
When delete pet by current id
Then check response code response.code.ok