(load-file "src/cl_check/space_helpers.clj")

(defn eval_with_move
  [[[old_x old_y][new_x new_y]] func]
  (func old_x old_y new_x new_y))

(defn eval_with_old
  [move func]
  (eval_with_move move
    (fn [old_x old_y new_x new_y] (func new_x new_y))))

(defn eval_with_new
  [move func]
  (eval_with_move move
    (fn [old_x old_y new_x new_y] (func old_x old_y))))

(defn eval_with_xs
  [move func]
  (eval_with_move move
    (fn [old_x old_y new_x new_y] (func old_x new_x))))

(defn eval_with_ys
  [move func]
  (eval_with_move move
    (fn [old_x old_y new_x new_y] (func old_y new_y))))
