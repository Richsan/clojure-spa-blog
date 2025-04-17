(ns personal-blog.page-components
  (:require [reagent-mui.components :as mui.comp]
            [reagent-mui.icons.menu :as mui.icons.menu]))

(defn header []
  [:header
   [mui.comp/box {:sx {:flex-grow 1}}
    [mui.comp/app-bar {:position "static"
                       :sx       {:min-height 128}}
     [mui.comp/toolbar
      [mui.comp/icon-button {:size       "large"
                             :edge       "start"
                             :color      "inherit"
                             :aria-label "open drawer"
                             :sx         {:display {:md "none"
                                            :lg "none"
                                            :xl "none"}}}
       [mui.icons.menu/menu]]
      [mui.comp/typography {:variant   "h6"
                            :component "div"
                            :sx        {:flex-grow 1}}
       [mui.comp/button {
                         :href "/"
                         :color      "inherit"}
        "Richsan Blog"]]]]]])