(ns little-schemer.chapter02
  (:require [little-schemer.util :refer :all]))

(defn lat? [l]
  (cond
    (empty? l) true
    (atom? (first l)) (recur (rest l))
    :else false))

(lat? '(Jack Sprat could eat no chicken fat))

(lat? '((Jack) Sprat could eat no chicken fat))

(lat? '(Jack (Sprat could) eat no chicken fat))

(lat? ())

(lat? '(bacon and eggs))

(or (empty? ()) (atom? '(d e f g)))

(or (empty? '(a b c)) (empty? ()))

(or (empty? '(a b c)) (empty? '(atom)))

(defn member? [a lat]
  (cond
    (empty? lat) false
    :else (or (= (first lat) a)
              (recur a (rest lat)))))

(member? 'tea '(coffee tea or milk))

(member? 'poached '(fried eggs and scrambled eggs))

(member? 'meat '(mashed potatoes and meat gravy))

(member? 'liver '(bagels and lox))
