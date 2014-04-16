(ns cl-check.core)

(load-file "src/cl_check/board_creation.clj")
(load-file "src/cl_check/space_helpers.clj")
(load-file "src/cl_check/eval_helpers.clj")
(load-file "src/cl_check/move_validations.clj")

(defn move_checker
  [[old_x, old_y, new_x, new_y]]
    (change_space
       new_x
       new_y
      (get_space old_x old_y))
    (change_space old_x old_y "_"))


(defn remove_checker
  [[x, y]]
  (println "i am removing a checker from" x y)
  (change_space x y "_"))

(defn king_of_color
  [color]
  (cond (= "r" color) "rk"
        (= "b" color) "rb"
    ))

(defn make_king
  [x, y]
  (change_space
     x
     y
     (king_of_color x y)))

(defn make_move
  [move]
  (move_checker move)
  (if (validate_jump_occuring move)
      (remove_checker (middle_cords move)))
  (if (king_row move)
      (eval_with_new move make_king))
  true )

(defn valid_move
  [move]
  (and
    (validate_direction move)
    (validate_distance move)
    (validate_landing move)))

(defn move
  [[old_cords][new_cords]]
  (let [move [old_cords new_cords]]
  (if (valid_move move)
     (make_move move)
     false)))

(defn eval_with_move
  [[[old_x old_y][new_x new_y]] func]
  (func old_x old_y new_x new_y))

(defn foo
  [move func]
  (eval_with_move move
    (fn [old_x old_y new_x new_y] (func new_x new_y))))

(foo println move)

;; (move [0 2] [1 3])

