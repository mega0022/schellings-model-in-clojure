(ns schellings-model-in-clojure.core
  (:require
   [seesaw.core :as sc]
   [schellings-model-in-clojure.view :as view]
   [schellings-model-in-clojure.controller :as controller])
  (:gen-class))

(defn show-gui []
  (sc/native!)
  (sc/show! (sc/pack! view/main-window))
  (view/init-view)
  (view/repaint!)
  (controller/setup-listeners)
  )

(defn -main
  "Start up the simulation of Schelling's model"
  [& args]
  (show-gui))
