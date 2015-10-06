(ns schellings-model-in-clojure.model)

; Atoms that reflect the state of the GUI sliders
; These aren't good names (they probably shouldn't say
; "atom", for example) so feel free to make them better.
(def similarity-atom (atom 0))
(def balance-atom (atom 0))
(def empty-atom (atom 0))

(defn handle-neighbor-change
  "Called when the state of a neighboring position changes.
   The first argument will be the position atom that is being
   notified of the change, and the 4th and 5th arguments are
   the old and new states of the neighbor respectively. You
   might be able to use those to just directly update the red/blue
   counts for this position without requiring that it go look
   at its neighbors."
  [me key neighbor old-state new-state]
  ; You'll obviously want to replace this with some code that actually
  ; does something useful :-)
  ; If your positions contain individuals as agents, this is
  ; probably where you want to call send to handle whatever
  ; needs to be done. Otherwise everything will end up happening in
  ; the main thread.
  (println (str "My neighbor changed from " old-state " to " new-state)))

(defn make-position
  "Create a position atom that contains an individual agent, or nil
  if there's no individual there."
  (if (< (rand) @empty-atom)
    (atom nil)
    (let [color (if (< (rand) @balance-atom) :red :blue)
          individual (agent color)]
      (atom individual))))

