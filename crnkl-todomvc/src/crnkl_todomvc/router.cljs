(ns crnkl-todomvc.router
  (:require ;; [bidi.bidi :as bidi]
            [goog.events :as events]
            [clojure.string :as str]))


#_(defn start! [on-set-page routes]
  (letfn [(handle-route []
            (let [uri (str/replace js/location.hash "#" "")]
              (->> (if-not (empty? uri) uri "/")
                   (bidi/match-route routes)
                   on-set-page)))]
    (events/listen js/window "hashchange" handle-route)
    (handle-route)
    handle-route))


#_(defn stop! [handler]
  (events/unlisten js/window "hashchange" handler))
