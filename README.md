# ENM320 Production and Operations Planning II & BİM208 Computer Programming IV Term Project

## Overview

This project aims to develop a software solution to determine the optimal lot size (𝑄) and reorder point (𝑅) for given data. The software requests various cost and demand parameters, calculates holding costs, and uses these inputs to determine key decision variables and performance measures. The calculations leverage the Z-Chart and Loss Function values to iteratively find the optimal solution.

## Features

- **Input Collection**: Requests costs (unit cost, ordering cost, penalty cost), interest rate, lead time, lead time demand, and lead time standard deviation from the user.
- **Annual Demand Calculation**: Computes annual demand based on lead time and demand.
- **Z-Chart and Loss Function Integration**: Automatically looks up normal distribution and loss function values during the iteration process.
- **Output Decision Variables and Performance Measures**:
  - Optimal lot size (𝑄)
  - Reorder point (𝑅)
  - Number of iterations
  - Safety stock
  - Average annual holding, setup, and penalty costs
  - Average time between order placements
  - Proportion of order cycles with no stockout
  - Proportion of unmet demands

## Installation and Usage

### Prerequisites

- Java Development Kit (JDK) installed on your system
- A Java IDE (e.g., IntelliJ IDEA, VSCode) or a text editor
- Command-line interface (CLI) for running Java programs

### Instructions

1. **Clone the Repository**:
 ```bash
 git clone https://github.com/gizemtuguz/ENM320-BIM208-Term-Project.git
 cd ENM320-BIM208-Term-Project
 ```
   
2. **Compile the Java Program**:

 ```bash
javac src/Calculation.java
 ```

3. **Run the Java Program**:

 ```bash
java -cp src Calculation
 ```

## Input Values:
The program will prompt you to enter the following values:

- Unit cost (c)
- Interest rate (i)
- Penalty cost (p)
- Ordering cost (K)
- Lead time (T)
- Mean demand (mean)
- Standard deviation of demand (dev)

## View Results:
The program will output the optimal order quantity, reorder point, safety stock, and other performance measures.

## Example
### Input
 ```bash
Enter unit cost (c): 20
Enter interest rate (i): 0.25
Enter penalty cost (p): 20
Enter ordering cost (K): 100
Enter lead time (T): 4
Enter mean demand (mean): 500
Enter standard deviation of demand (dev): 100
 ```
### Output
 ```bash
Initial F Value: 0.8847
Optimal Order Quantity: 173.21
Reorder Point: 600.0
Safety Stock: 100.0
 ```
## File Structure
- **src/Calculation.java:** The main Java file containing the logic for the calculations.
- **src/excel.csv:** The CSV file with Z-Chart and Loss Function values used in the iterative process.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
