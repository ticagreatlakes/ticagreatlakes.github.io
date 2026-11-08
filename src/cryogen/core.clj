(ns cryogen.core
  (:require [cryogen-core.compiler :refer [compile-assets-timed]]
            [cryogen-core.plugins :refer [load-plugins]]))

(defn extender [params site-data]
  (let [future-shows (take-while #(-> % :date (.after (java.util.Date.)))
                                 (-> site-data :posts))]
    (assoc params :next-show (last future-shows))))

(def extra-config {:extend-params-fn extender})

(defn -main []
  (load-plugins)
  (compile-assets-timed extra-config)
  (System/exit 0))
