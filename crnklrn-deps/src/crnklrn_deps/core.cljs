(ns crnklrn-deps.core
  (:require [crinkle.component :refer [RE CE] :as c]
              [crinkle.dom :as d]
              [react-dom :refer [render]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defn my-function-component [{:keys [foo-text] :as props}]
  ;; Return React Elements using any library (or not) you want.
  ;; Here's one way using Crinkle:
  ;; (RE "p" {} foo-text)
  ;; (RE :p {} foo-text)
  (d/div {} foo-text))

(defn start []
  (render
   (CE my-function-component {:foo-text "Hello to your momma an' dem!"})
   (.. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (prn "your momma an dem!")
  
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
