(ns little-schemer.chapter05
  (:require [little-schemer.chapter02 :refer [lat?]]
            [little-schemer.chapter04 :refer [eqan? o+]]
            [little-schemer.util :refer :all]))

(defn rember* [a l]
  (cond
    (empty? l) ()

    (atom? (first l))
    (cond
      (= (first l) a) (rember* a (rest l))
      :else (cons (first l)
                  (rember* a (rest l))))

    :else (cons (rember* a (first l))
                (rember* a (rest l)))))

(rember* 'cup '((coffee) cup ((tea) cup)
                (and (hick)) cup))

(rember* 'sauce '(((tomato sauce))
                  ((bean) sauce)
                  (and ((flying)) sauce)))

(lat? '(((tomato sauce))
        ((bean) sauce)
        (and ((flying)) sauce)))

(atom? (first '(((tomato sauce))
                ((bean) sauce)
                (and ((flying)) sauce))))

(defn insertR* [new old l]
  (cond
    (empty? l) ()

    (atom? (first l))
    (cond
      (= (first l) old) (cons old
                              (cons new
                                    (insertR* new old (rest l))))
      :else (cons (first l)
                  (insertR* new old (rest l))))

    :else (cons (insertR* new old (first l))
                (insertR* new old (rest l)))))

(insertR* 'roast 'chuck '((how much (wood))
                          could
                          ((a (wood) chuck))
                          (if (a) ((wood chuck)))
                          could chuck wood))

(defn occur* [a l]
  (cond
    (empty? l) 0

    (atom? (first l))
    (cond
      (= (first l) a) (inc (occur* a (rest l)))
      :else (occur* a (rest l)))

    :else (o+ (occur* a (first l))
              (occur* a (rest l)))))

(occur* 'banana '((banana)
                  (split ((((banana ice)))
                          (cream (banana))
                          sherbet))
                  (banana)
                  (bread)
                  (banana brandy)))

(defn subst* [new old l]
  (cond
    (empty? l) ()

    (atom? (first l))
    (cond
      (= (first l) old) (cons new
                              (subst* new old (rest l)))
      :else (cons (first l)
                  (subst* new old (rest l))))

    :else (cons (subst* new old (first l))
                (subst* new old (rest l)))))

(subst* 'orange 'banana '((banana)
                          (split ((((banana ice)))
                                  (cream (banana))
                                  sherbet))
                          (banana)
                          (bread)
                          (banana brandy)))

(defn insertL* [new old l]
  (cond
    (empty? l) ()

    (atom? (first l))
    (cond
      (= (first l) old) (cons new
                              (cons old
                                    (insertL* new old (rest l))))
      :else (cons (first l)
                  (insertL* new old (rest l))))

    :else (cons (insertL* new old (first l))
                (insertL* new old (rest l)))))

(insertL* 'pecker 'chuck '((how much (wood))
                           could
                           ((a (wood) chuck))
                           (((chuck)))
                           (if (a) ((wood chuck)))
                           could chuck wood))

(defn member* [a l]
  (cond
    (empty? l) false
    (atom? (first l)) (or (= (first l) a)
                          (member* a (rest l)))
    :else (or (member* a (first l))
              (member* a (rest l)))))

(member* 'chips '((potato) (chips ((with) fish) (chips))))

(defn leftmost [l]
  (cond
    (atom? (first l)) (first l)
    :else (recur (first l))))

(leftmost '((potato) (chips ((with) fish) (chips))))

(leftmost '(((hot) (tuna (and))) cheese))

;; (leftmost '(((() four)) 17 (seventeen)))

;; (leftmost ())

(and (atom? (first '(mozzarella pizza)))
     (= (first '(mozzarella pizza)) 'pizza))

(and (atom? (first '((mozarella mushroom) pizza)))
     (= (first '((mozzarella mushroom) pizza)) 'pizza))

(and (atom? (first '(pizza (tastes good))))
     (= (first '(pizza (tastes good))) 'pizza))

(defn eqlist? [l1 l2]
  (cond
    (and (empty? l1) (empty? l2)) true
    (or (empty? l1) (empty? l2)) false

    (and (atom? (first l1))
         (atom? (first l2)))
    (and (eqan? (first l1) (first l2))
         (eqlist? (rest l1) (rest l2)))

    (or (atom? (first l1))
        (atom? (first l2))) false
    :else (and (eqlist? (first l1) (first l2))
               (eqlist? (rest l1) (rest l2)))))

(eqlist? '(strawberry ice cream)
         '(strawberry ice cream))

(eqlist? '(strawberry ice cream)
         '(strawberry cream ice))

(eqlist? '(banana ((split)))
         '((banana) (split)))

(eqlist? '(beef ((sausage)) (and (soda)))
         '(beef ((salami)) (and (soda))))

(eqlist? '(beef ((sausage)) (and (soda)))
         '(beef ((sausage)) (and (soda))))

(defn equal? [s1 s2]
  (cond
    (and (atom? s1) (atom? s2)) (eqan? s1 s2)
    (or (atom? s1) (atom? s2)) false
    :else (eqlist? s1 s2)))

(defn eqlist?' [l1 l2]
  (cond
    (and (empty? l1) (empty? l2)) true
    (or (empty? l1) (empty? l2)) false
    :else (and (equal? (first l1) (first l2))
               (eqlist?' (rest l1) (rest l2)))))

(defn rember [s l]
  (cond
    (empty? l) ()
    (equal? (first l) s) (rest l)
    :else (cons (first l)
                (rember s (rest l)))))
