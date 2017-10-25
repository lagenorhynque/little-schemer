(ns little-schemer.chapter01
  (:require [little-schemer.util :refer :all]))

'atom

'turkey

1492

'u

'*abc$

'(atom)

'(atom turkey or)

'(atom turkey) 'or

'((atom turkey) or)

'xyz

'(x y z)

'(how are you doing so far)

(count '(how are you doing so far))

'(((how) ar) ((you) (doing so)) far)

(count '(((how) ar) ((you) (doing so)) far))

()

'(() () () ())

(first '(a b c))

(first '((a b c) x y z))

;; (first 'hotdog)

(first ())

(first '(((hotdogs)) (and) (pickle) relish))

(first (first '(((hotdog)) (and))))

(rest '(a b c))

(rest '((a b c) x y z))

(rest '(hamburger))

(rest '((x) t r))

;; (rest 'hotdogs)

(rest ())

(first (rest '((b) (x y) ((c)))))

(rest (rest '((b) (x y) ((c)))))

;; (rest (first '(a (b (c)) d)))

(cons 'peanut '(butter and jelly))

(cons '(banana and) '(peanut butter and jelly))

(cons '((help) this) '(is very ((hard) to learn)))

(cons '(a b (c)) ())

(cons 'a ())

;; (cons '((a b c)) 'b)

;; (cons 'a 'b)

(cons 'a (first '((b) c d)))

(cons 'a (rest '((b) c d)))

(empty? ())

(empty? (quote ()))

(empty? '(a b c))

;; (empty? 'spagetti)

(atom? 'Harry)

(atom? '(Harry had a heap of apples))

(atom? (first '(Harry had a heap of apples)))

(atom? (rest '(Harry had a heap of apples)))

(atom? (rest '(Harry)))

(atom? (first (rest '(swing low sweet cherry oat))))

(atom? (first (rest '(swing (low sweet) cherry oat))))

(= 'Harry 'Harry)

(= 'margarine 'butter)

(= () '(strawberry))

(= 6 7)

(= (first '(Mary had a little lamb chop)) 'Mary)

(= (rest '(soured milk)) 'milk)

(= (first '(beans beans we need jelly beans))
   (first (rest '(beans beans we need jelly beans))))
