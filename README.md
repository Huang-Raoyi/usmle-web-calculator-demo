# usmle-web-calculator-demo

## Data model

### Critical features with z-score normalization
* step1Score
* step1Failure
* step2Score
* step2Failure
* step2CSFailure
* step3
* step3Failure
* visaNeeded
  * 1 -> True
  * 0 -> False
* YOG - year of graduation
* priorResidency (case-insensitive)
  * yes -> True
  * no -> False
* researchExperience
* clinicalExperience
* papers
* speciality
  * Family Medicine -> [1, 0, 0, 0, 0, 0]
  * Internal Medicine -> [0, 1, 0, 0, 0, 0]
  * Neurology -> [0, 0, 1, 0, 0, 0]
  * Pathology -> [0, 0, 0, 1, 0, 0]
  * Pediatrics -> [0, 0, 0, 0, 1, 0]
  * Psychiatry -> [0, 0, 0, 0, 0, 1]

### Quasi-Binomial Regression
* `probability / (1 - probability) = X.T @ coefficients` (intercept is included)
* `probability = 1 / (1 + X.T @ coefficients)`


## Web demo
<img width="169" alt="Screenshot 2023-08-25 at 10 14 18 AM" src="https://github.com/Huang-Raoyi/usmle-web-calculator-demo/assets/81523429/e5a90d84-a53d-482e-b08c-2466ecd84ec8">
<img width="241" alt="Screenshot 2023-08-25 at 10 15 08 AM" src="https://github.com/Huang-Raoyi/usmle-web-calculator-demo/assets/81523429/591e785a-1947-48ef-a9c2-3b408afd0f63">
