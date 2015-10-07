# schellings-model-in-clojure

A simple starter for an implementation of Schelling's model of segregation in Clojure. See, e.g., [this description](http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/) and (JavaScript) implementation for more details on the model.

The goal is to complete the implementation using Clojure's concurrency tools to implement each "person" in the model so they act asynchronously.

## Getting started

Fork this repository and:

 - Implement some way of reading the settings of the various sliders so their values are accessible to the "people" in the model. (We could have each "person" query the sliders directly when they need a value, but for the purposes of the assignment I'd rather you have a listener on the sliders that stores the values in an appropriate Clojure entity that the "people" can then access.)
 - Implement the function assigned to the listeners on the Start/Stop button and the Reset button. The start button should cause all the people to begin acting (asynchronously), and the stop button should stop them all. The reset button should stop everything, and repopulate the board with a new set of random "people" based on the values of the appropriate sliders. (There's a function that does almost all of that; you just need to stop everybody and then call that.)

## Usage

You should be able to run the simulation with

    $ lein run

in the project directory.

## License

Copyright © 2015 Nic McPhee & other authors

Distributed under the MIT License.
