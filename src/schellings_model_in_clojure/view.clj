(ns schellings-model-in-clojure.view
  (:require [schellings-model-in-clojure.model :as model]
            [seesaw.core :as sc]
            [seesaw.graphics :as sg]
            [seesaw.color :as scolor]
            [seesaw.table :as st]
            [seesaw.tree :as stree]
            [seesaw.bind :as sb]))

(def number-of-individuals-on-a-side 30)
(def individual-rectangle-size 10)

(def world-canvas
  (let [side-size-in-pixels (* number-of-individuals-on-a-side individual-rectangle-size)]
    (sc/canvas :id :worldcanvas
               :background :white
               :preferred-size [side-size-in-pixels :by side-size-in-pixels])))

(defn repaint! []
  (sc/repaint! world-canvas))

(defn neighborhood [[x y]]
  (for [dx [-1 0 1]
        dy [-1 0 1]
        :let [new-x (+ x dx)
              new-y (+ y dy)]
        :when (and (not= [new-x new-y] [x y])
                   (>= new-x 0)
                   (< new-x number-of-individuals-on-a-side)
                   (>= new-y 0)
                   (< new-y number-of-individuals-on-a-side))]
    [(+ x dx) (+ y dx)]))

(defn neighbors [position board-map]
  (map board-map (neighborhood position)))

(defn make-board-map [number-of-individuals-on-a-side]
  (into {}
        (for [x (range number-of-individuals-on-a-side)
              y (range number-of-individuals-on-a-side)]
          [[x y] (model/make-position)])))

(defn make-board [number-of-individuals-on-a-side]
  (let [board-map (make-board-map number-of-individuals-on-a-side)]
    (doseq [coordinates (keys board-map)
            neighbor-coord (neighbors coordinates board-maps)
            :let [position (board-map coordinates)
                  neighbor (board-map neighbor-coord)]]
      (add-watch position :neighbor-changed
                 (partial model/handle-neighbor-change neighbor)))))

(defn init-view []
  (make-board number-of-individuals-on-a-side)
  (sc/config! world-canvas :paint paint-world))

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

(def title "Schelling's model of segregation")
(def window-size 800)

(def main-window
  (sc/frame :title title
            :width (:width window-size)
            :height (:height window-size)
            :content window-content))
