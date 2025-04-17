(ns personal-blog.model.blog
  (:require [schema.core :as s]))

(def article-skeleton
  {(s/required-key :id) s/Uuid
   (s/required-key :title) s/Str
   (s/required-key :post-date) s/Str
   (s/required-key :article) s/Str
   (s/required-key :summary) s/Str})

(s/defschema Article article-skeleton)