(load-file "src/cl_check/space_helpers.clj")
(load-file "src/cl_check/eval_helpers.clj")

(defn abs "(abs n) is the absolute value of n" [n]
  (cond
   (not (number? n)) (throw (IllegalArgumentException.
                             "abs requires a number"))
   (neg? n) (- n)
   :else n))

(defn is_moving_up
  [move]
  (eval_with_ys move <))

(defn is_moving_down
  [move]
  (eval_with_ys move >))

(defn validate_direction
  [move]
  (let [old_space (eval_with_old move get_space)]
    (cond (is_king old_space) true
          (is_red old_space) (is_moving_up move)
          (is_black old_space) (is_moving_down move)
          :else false)))

(defn validate_landing
  [move]
  (def landing (eval_with_new get_space move))
  (cond (= landing "_") true
        :else false))

(defn calc_dist [num_1, num_2]
  (abs (- num_1 num_2)))

(defn jump_distance_okay
  [old_x old_y new_x new_y]
  (and
    (= 2 (calc_dist old_x new_x))
    (= 2 (calc_dist old_y new_y))))

(defn validate_jump_occuring
  ([[old_x, old_y, new_x, new_y]]
    (def own_color (get_color old_x old_y))
    (def middle_color (middle_space old_x old_y new_x new_y))
    (and (jump_distance_okay old_x old_y new_x new_y)
         (opposing_colors own_color middle_color)))
    ([old_x, old_y, new_x, new_y]
    (def own_color (get_color old_x old_y))
    (and (jump_distance_okay old_x old_y new_x new_y)
         (opposing_colors own_color middle_color))))

(defn validate_distance
  [[old_x, old_y, new_x, new_y]]
  (cond
    (= 1 (calc_dist old_x new_x)) (= 1 (calc_dist old_y new_y))
    (validate_jump_occuring old_x old_y new_x new_y) true
    :else false))
