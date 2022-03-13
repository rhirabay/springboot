Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday
    Given name is ryo
    When I ask whether it's Friday yet
    Then response is:
      """
      Hello, ryo.
      """
    And name is emi
    Then response is:
      """
      Hello, emi.
      """
