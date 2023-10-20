# Trapped Material Task

## Problem Description

This project provides a solution for calculating the amount of trapped material between the walls formed by terrain heights and holes on a spaceship.
The core problem involves determining the volume of trapped material between the heights of the terrain while considering negative values as holes in the terrain.

## Implementation

The implementation is provided in the `CalculateTrappedMaterial` file. The `material` function calculates the volume of trapped material based on the given integer array of terrain heights and holes. This implementation correctly accounts for negative values in the terrain, treating them as holes.

## Complexity

The complexity of the solution can be analyzed as follows:

- **Time Complexity**: The algorithm operates in linear time, O(N), where N is the length of the input `spaceship` array. The algorithm iterates through the array once, using two pointers.

- **Space Complexity**: The space complexity of the algorithm is constant, O(1). The algorithm uses a fixed amount of additional memory to store variables for the left and right pointers, leftMax, rightMax, and the result (trappedMaterial).



The complexity of the problem itself remains O(N) because it's necessary to examine every element in the input array to calculate the trapped material accurately.

## Running the Tests

The test suite for this project is organized in the `test` directory. You can run the tests to verify the correctness of the implementation. The tests cover various scenarios, including flat terrain, increasing terrain, decreasing terrain, empty terrain, and custom data. 



