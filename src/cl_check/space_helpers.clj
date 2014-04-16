(load-file "src/cl_check/board_creation.clj")
(defn get_space
  ([[x,y]]
    ((@board y) x))
  ([x,y]
    ((@board y) x)))

(defn middle_num
  [num_1, num_2]
  (- (max num_1 num_2) 1))

(defn middle_space
  ([[old_x, old_y, new_x, new_y]]
    (get_space (middle_num old_x new_x) (middle_num old_y new_y)))
  ([old_x, old_y, new_x, new_y]
    (get_space (middle_num old_x new_x) (middle_num old_y new_y))))

(defn middle_cords
  [[old_x, old_y, new_x, new_y]]
  [(middle_num new_x old_x) (middle_num new_y old_y)])

(defn king_row
  [[foo,bar,baz,new_y]]
  (or
     (= new_y 0)
     (= new_y 7)))

(defn space_color
  [space]
  (cond (= space "r") "r"
        (= space "rk") "r"
        (= space "b") "b"
        (= space "bk") "b"
        :else "_"))

(defn opposing_colors [color_one, color_two]
  (and
   (not= color_one color_two)
   (not= color_one "_")
   (not= color_two "_")))

(defn get_color [space]
  (space_color (get_space space )))

(defn is_red
  [space]
  (= "r" (get_color space)))

(defn is_black
  [space]
  (= "b" (get_color space)))

(defn is_king
  [[x y]]
  (let [space (get_space x y)]
    (or (= "rk" space) (= "bk" space))))
