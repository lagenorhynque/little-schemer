(defproject little-schemer "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-beta2"]]
  :source-paths ["src/clj" "test/clj"]
  :profiles
  {:dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]]
         :source-paths ["env/dev/clj"]}})
