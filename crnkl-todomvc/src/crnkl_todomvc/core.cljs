(ns crnkl-todomvc.core
  (:require [crinkle.component :refer [RE CE] :as c]
            [crinkle.dom :as d]
            ["react-dom" :refer [render]]
            ;; [bidi.bidi :as b]
            
            [crnkl-todomvc.views :as views]
            ;; [crnkl-todomvc.router :as router]
            [crnkl-todomvc.hiccup :refer [html]]))

(enable-console-print!)

;; (defroute "/" [] (dispatch [:set-showing :all]))
;; (defroute "/:filter" [filter] (dispatch [:set-showing (keyword filter)]))

;; https://github.com/roman01la/cljs-rum-realworld-example-app/blob/master/src/conduit/router.cljs
;; https://github.com/Day8/re-frame/blob/master/examples/todomvc/src/todomvc/core.cljs
;; https://github.com/metosin/reitit/blob/master/examples/frontend/src/frontend/core.cljs

(def routes ["/" [["" :home]
                  ["/login" :login]]])

;; define your app data so that it doesn't get over-written on reload

(defn my-function-component [{:keys [foo-text] :as props}]
  (html (views/todo-app)))

(defn start []
  (render
   (CE my-function-component {:foo-text "Hello to your momma an' dems!"})
   (.. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds

  ;; (router/start! (fn [route]
  ;;                  (prn "got my route" route))
  ;;                routes)
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
