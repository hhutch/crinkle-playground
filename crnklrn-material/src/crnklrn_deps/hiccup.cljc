(ns crnklrn-deps.hiccup
  #?(:clj  (:require [hicada.compiler])
     :cljs (:require [hicada.interpreter]
                     ["react" :as react]
                     [crinkle.component :as c]))
  #?(:cljs (:require-macros [crnklrn-deps.hiccup :refer [html]])))

#?(:cljs (js/goog.exportSymbol "React" react))
#?(:clj
   (defmacro html [body]
     (hicada.compiler/compile
       body
       {:create-element 'js/React.createElement
        :rewrite-for?   true
        :emit-fn        (fn [tag attr children]
                          ;; Now handle the emitter case:
                          (if (and (seq? tag) (= 'crinkle.component/create-element-raw-props (first tag)))
                            tag
                            (list* 'js/React.createElement tag attr children)))
        #_:interpret-or-inline-fn #_(fn [expr]
                                      (cond
                                        ;;By convention in my codebase, all react components are prefixed with *, so if we're invoking one of those we know we can inline and don't need to interpret
                                        (and (list? expr)
                                          (.startsWith (name (first expr)) "*"))
                                        :inline))}
       {:CE (fn [_ klass attrs & children]
              (assert (even? (count children)))
              (let [{:keys [ref key]} (into {} (partition-all 2) children)]
                [(list 'crinkle.component/create-element-raw-props klass attrs key ref)]))})))

