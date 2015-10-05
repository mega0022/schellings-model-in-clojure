(ns schellings-model-in-clojure.view
  (:require [seesaw.core :as sc]
            [seesaw.graphics :as sg]
            [seesaw.color :as scolor]
            [seesaw.table :as st]
            [seesaw.tree :as stree]))

;; A forward reference.
(def main-window)

(defn repaint []
  )

(defn init-view []
  )

(def title "Schelling's model of segregation")
(def window-size 800)

(def reset-button
  (sc/button :text "Reset"))
(def start-stop-button
  (sc/button :text "Start"))

(def buttons
  (sc/vertical-panel
   :items [reset-button start-stop-button]))

(def similarity-slider
  (sc/slider :orientation :horizontal
             :value 0.3
             :min 0
             :max 1))

(def sliders
  (sc/vertical-panel
   :items [similarity-slider balance-slider proportion-empty-slider]))

(def controls
  (sc/horizontal-panel
   :items [buttons sliders]))

(def window-content
  (sc/border-panel
   :center world
   :south controls))

(def main-window
  (sc/frame :title title
            :width (:width window-size)
            :height (:height window-size)
            :content window-content))
