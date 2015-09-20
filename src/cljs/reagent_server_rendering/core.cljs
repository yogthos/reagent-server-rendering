(ns reagent-server-rendering.core
    (:require [reagent.core :as reagent]))

(defn home-page []
  [:div [:h2 "Welcome to reagent-server-rendering"]
   [:div [:a {:href "/about"} "go to about page"]]])

(defn about-page []
  [:div [:h2 "About reagent-server-rendering"]
   [:div [:a {:href "/"} "go to the home page"]]])

(def pages
  {"/"  home-page
   "/about" about-page})

(defn ^:export render-page [uri]
  (reagent/render-to-string [(get pages uri)]))

(defn ^:export main [uri]
  (reagent/render [(get pages uri)] (.getElementById js/document "app")))

