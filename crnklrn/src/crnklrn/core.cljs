(ns crnklrn.core
    (:require [crinkle.component :refer [RE CE] :as c]
              [crinkle.dom :as d]
              [react-dom :refer [render]]))

(enable-console-print!)

(println "This text is printed from src/crnklrn/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defn my-function-component [{:keys [foo-text] :as props}]
  ;; Return React Elements using any library (or not) you want.
  ;; Here's one way using Crinkle:
  ;; (RE "p" {} foo-text)
  ;; (RE :p {} foo-text)
  (d/div {} foo-text))

;; Note: no wrappers or component factories! 
(render
 (CE my-function-component {:foo-text "Hello World!"})
 (.. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)


  )
;;


;; todo mvc, hooks, use-reducer
;; https://github.com/Day8/re-frame/tree/master/examples/todomvc

;; https://kevinlynagh.com/notes/fast-cljs-react-templates/
