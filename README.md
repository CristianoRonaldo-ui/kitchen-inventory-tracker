# Kitchen Inventory Tracker

A small Java program that tracks ingredient stock in a restaurant kitchen using a `HashMap<String, Integer>`. It supports adding stock, using stock with validation, checking quantities, and printing a low-stock report.

## Features

- **Add stock**: adding an existing ingredient accumulates its quantity instead of overwriting it
- **Use stock**: rejects non-positive amounts, unknown ingredients, and requests larger than the available quantity
- **Check quantity**: unknown ingredients return `0` instead of crashing
- **Low-stock report**: lists every ingredient below a chosen threshold
- **Manual tests**: built-in PASS/FAIL checks for the core behaviour

## Project Structure

```
kitchen-inventory-tracker/
├── .gitignore
├── README.md
└── src/
    ├── KitchenInventory.java   # Inventory logic backed by a HashMap
    └── InventoryApp.java       # Demo program and manual tests
```

## How to Run

Requires Java (JDK 17 or newer).

```bash
javac src/*.java
java -cp src InventoryApp
```

## Example Output

```
Rice: 30
Fish sauce: 5
Noodles: 0

--- Using stock ---
Rice after using 12: 18
Error: Not enough fish sauce. Available: 5, requested: 100
Error: Item not found: noodles

Low stock items (below 10):
  - fish sauce: 5

--- Running tests ---
Test 1 PASS: addStock accumulates quantity
Test 2 PASS: useStock reduces quantity
Test 3 PASS: useStock rejects overuse, stock unchanged
```

## Complexity Analysis

| Operation | Time Complexity | Why |
|---|---|---|
| `addStock` | O(1) average | One `getOrDefault` and one `put` on a HashMap |
| `useStock` | O(1) average | `containsKey`, `get`, and `put` are constant time on average |
| `getQuantity` | O(1) average | A single hash lookup |
| `printLowStockReport` | O(n) | Visits every ingredient once (n = number of ingredients) |

## What I Learned

- **HashMap is Java's version of Python's `dict`**: `put` replaces `d[key] = value`, `getOrDefault` replaces `d.get(key, default)`, and `containsKey` replaces `key in d`.
- **Generics need wrapper types**: `HashMap<String, int>` does not compile. Primitives like `int` must be written as `Integer`, and Java converts between them automatically (autoboxing).
- **`get()` can return `null`**: calling `stock.get(item) + amount` on a missing key throws a `NullPointerException`, so I used `getOrDefault` or checked `containsKey` first.
- **Order of validation matters**: `useStock` checks everything *before* changing the stock, so a failed request never leaves the inventory in a wrong state. Test 3 verifies this.
- **HashMap does not keep insertion order**: unlike Python's `dict` (3.7+), iterating over a `HashMap` may return keys in any order.
- **Static context**: `runTests()` must be `static` to be called directly from `main`.
- **Test isolation**: each test uses its own `KitchenInventory` object so tests cannot affect each other.