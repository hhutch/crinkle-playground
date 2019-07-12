(ns crnklrn-deps.hiccup
  #?(:clj (:require [hicada.compiler])
     :cljs (:require [hicada.interpreter]
                     ["react" :as react]))
  #?(:cljs (:require-macros [crnklrn-deps.hiccup :refer [html]])))

#?(:cljs (js/goog.exportSymbol "React" react))
#?(:clj
   (defmacro html [body]
     (hicada.compiler/compile body {:create-element 'js/React.createElement
                                    :rewrite-for?   true
                                    #_:interpret-or-inline-fn #_(fn [expr]
                                                                  (cond
                                                                    ;;By convention in my codebase, all react components are prefixed with *, so if we're invoking one of those we know we can inline and don't need to interpret
                                                                    (and (list? expr)
                                                                      (.startsWith (name (first expr)) "*"))
                                                                    :inline))})))
