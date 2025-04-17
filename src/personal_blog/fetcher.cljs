(ns personal-blog.fetcher
  (:require [personal-blog.model.blog :as model.blog]
            [cljs-http.client :as http]
            [reagent.core :as r]
            [cljs.core.async :refer [<! go]]
            [schema.core :as s]))


(def blog-articles
  (let [result (r/atom nil)
                         _ (go (->> (<! (http/get "/articles.edn"))
                                    :body
                                    (reset! result)))]
                     result))

(s/defn article->md-text [{:keys [article]} :- model.blog/Article]
        (let [a (r/atom "")]
          (go (let [md-str (-> (<! (http/get (str "/posts/" article)))
                               :body)]
                (reset! a md-str)
                ))
          a))