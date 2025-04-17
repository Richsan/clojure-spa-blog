(ns personal-blog.markdown
  (:require [reagent.core :as r]
            [cljs.core.async.interop :refer-macros [<p!]]
            [cljs.core.async :refer [go]]
            ["react-markdown$default" :as react.markdown]
            ["rehype" :refer [rehype]]
            ["remark-math$default" :as remark.math]
            ["rehype-katex$default" :as rehype.katex]
            ["rehype-highlight$default" :as rehype.highlight]
            ["highlight.js/lib/languages/clojure" :as clj.highlights]
            ["@uiw/react-md-editor$default" :as md-editor]))

(def react-markdown
  (r/adapt-react-class react.markdown))


(def react-markdown-editor
  (r/adapt-react-class md-editor))

(def languages-to-register
  (clj->js {:languages {:clojure clj.highlights}}))

(defn render [md-text]
  [:div
   [react-markdown
    {:class-name "markdown"
     :remarkPlugins [remark.math]
     :rehypePlugins [rehype.katex
                     (partial rehype.highlight languages-to-register)]}
    md-text]])