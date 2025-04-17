(ns personal-blog.core
  (:require [reagent.core :as r]
            [reagent.dom :as r.dom]
            [personal-blog.blog :as blog]
            [personal-blog.routes :as blog.routes]
            [personal-blog.markdown :as md]
            [reagent-mui.styles :as mui.styles]))

(defn editor []
  (let [md-val-test (r/atom "# Markdown Editor\n\n---\n\n**Hello world!!!**\n\n[![](https://avatars.githubusercontent.com/u/1680273?s=80&v=4)](https://avatars.githubusercontent.com/u/1680273?v=4)\n\n\\`\\`\\`javascript\nimport React from \"react\";\nimport ReactDOM from \"react-dom\";\nimport MEDitor from '@uiw/react-md-editor';\n")]
    (fn []
      [:div
       [md/react-markdown-editor {:value @md-val-test :on-change (fn [val] (r/rswap! md-val-test (constantly val)))}]]
      )))

(def theme
  (mui.styles/create-theme
    {:palette
     {:mode "light"
      :primary
      {:main "#374639"}
      :secondary
      {:main "#f50057"}
      :background
      {:default "#dcdcca"
       :paper "#f5f0f0"}}}))

(defn mount-root []
  (r.dom/render [mui.styles/theme-provider
                 theme
                 [blog/app]] (.getElementById js/document "app")))


(defn ^:export init! []
  (blog.routes/router-start!)
  (mount-root))
