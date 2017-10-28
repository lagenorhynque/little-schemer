(ns little-schemer.chapter03
  (:require [little-schemer.util :refer :all]))

(defn rember [a lat]
  (cond
    (empty? lat) ()
    :else (cond
            (= (first lat) a) (rest lat)
            :else (cons (first lat)
                        (rember a (rest lat))))))

(rember 'mint '(lamb chops and mint jelly))

(rember 'mint '(lamb chops and mint flavored mint jelly))

(rember 'toast '(bacon lettuce and tomato))

(rember 'cup '(coffee cup tea cup and hick cup))

(rember 'bacon '(bacon lettuce and tomato))

(rember 'and '(bacon lettuce and tomato))

(defn rember' [a lat]
  (cond
    (empty? lat) ()
    (= (first lat) a) (rest lat)
    :else (cons (first lat)
                (rember' a (rest lat)))))

(rember' 'and '(bacon lettuce and tomato))

(rember' 'sauce '(soy sauce and tomato sauce))

(defn firsts [l]
  (cond
    (empty? l) ()
    :else (cons (first (first l))
                (firsts (rest l)))))

(firsts '((apple peach pumpkin)
          (plum pear cherry)
          (grape raisin pea)
          (bean carrot eggplant)))

(firsts '((a b) (c d) (e f)))

(firsts ())

(firsts '((firve plums)
          (four)
          (eleven green oranges)))

(firsts '(((firve plums) four)
          (eleven grenn oranges)
          ((no) more)))

(defn insertR [new old lat]
  (cond
    (empty? lat) ()
    (= (first lat) old) (cons old
                              (cons new
                                    (rest lat)))
    :else (cons (first lat)
                (insertR new old (rest lat)))))

(insertR 'topping 'fudge '(ice cream with fudge for dessert))

(insertR 'jalapeño 'and '(tacos tamales and salsa))

(insertR 'e 'd '(a b c d f g d h))

(defn insertL [new old lat]
  (cond
    (empty? lat) ()
    (= (first lat) old) (cons new
                              (cons old
                                    (rest lat)))
    :else (cons (first lat)
                (insertL new old (rest lat)))))

(defn insertL' [new old lat]
  (cond
    (empty? lat) ()
    (= (first lat) old) (cons new
                              lat)
    :else (cons (first lat)
                (insertL' new old (rest lat)))))

(defn subst [new old lat]
  (cond
    (empty? lat) ()
    (= (first lat) old) (cons new (rest lat))
    :else (cons (first lat)
                (subst new old (rest lat)))))

(subst 'topping 'fudge '(ice cream with fudge for dessert))

(defn subst2 [new o1 o2 lat]
  (cond
    (empty? lat) ()
    (or (= (first lat) o1)
        (= (first lat) o2)) (cons new (rest lat))
    :else (cons (first lat)
                (subst2 new o1 o2 (rest lat)))))

(subst2 'vanilla 'chocolate 'banana '(banana ice creamwith chocolate topping))

(defn multirember [a lat]
  (cond
    (empty? lat) ()
    (= (first lat) a) (multirember a (rest lat))
    :else (cons (first lat)
                (multirember a (rest lat)))))

(multirember 'cup '(coffee cup tea cup and hick cup))

(defn multiinsertR [new old lat]
  (cond
    (empty? lat) ()
    (= (first lat) old) (cons old
                              (cons new
                                    (multiinsertR new old (rest lat))))
    :else (cons (first lat)
                (multiinsertR new old (rest lat)))))

(defn multiinsertL [new old lat]
  (cond
    (empty? lat) ()
    (= (first lat) old) (cons new
                              (cons old
                                    (multiinsertL new old (rest lat))))
    :else (cons (first lat)
                (multiinsertL new old (rest lat)))))

(multiinsertL 'fried 'fish '(chips and fish or fish and fried))

(defn multisubst [new old lat]
  (cond
    (empty? lat) ()
    (= (first lat) old) (cons new
                              (multisubst new old (rest lat)))
    :else (cons (first lat)
                (multisubst new old (rest lat)))))
