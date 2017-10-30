(ns little-schemer.chapter06
  (:require [little-schemer.chapter02 :refer [lat?]]
            [little-schemer.chapter04 :refer [o+ o* ↑]]
            [little-schemer.util :refer :all]))

(quote a)

(quote +)

(quote *)

(= (quote a) 'a)

(= 'a 'a)

(defn numbered? [aexp]
  (cond
    (atom? aexp) (number? aexp)
    :else (and (numbered? (first aexp))
               (numbered? (first (rest (rest aexp)))))))

(numbered? 1)

(numbered? '(3 + (4 ↑ 5)))

(numbered? '(2 * sausage))

(defn value [nexp]
  (cond
    (atom? nexp) nexp

    (= (first (rest nexp)) (quote +))
    (o+ (value (first nexp))
        (value (first (rest (rest nexp)))))

    (= (first (rest nexp)) (quote *))
    (o* (value (first nexp))
        (value (first (rest (rest nexp)))))

    :else (↑ (value (first nexp))
             (value (first (rest (rest nexp)))))))

(value 13)

(value '(1 + 3))

(value '(1 + (3 ↑ 4)))

(value 'cookie)

(defn first-sub-exp [aexp]
  (first (rest aexp)))

(defn second-sub-exp [aexp]
  (first (rest (rest aexp))))

(defn operator [aexp]
  (first aexp))

(defn value' [nexp]
  (cond
    (atom? nexp) nexp

    (= (operator nexp) (quote +))
    (o+ (value (first-sub-exp nexp))
        (value (second-sub-exp nexp)))

    (= (operator nexp) (quote *))
    (o* (value (first-sub-exp nexp))
        (value (second-sub-exp nexp)))

    :else (↑ (value (first-sub-exp nexp))
             (value (first-sub-exp nexp)))))

(defn first-sub-exp' [aexp]
  (first aexp))

(defn operator' [aexp]
  (first (rest aexp)))

(defn sero? [n]
  (empty? n))

(defn edd1 [n]
  (cons () n))

(defn zub1 [n]
  (rest n))

;; (zub1 ())

(defn o+' [n m]
  (cond
    (sero? m) n
    :else (edd1 (o+' n (zub1 m)))))

(lat? '(1 2 3))

(lat? '((()) (() ()) (() () ())))
