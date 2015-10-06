(ns schellings-model-in-clojure.model)

; Atoms that reflect the state of the GUI sliders
(def similarity-atom (atom 0))
(def balance-atom (atom 0))
(def empty-atom (atom 0))

(defn make-individuals [max-x max-y watcher-fn]
  (for [x (range max-x)
        y (range max-y)]
    (let [individual (agent {})
          color (if (< (rand) 0.5) :red :blue)]
      (add-watch individual :canvas watcher-fn)
      (send individual assoc :position [x y] :color color))))

