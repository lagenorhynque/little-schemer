(ns little-schemer.chapter07
  (:require [little-schemer.chapter02 :refer [member?]]
            [little-schemer.chapter03 :refer [firsts multirember]]
            [little-schemer.util :refer :all])
  (:refer-clojure :exclude [set?]))

(defn set? [lat]
  (cond
    (empty? lat) true
    (member? (first lat) (rest lat)) false
    :else (recur (rest lat))))

(set? '(apple peaches apple plum))

(set? '(apples peaches pears plums))

(set? ())

(set? '(apple 3 pear 4 9 apple 3 4))

(defn makeset [lat]
  (cond
    (empty? lat) ()
    (member? (first lat) (rest lat)) (makeset (rest lat))
    :else (cons (first lat)
                (makeset (rest lat)))))

(makeset '(apple peach pear peach plum apple lemon peach))

(defn makeset' [lat]
  (cond
    (empty? lat) ()
    :else (cons (first lat)
                (makeset'
                 (multirember (first lat) (rest lat))))))

(makeset' '(apple peach pear peach plum apple lemon peach))

(makeset' '(apple 3 pear 4 9 apple 3 4))

(defn subset? [set1 set2]
  (cond
    (empty? set1) true

    (member? (first set1) set2)
    (recur (rest set1) set2)

    :else false))

(subset? '(5 chicken wings)
         '(5 hamburgers 2 pieces fried chicken and light duckling wings))

(subset? '(4 pounds of horseradish)
         '(four pounds chicken and 5 ounces horseradish))

(defn subset?' [set1 set2]
  (cond
    (empty? set1) true
    :else (and (member? (first set1) set2)
               (recur (rest set1) set2))))

(defn eqset? [set1 set2]
  (cond
    (subset? set1 set2) (subset? set2 set1)
    :else false))

(eqset? '(6 large chickens with wings)
        '(6 chickens with large wings))

(defn eqset?' [set1 set2]
  (and (subset? set1 set2)
       (subset? set2 set1)))

(defn intersect? [set1 set2]
  (cond
    (empty? set1) false
    (member? (first set1) set2) true
    :else (recur (rest set1) set2)))

(intersect? '(stewed tomatoes and macaroni)
            '(macaroni and cheese))

(defn intersect?' [set1 set2]
  (cond
    (empty? set1) false
    :else (or (member? (first set1) set2)
              (recur (rest set1) set2))))

(defn intersect [set1 set2]
  (cond
    (empty? set1) ()
    (member? (first set1) set2) (cons (first set1)
                                      (intersect (rest set1) set2))
    :else (intersect (rest set1) set2)))

(intersect '(stewed tomatoes and macaroni)
           '(macaroni and cheese))

(defn union [set1 set2]
  (cond
    (empty? set1) set2
    (member? (first set1) set2) (union (rest set1) set2)
    :else (cons (first set1)
                (union (rest set1) set2))))

(union '(stewed tomatoes and macaroni casserole)
       '(macaroni and cheese))

(defn difference [set1 set2]
  (cond
    (empty? set1) ()
    (member? (first set1) set2) (difference (rest set1) set2)
    :else (cons (first set1)
                (difference (rest set1) set2))))

(defn intersectall [l-set]
  (cond
    (empty? (rest l-set)) (first l-set)
    :else (intersect (first l-set)
                     (intersectall (rest l-set)))))

(intersectall '((a b c)
                (c a d e)
                (e f g h a b)))

(intersectall '((6 pears and)
                (3 peaches and 6 peppers)
                (8 pears and 6 plums)
                (and 6 prunes with lots of apples)))

(defn a-pair? [x]
  (cond
    (atom? x) false
    (empty? x) false
    (empty? (rest x)) false
    (empty? (rest (rest x))) true
    :else false))

(a-pair? '(pear pear))

(a-pair? '(3 7))

(a-pair? '((2) (pair)))

(a-pair? '(full (house)))

(defn first' [p]
  (first p))

(defn second' [p]
  (first (rest p)))

(defn build [a1 a2]
  (cons a1
        (cons a2 ())))

(defn third [l]
  (first (rest (rest l))))

(defn fun? [rel]
  (set? (firsts rel)))

(fun? '((4 3) (4 2) (7 6) (6 2) (3 4)))

(fun? '((8 3) (4 2) (7 6) (6 2) (3 4)))

(fun? '((b 4) (b 0) (b 9) (e 5) (g 4)))

(defn revrel [rel]
  (cond
    (empty? rel) ()
    :else (cons (build (second' (first rel))
                       (first' (first rel)))
                (revrel (rest rel)))))

(revrel '((8 a) (pumpkin pie) (got sick)))

(defn revpair [pair]
  (build (second' pair)
         (first' pair)))

(defn revrel' [rel]
  (cond
    (empty? rel) ()
    :else (cons (revpair (first rel))
                (revrel' (rest rel)))))

(defn seconds [l]
  (cond
    (empty? l) ()
    :else (cons (second (first l))
                (seconds (rest l)))))

(defn fullfun? [fun]
  (set? (seconds fun)))

(fullfun? '((8 3) (4 2) (7 6) (6 2) (3 4)))

(fullfun? '((8 3) (4 8) (7 6) (6 2) (3 4)))

(fullfun? '((grape rasin) (plum prune) (stewed prune)))

(fullfun? '((grape rasin) (plum prune) (stewed grape)))

(defn one-to-one? [fun]
  (fun? (revrel fun)))

(one-to-one? '((chocolate chip) (doughy cookie)))
