(ns personal-blog.pages.article
  (:require [personal-blog.fetcher :as blog.fetcher]
            [personal-blog.markdown :as md]
            [personal-blog.model.blog :as model.blog]
            [personal-blog.page-components :as page-components]
            [reagent-mui.components :as mui.comp]
            [schema.core :as s]))

(s/defn article-read [article :- model.blog/Article]
        (let [article-content (blog.fetcher/article->md-text article)]
          (fn []
            [:article
             [mui.comp/container {:fixed true}
              (md/render @article-content)]])))

(s/defn article-page [article :- model.blog/Article]
  [:div
   [page-components/header]
   [article-read article]])