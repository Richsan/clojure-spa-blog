(ns personal-blog.pages.home
  (:require [personal-blog.page-components :as view-components]
            [reagent-mui.components :as mui.comp]))

(defn about-page []
  [:div "abou-page"])

(defn article-preview [{:keys [id title summary] :as article}]
  [:div
   [mui.comp/card {:sx {:max-width 1000}}
    [mui.comp/card-content
     [mui.comp/typography {:variant   "h5"
                           :component "div"}
      title]
     [mui.comp/typography {:variant "body2"
                           :sx      {:color "text.secondary"}}
      summary]
     [mui.comp/card-actions
      [mui.comp/button {:size "small"}
       "Share"]
      [mui.comp/button {:size "small" :href (str "/articles/ptbr/" id)}
       "Learn"]]]]])

(defn articles-feed
  [articles]
  [:div
   [mui.comp/stack {:spacing     2
                    :align-items "center"
                    :sx          {:margin-top  2}}
    (for [article articles]
      [article-preview article])]])

(defn home-page [articles]
  [:div
   [view-components/header]
   [articles-feed articles]])