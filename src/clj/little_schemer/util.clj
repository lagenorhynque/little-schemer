(ns little-schemer.util)

(def car first)

(def cdr rest)

(def null? empty?)

(def atom? (complement seqable?))

(def eq? =)
