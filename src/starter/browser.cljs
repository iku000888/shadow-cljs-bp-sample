(ns starter.browser
  (:require [reagent.core :as reagent]
            ["@blueprintjs/core" :as bp-core]))

(def button
  (reagent/adapt-react-class bp-core/Button))

(def popover
  (reagent/adapt-react-class bp-core/Popover))

(defn app []
  [:div "hello world"
   [popover
    {:content (reagent/as-element [:div "I am a blueprint popover"])
     :target (reagent/as-element [button "foo"])}]])

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (reagent/render app
                  (js/document.getElementById "app"))
  (js/console.log "start"))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))
