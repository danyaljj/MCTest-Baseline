# MCTest-Baseline

Sliding window baseline for MCTest [as explained in the paper](http://www.msr-waypoint.com/en-us/um/people/cburges/papers/MCTest-EMNLP13.pdf) (which can be used for any machine comprehension task, as a baseline). 
Wrote this a couple of years ago based on Chris Burges's original C# implementation; putting it here in case anyone wants to use it. 

Here are some numbers: 

| Dataset     | all data | multiple sentence | single sentence |
|-------------|----------|-------------------|-----------------|
| MC160(test) | 65.416    |    57.031         | 75.0   |
| MC500(test) | 55.66 | 54.878        | 56.617      |


## Bugs/Typos/Suggestions? 
Sure! send a PR or open an issue. 
