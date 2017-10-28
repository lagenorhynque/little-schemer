(ns little-schemer.chapter04
  (:require [little-schemer.util :refer :all]))

(atom? 14)

(inc 67)

(dec 5)

(dec 0)

(zero? 0)

(zero? 1492)

(defn o+ [n m]
  (cond
    (zero? m) n
    :else (inc (o+ n (dec m)))))

(o+ 46 12)

(defn o- [n m]
  (cond
    (zero? m) n
    :else (dec (o- n (dec m)))))

(o- 14 3)

(o- 17 9)

(o- 18 25)

(defn addtup [tup]
  (cond
    (empty? tup) 0
    :else (o+ (first tup)
              (addtup (rest tup)))))

(addtup '(3 5 2 8))

(addtup '(15 6 7 12 3))

(defn o* [n m]
  (cond
    (zero? m) 0
    :else (o+ n (o* n (dec m)))))

(o* 5 3)

(o* 13 4)

(o* 12 3)

(defn tup+ [tup1 tup2]
  (cond
    (and (empty? tup1)
         (empty? tup2)) ()
    :else (cons (o+ (first tup1) (first tup2))
                (tup+ (rest tup1) (rest tup2)))))

(tup+ '(3 6 9 11 4) '(8 5 2 0 7))

(tup+ '(2 3) '(4 6))

(tup+ '(3 7) '(4 6))

(defn tup+' [tup1 tup2]
  (cond
    (empty? tup1) tup2
    (empty? tup2) tup1
    :else (cons (o+ (first tup1) (first tup2))
                (tup+' (rest tup1) (rest tup2)))))

(tup+' '(3 7) '(4 6 8 1))

(tup+' '(3 7 8 1) '(4 6))

(defn >' [n m]
  (cond
    (zero? n) false
    (zero? m) true
    :else (>' (dec n) (dec m))))

(>' 12 133)

(>' 120 11)

(>' 3 3)

(defn <' [n m]
  (cond
    (zero? m) false
    (zero? n) true
    :else (<' (dec n) (dec m))))

(<' 4 6)

(<' 8 3)

(<' 6 6)

(defn =' [n m]
  (cond
    (zero? m) (zero? n)
    (zero? n) false
    :else (=' (dec n) (dec m))))

(defn ='' [n m]
  (cond
    (>' n m) false
    (<' n m) false
    :else true))

(defn ↑ [n m]
  (cond
    (zero? m) 1
    :else (o* n (↑ n (dec m)))))

(↑ 1 1)

(↑ 2 3)

(↑ 5 3)

(defn ÷ [n m]
  (cond
    (<' n m) 0
    :else (inc (÷ (o- n m) m))))

(÷ 15 4)

(defn length [lat]
  (cond
    (empty? lat) 0
    :else (inc (length (rest lat)))))

(length '(hotdogs with mustard saurkraut and pickles))

(length '(ham and cheese on rye))

(defn pick [n lat]
  (cond
    (zero? (dec n)) (first lat)
    :else (recur (dec n) (rest lat))))

(pick 4 '(lasagna spaghetti ravioli mararoni meatball))

;; (pick 0 '(a))

(defn rempick [n lat]
  (cond
    (zero? (dec n)) (rest lat)
    :else (cons (first lat)
                (rempick (dec n) (rest lat)))))

(rempick 3 '(hotdogs with hot mustard))

(number? 'tomato)

(number? 76)

(defn no-nums [lat]
  (cond
    (empty? lat) ()
    (number? (first lat)) (no-nums (rest lat))
    :else (cons (first lat)
                (no-nums (rest lat)))))

(no-nums '(5 pears 6 prunes 9 dates))

(defn all-nums [lat]
  (cond
    (empty? lat) ()
    (number? (first lat)) (cons (first lat)
                                (all-nums (rest lat)))
    :else (all-nums (rest lat))))

(defn eqan? [a1 a2]
  (cond
    (and (number? a1)
         (number? a2)) (=' a1 a2)
    (or (number? a1)
        (number? a2)) false
    :else (= a1 a2)))

(defn occur [a lat]
  (cond
    (empty? lat) 0
    (= (first lat) a) (inc (occur a (rest lat)))
    :else (occur a (rest lat))))

(defn one? [n]
  (=' n 1))

(defn rempick' [n lat]
  (cond
    (one? n) (rest lat)
    :else (cons (first lat)
                (rempick' (dec n) (rest lat)))))

(rempick' 3 '(lemon meringue salty pie))
