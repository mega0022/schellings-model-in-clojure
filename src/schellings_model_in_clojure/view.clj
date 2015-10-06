(ns schellings-model-in-clojure.view
  (:require [schellings-model-in-clojure.model :as model]
            [seesaw.core :as sc]
            [seesaw.graphics :as sg]
            [seesaw.color :as scolor]
            [seesaw.table :as st]
            [seesaw.tree :as stree]
            [seesaw.bind :as sb]))

;; A forward reference.
(def main-window)

(def world-canvas (sc/canvas :id :worldcanvas
                             :background :white
                             :preferred-size [300 :by 300]))

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

(defn make-slider [slider-label initial-value slider-atom]
  (let [title-label (sc/label :text (str slider-label ": "))
        value-label (sc/label :text initial-value)
        slider (sc/slider :orientation :horizontal
                          :value initial-value
                          :min 0
                          :max 100)]
    (sb/bind slider (sb/transform / 100.0) slider-atom)
    (sb/bind slider-atom (sb/transform #(Math/round (* 100.0 %))) value-label)
    (sc/vertical-panel
     :items [(sc/horizontal-panel :items [title-label value-label])
             slider])))

(def sliders
  (sc/vertical-panel
   :items (map (partial apply make-slider)
               [["Similarity tolerance" 30 model/similarity-atom]
                ["Relative proportions" 50 model/balance-atom]
                ["Proportion empty" 10 model/empty-atom]])))

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
