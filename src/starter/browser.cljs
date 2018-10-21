(ns starter.browser
  (:require [reagent.core :as reagent]
            ["@blueprintjs/core" :as bp-core]
            ["@blueprintjs/table" :as bp-table]))

(def button (reagent/adapt-react-class bp-core/Button))

(def popover (reagent/adapt-react-class bp-core/Popover))

(def table-data (into [] (map vec (partition 4 (range 40)))))

(def table (reagent/adapt-react-class bp-table/Table))

(def column (reagent/adapt-react-class bp-table/Column))

(def cell (reagent/adapt-react-class bp-table/Cell))

(defn app []
  [:div
   [popover
    {:content (reagent/as-element [:div "I am a blueprint popover"])
     :target (reagent/as-element [button "foo"])}]
   [:div
    (let [cr #(reagent/as-element [cell (get-in table-data [%1 %2])])]
      [table {:numRows 10}
       [column {:name "foo" :cellRenderer cr}]
       [column {:name "bar" :cellRenderer cr}]
       [column {:name "baz" :cellRenderer cr}]
       [column {:name "quz" :cellRenderer cr}]])]])

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
