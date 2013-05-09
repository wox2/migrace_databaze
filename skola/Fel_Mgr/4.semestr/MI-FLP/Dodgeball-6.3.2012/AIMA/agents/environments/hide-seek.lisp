;;; File: hide-seek.lisp -*- Mode: Lisp; Syntax: Common-Lisp; -*-

;;;; The Hide-Seek World Environment

;;; ===========================================================
;;; This definition should be changed to obtain different testing environment
;;; At least, you will put your agent name instead of bs-agent
;;;
(defstructure (hs-world (:include grid-environment
    (size (@ 10 10))                ; 10 x 10 grid environment
    (aspec '(ask-user-hs-agent))    ; one seeking agent, you put your agent here
    (bspec '((at edge wall)         ; grid edges are wall
             (* 5 bush)             ; 5 bushes at random locations
             (* 5 (C bush person)))))) ; 5 more bushes with hidden persons
    (persons-remaining 5))          ; shoul correspond to hidden persons
;;; ==========================================================

(defstructure (hs-agent 
                (:include agent (body (make-hs-agent-body))))
  "An agent for bs world.")

(defstructure (hs-agent-body (:include agent-body))
  (grid nil))

(defstructure (ask-user-hs-agent (:include hs-agent (program 'ask-user-hs))) 
  "An agent that asks the user to type in an action.")

(defun ask-user-hs (percept)
  "Ask the user what action to take."
  (format t 
     "~&Percept is ~A; action (goright midright forw midleft goleft pyky turnleft turnright)? " percept)
  (read))

;;; ================================================================
;;; This is to be defined when designing a new agent 
;
;(defstructure (xxx    ; replace xxx by your unique name, as e.g. FEL username
;                (:include hs-agent 
;                  (body (make-xxx-body))
;                  (program 'xxx_program))) 
;  "Your agent for hs-world.")
;
;(defstructure (xxx-body 
;                (:include hs-agent-body))
;  (slot1 default1)  ; any specific extra slots your agent's body would need
;  ...
;  (slotn defaultn))
;
;(defun xxx_program (percept)
;  (let ((agent-body (first percept)))    ; extracts agent body from percept
;    (setf percept (second percept))      ; extracts proper percept
; ...
; ...  here your program comes to calculate and return a proper action       
; ...
;) )
;;; Any number of auxiliary functions can follow
;;; 
;;; To test run the game you perform
;;; (run-environment (make-hs-world :max-steps 10)); or more than 10 steps
;;; ==================================================================

(defstructure (bush (:include obstacle
    (alive? t)
    (name "B") 
    (size 0.9)
    ))
  "A bush that can serve to hide persons protecting them from 3 directions.")

(defstructure (person (:include object
    (name "P") 
    (size 0.01)))
  "An object to be hidden in a bush, visible only from where bush is heading.")

;;;; Defining the generic functions

(defmethod termination? ((env hs-world))
  "End when everyone dead."
  (or (> (environment-step env) (environment-max-steps env))
      (zerop (hs-world-persons-remaining env))))

(defmethod initialize ((env hs-world))
  "Tests if persons could be visible at all."
  (call-next-method)
  (check-persons-visible (grid-environment-grid env) env))

(defun check-persons-visible (grid env)
  (let* ((dim (array-dimensions grid))
         (xmax (- (xy-x dim) 2))
         (ymax (- (xy-y dim) 2)))
    (dotimes (x xmax)
      (dotimes (y ymax)
        (let* ((loc (@ (1+ x) (1+ y)))
               (bush (find-object-if #'bush-p loc env))
               (person (and bush (find-if #'person-p (object-contents bush))))
               (heading (and person (object-heading bush))))
          (when (and person (not (empty-loc? (xy-add loc heading) env)))
            (check-bush bush loc grid env)))))))

(defun check-bush (bush loc grid env)
  "Checks and makes the person in bush visible from somewhere."
  (let ((free-heading (get-free-hd loc env)))
    (if free-heading
        (setf (object-heading bush) free-heading)
        (delete-obstacle bush loc grid env))))

(defun get-free-hd (loc env)
  "Finds a free neighbor of loc or occupied by an agent."
  (let ((hd-list '((1 0) (-1 0) (0 1) (0 -1))))
    (dolist (hd hd-list)
      (let ((neighbor (xy-add loc hd)))
        (when (or (null (grid-contents env neighbor))
                  (find-object-if #'agent-p neighbor env))
          (return-from get-free-hd hd))))
    (dolist (hd hd-list)
      (let ((neighbor (xy-add loc hd)))
        (when (find-object-if #'bush-p neighbor env)
          (let ((grid (grid-environment-grid env))
                (nx (xy-x neighbor))
                (ny (xy-y neighbor)))
            (setf (aref grid nx ny) nil)
            (return-from get-free-hd hd)))))))
   
(defmethod performance-measure ((env hs-world) agent)
  "Score 20 for finding a person and penalty of 1 for each step taken."
  (let ((agent-body (agent-body agent)))
    (- (* (length (object-contents agent-body)) 20)
       (environment-step env))))

(defmethod get-percept ((env hs-world) agent)
  "Perceive nil until the first object seen where agent is heading."
  (let* ((loc (object-loc (agent-body agent))) 
         (heading (object-heading (agent-body agent)))
         next-loc next-obj result (n 0))
    (loop 
      (setf next-loc (xy-add loc heading))
      (if (not (empty-loc? next-loc env)) (return))
      (setf loc next-loc)
      (incf n))
    
    (let* ((bush    (find-object-if #'bush-p next-loc env))
           (person  (and bush (find-if #'person-p (object-contents bush))))
           (wall    (find-object-if #'wall-p next-loc env))
           (obj     (find-object-if #'object-p next-loc env))
           (bump    (object-bump (agent-body agent))))
      (if bump 
          (list (agent-body agent) 'bump)
          (prog2
            (setf result 
                  (list (cond 
                          ((and bush person 
                                (xy-equal loc (looks-where next-loc env)))
                           'person)
                          (bush 'bush)
                          (wall 'wall)
                          (obj 'obstacle)))) 
            (dotimes (i n (cons (agent-body agent) result)) 
              (setf result (cons nil result))))))))

(defun check-grid-contents (env loc)
  (unless (empty-loc? loc env)
    (let* ((bush    (find-object-if #'bush-p loc env))
           (wall    (find-object-if #'wall-p loc env))
           (obj     (find-object-if #'object-p loc env))
           (agent   (find-object-if #'agent-p loc env)))
      (cond (bush (list 'B (object-heading bush)))
            (wall 'W)
            (obj  'O)
            (agent (list 'A (object-heading (agent-body agent))))))))
       
(defun looks-where (loc env)
  (let ((object (find-object-if #'bush-p loc env)))
    (if object (xy-add loc (object-heading object)))))

(defmethod legal-actions ((env hs-world))
  "In the wumpus world, agents can move around, grab gold and shoot arrows."
  '(goright midright forw midleft goleft pyky turnleft turnright stop))

;;;; Actions

(defmethod pyky ((env hs-world) agent-body &optional args)
  "Agent heading to a neighbor bush with person picks the person."
  (declare-ignore args) ;; They are used in other environments
  (let* ((loc (object-loc agent-body))
         (heading (object-heading agent-body))
         (neighbor (xy-add loc heading))
         (neighbor-bush (find-object-if #'bush-p neighbor env))
         (neighbor-person 
           (and neighbor-bush
                (find-if #'person-p (object-contents neighbor-bush)))))
     (cond ((and neighbor-person (is-heading-to neighbor-bush loc))
            (setf (object-contents neighbor-bush)
                  (delete neighbor-person (object-contents neighbor-bush)))
            (place-in-container neighbor-person agent-body env)
            (decf (hs-world-persons-remaining env)))
           (t (setf (environment-illegal-actions env)
               (nconc (environment-illegal-actions env) 
                      (list agent-body 'pyky)))))))

(defun is-heading-to (object location)
  "Test if object in its actual position is heading to location."
  (let ((loc (object-loc object))
        (heading (object-heading object)))
    (xy-equal location (xy-add loc heading))))

(defmethod turnleft ((env hs-world) agent-body &optional args)
  "Turn left."
  (setf (object-heading agent-body) (tleft (object-heading agent-body))))

(defun tleft (xy)
  (let ((x (xy-x xy)))
    (setf (xy-x xy) (if (zerop x) (- (xy-y xy)) 0)
          (xy-y xy) x)
    xy))

(defmethod turnright ((env hs-world) agent-body &optional args)
  "Turn left."
  (setf (object-heading agent-body) (tright (object-heading agent-body))))

(defun tright (xy)
  (let ((x (xy-x xy)))
    (setf (xy-x xy) (xy-y xy)
          (xy-y xy) (if (zerop x) 0 (- x)))
    xy))

(defmethod goleft ((env hs-world) agent-body &optional (args 1))
  "Go args steps to the left from where heading."
   (let ((heading (tleft (copy-list (object-heading agent-body)))))
     (dotimes (i args)
       (move-object-by agent-body heading env))))
         
(defmethod goright ((env hs-world) agent-body &optional (args 1))
  "Go args steps to the right from where heading."
   (let ((heading (tright (copy-list (object-heading agent-body)))))
     (dotimes (i args)
       (move-object-by agent-body heading env))))

(defmethod forw ((env hs-world) agent-body &optional (args 1))
  "Go args steps to where heading."
   (let ((heading (object-heading agent-body)))
     (dotimes (i args) 
       (move-object-by agent-body heading env))))
         
(defmethod midleft ((env hs-world) agent-body &optional (args 1))
  "Go args steps to the left-forward from where heading."
   (let* ((heading (object-heading agent-body))
          (direction (xy-add heading (tleft (copy-list heading)))))
     (dotimes (i args)
       (move-object-by agent-body direction env))))
         
(defmethod midright ((env hs-world) agent-body &optional (args 1))
  "Go args steps to the right-forward from where heading."
   (let* ((heading (object-heading agent-body))
          (direction (xy-add heading (tright (copy-list heading)))))
     (dotimes (i args)
       (move-object-by agent-body direction env))))
 
(defmethod stop ((env hs-world) agent-body &optional args)
  "Stop hs world execution."
  (setf (environment-max-steps env) 0))
                 
; (run-environment (make-hs-world :max-steps 10))

