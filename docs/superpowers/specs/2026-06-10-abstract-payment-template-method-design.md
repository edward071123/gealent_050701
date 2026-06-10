# Abstract Payment Template Method Design

## Goal

Refactor `src/junior/abstractAndInterface2/abstractExample` so the abstract
class demonstrates where inheritance is useful:

- shared fields
- a shared payment workflow
- shared method implementations
- protected extension points for subclasses

## Design

`APaymentService` owns the payment template. Its public `pay(int amount)`
method is `final` so every payment type executes the same steps:

1. validate the amount
2. print the start of the payment
3. delegate provider-specific behavior to `executePayment(int amount)`
4. print the successful result

The class stores a shared `paymentName` field initialized by a protected
constructor. Common validation and output remain private because subclasses
do not need to replace them.

`executePayment(int amount)` is a protected abstract method. Credit card and
LinePay subclasses implement only their provider-specific processing message.

## Order Flow

`AOrderService` receives an `APaymentService` through constructor injection and
stores it in a final field. Construction has no payment side effects.

The explicit `createOrder(int amount)` method prints the order creation message
and then calls the selected payment service. The caller controls the amount.

## Error Handling

`APaymentService.pay` rejects amounts less than or equal to zero with
`IllegalArgumentException`. Provider-specific processing does not run when
validation fails.

## Verification

Add a small Java test class that does not require an external test framework.
It will verify:

- credit card payment follows the shared workflow
- LinePay payment follows the shared workflow
- zero or negative amounts are rejected before provider processing
- creating an order service does not immediately charge
- `createOrder` passes the requested amount to the payment service

Compile all files under `abstractAndInterface2` with `javac`, run the test
class, and run `Demo` to confirm the teaching example output.

## Scope

The interface example remains unchanged. The refactor is limited to the
abstract payment example and the abstract section of `Demo`.
