(ns crnkl-todomvc.hiccup
  #?(:clj (:require [hicada.compiler])
     :cljs (:require [hicada.interpreter]
                     ["react" :as react]))
  #?(:cljs (:require-macros [crnkl-todomvc.hiccup :refer [html]])))

#?(:cljs (js/goog.exportSymbol "React" react))
#?(:clj
   (defmacro html [body]
     (hicada.compiler/compile body {:create-element 'js/React.createElement
                                    :rewrite-for?   true})))
