Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario Outline: Sunday isn't Friday
    Given name is <name>
    When I ask whether it's Friday yet
    Then response is "<expect>"
  Examples:
    | name | expect      |
    | ryo  | Hello, ryo. |
    | emi  | Hello, emi. |

