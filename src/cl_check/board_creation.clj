(defn make_row [val]
  (replicate 8 val))

(defn create_new_board []
    (make_row
       (make_row "_")))

(def board (atom (create_new_board)))

(defn get_row
  [y]
  (@board y))

(defn new_board
  [old_board, x, y, new_val]
    (assoc
      old_board
      y
      (assoc (get_row y) x new_val)))

(defn change_space
  [x, y, new_value]
  (swap!
    board
    new_board x y new_value))

(defn insert_reds []
  (dotimes [y 3]
    (if (zero? (mod y 2))
      (dotimes [x 8]
        (if (= (mod x 2)0 )
          (change_space x y "r")))
      (dotimes [x 8]
        (if (= (mod x 2) 1)
          (change_space x y "r"))))))

(defn insert_blacks []
  (dotimes [y 3]
    (if (= (mod (+ y 5) 2) 0)
      (dotimes [x 8]
        (if (= (mod x 2) 0)
         (change_space x (+ y 5) "b")))
      (dotimes [x 8]
        (if (= (mod x 2) 1)
         (change_space x (+ y 5) "b"))))))

(defn print_board []
  (for [row @board]
    (println row)))
