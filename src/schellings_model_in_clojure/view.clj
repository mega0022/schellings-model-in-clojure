(ns schellings-model-in-clojure.view
  (:require [seesaw.core :as sc]
            [seesaw.graphics :as sg]
            [seesaw.color :as scolor]
            [seesaw.table :as st]
            [seesaw.tree :as stree]))

;; A forward reference.
(def main-window)

(def world-canvas (sc/canvas :id :worldcanvas
                             :background :white))

(defn paint-world [context graphics]
  )

(defn repaint! []
  (sc/repaint! world-canvas))

(defn init-view []
  (sc/config! world-canvas :paint paint-world))

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

(def balance-slider
  (sc/slider :orientation :horizontal
             :value 0.5
             :min 0
             :max 1))

(def proportion-empty-slider
  (sc/slider :orientation :horizontal
             :value 0.1
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
   :center world-canvas
   :south controls))

(def main-window
  (sc/frame :title title
            :width (:width window-size)
            :height (:height window-size)
            :content window-content))
