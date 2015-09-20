(ns reagent-server-rendering.cache
  (:require [clojure.core.cache :as cc]))

(defn invalidate!
  "removes the id and the content associated with it from cache"
  [cache id]
  (swap! cache cc/evict id))

(defmacro cached!
  "checks if there is a cached copy of the expression available,
  if so the cached version is returned, otherwise the expression
  is evaluated"
  [cache id expr]
  `(-> (swap! ~cache update ~id
                (fn [item#]
                  (or item# (try ~expr (catch Exception ex#)))))
         (get ~id)))

(defn create-cache [& [size]]
  (atom (cc/lru-cache-factory {} :threshold (or size 32))))
