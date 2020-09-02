## Simple Retail Banking Application
simulation of commands like
login
topup
pay

Simulation sample Result:
| Action               | Result Balances                                        |
| -------------------- | ------------------------------------------------------ |
| Bob pays Alice 50    | (150, 30)                                              |
| Bob pays Alice 100   | (180, 0) with Bob owing 70                             |
| Bob tops up 30       | (210, 0) with Bob owing 40                             |
| Alice pays 30 to Bob | (210, 0) with Bob owing 10. Debt has further decreased |
| Bob tops up 100      | (220, 90)                                              |

Commands
`
> login Alice
Hello, Alice!
Your balance is 0.

> topup 100
Your balance is 100.

> login Bob
Hello, Bob!
Your balance is 0.

> topup 80
Your balance is 80.

> pay Alice 50
Transferred 50 to Alice.
Your balance is 30.

> pay Alice 100
Transferred 30 to Alice.
Your balance is 0.
Owing 70 to Alice.

> topup 30
Transferred 30 to Alice.
Your balance is 0.
Owing 40 to Alice.

> login Alice
Hello, Alice!
Owing 40 from Bob.
Your balance is 210.

> pay Bob 30
Owing 10 from Bob.
Your balance is 210.

> login Bob
Hello, Bob!
Your balance is 0.
Owing 10 to Alice.

> topup 100
Transferred 10 to Alice.
Your balance is 90.
`