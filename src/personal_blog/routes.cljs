(ns personal-blog.routes
  (:require [reagent.core :as r]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.coercion.schema :as rcs]
            [personal-blog.pages.home :as pages.home]
            [personal-blog.pages.article :as pages.article]
            [personal-blog.fetcher :as blog.fetcher]
            [schema.core :as s]))

(defonce match (r/atom nil))

(def routes
  [["/about"
    {:name ::about
     :view pages.home/about-page}]
   ["/articles/ptbr/:id"
    {:name ::article
     :view pages.article/article-page
     :parameters {:path {:id s/Uuid}}}]])

(defn  page-router []
  (let [{:keys [data parameters]} @match
        {:keys [name view] :as route-data} data
        articles @blog.fetcher/blog-articles]
    (cond
      (= ::article name)
      (let [article-id (-> parameters :path :id)
            article (first (filter (comp #(= % article-id) :id) articles))]
        [view article])

      (some? view) [view route-data]

      :else [pages.home/home-page articles])))

(defn router-start! []
  (rfe/start!
    (rf/router routes {:data {:coercion rcs/coercion}})
    (fn [m] (reset! match m))
    {:use-fragment false}))