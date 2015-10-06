(ns schellings-model-in-clojure.model)

(defn make-individuals [max-x max-y watcher-fn]
  (for [x (range max-x)
        y (range max-y)]
    (let [individual (agent {})
          color (if (< (rand) 0.5) :red :blue)]
      (add-watch individual :canvas watcher-fn)
      (send individual assoc :position [x y] :color color))))

