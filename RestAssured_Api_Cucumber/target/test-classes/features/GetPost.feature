Feature:
  Verify different GET operation using REST-Assured

  Scenario: Verify one author of the post
    Given I perform GET operation for "/post"
    And   I perform GET for the post number "2"
    Then  I should see the author name as "Kader"



  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/posts"
    Then  I should see the author names


  Scenario: Verify Parameter of Get
    Given I perform GET operation for "/posts"
    Then  i should see Verify GET Parameter



