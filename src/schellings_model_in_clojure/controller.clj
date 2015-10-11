(ns schellings-model-in-clojure.controller
  (:require [seesaw.core :as sc]
            [seesaw.graphics :as sg]
            [seesaw.color :as scolor]
            [seesaw.table :as st]
            [seesaw.tree :as stree]
            [schellings-model-in-clojure.view :as view]))

;;; It would be good to implement "interesting" actions
;;; for these buttons, but it's not the priority of the
;;; project. It's worth thinking, though, about how you'd
;;; stop/pause all these asynchronous agents since that's
;;; definitely more complicated than it would be if we had
;;; a synchronous model.
(defn reset-world [evnt])
(defn toggle-start-stop [evnt])

;;; It's possible that all the binding and watcher
;;; stuff should have happened here, but I'm not
;;; going to wrestle with that now.
(defn setup-listeners []
  (sc/listen view/reset-button :action reset-world)
  (sc/listen view/start-stop-button :action toggle-start-stop)
  )
