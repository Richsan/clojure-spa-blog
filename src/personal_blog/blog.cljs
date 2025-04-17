(ns personal-blog.blog
  (:require [personal-blog.routes :as routes]
            [schema.core :as s]))

(s/defn app []
  [:div [routes/page-router]])
