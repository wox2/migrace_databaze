;;; File: blind-seek.lisp -*- Mode: Lisp; Syntax: Common-Lisp; -*-

;;;; The Blind-Seek World Environment

(defparameter *dl* '((1 0) (0 1) (-1 0) (0 -1)))

;;; ===========================================================
;;; This definition should be changed to obtain different testing environment
;;; At least, you will put your agent name instead of bs-agent
;;;
(defstructure (bs-world (:include grid-environment
    (size (@ 10 10))               ; 10 x 10 grid
    (aspec '((at free? ask-user-bs-agent))) ; one bs-agent at a random free location
    (bspec '((at edge wall)        ; grid edge locations contain wall
             (* 10 obstacle)       ; 20 obstacles at random locations
             (* 5 (at free? person)))))  ; five persons at random free locations
    ))
;;; ===========================================================

(defstructure (bs-agent 
                (:include agent (body (make-bs-agent-body))))
  "An agent for bs world.")

(defstructure (bs-agent-body (:include agent-body))
  (penalty nil)
  (grid (make-array (@ 10 10) :initial-element '())))

(defstructure (ask-user-bs-agent (:include bs-agent (program 'ask-user-bs))) 
  "An agent that asks the user to type in an action.")

(defun ask-user-bs (percept)
  "Ask the user what action to take."
  (format t 
     "~&Percept is ~A; action (right forward left backward stop)? " percept)
  (read))

;;; ================================================================
;;; This is to be defined when designing a new agent 
;
(defstructure (xxx    ; replace xxx by your unique name, as e.g. FEL username
                (:include bs-agent 
                  (body (make-xxx-body))
                  (program 'xxx_program))) 
  "AnYour agent for bs world.")
;
(defstructure (xxx-body 
                (:include bs-agent-body))
;  (slot1 default1)  ; any specific extra slots your agent's body would need
;  ...
;  (slotn defaultn))
)
(defun xxx_program (percept)
  (let ((agent-body (first percept)))    ; extracts agent body from percept
    (setf percept (second percept))      ; extracts proper percept
    'forw
; ...
; ...  here your program comes to calculate and return a proper action       
; ...
) )
;;; Any number of auxiliary functions can follow
;;; 
;;; To test run the game you perform
;;; (run-environment (make-bs-world :max-steps 10)); or more than 10 steps
;;; ==================================================================


(defun rand-heading ()
  (copy-list (nth (random 4) *dl*)))

(defstructure (person (:include object
    (name "P") 
    (size 0.1)))
  "An object to be found by agent.")

;;;; Defining the generic functions

(defmethod initialize ((env bs-world))
  "Called once to do whatever is necessary to set up the environment
  for running the simulation."
  (call-next-method)
  (let* ((agent (first (environment-agents env)))
         (body (agent-body agent))
         (grid (grid-environment-grid env))
         (agrid (bs-agent-body-grid body)))
    (move-object-to body (random-loc env) env)
    (setf (object-bump body) nil) 
    (dotimes (x (xy-x (grid-environment-size env))) 
      (dotimes (y (xy-y (grid-environment-size env)))
        (let ((gxy (aref grid x y)))
          (setf (aref agrid x y) gxy)
          (when (member-if #'bs-agent-body-p gxy) 
            (setf (aref agrid x y) nil)))))
    grid))

(defmethod update-fn ((env bs-world))
  (for each agent in (environment-agents env) do
    (decf (bs-agent-score agent)))
  (call-next-method))

(defmethod termination? ((env bs-world))
  "End when some person met by agent or stop action performed."
  (> (environment-step env) (environment-max-steps env)))

(defmethod performance-measure ((env bs-world) agent)
  "Agent score is taken."
  (if (bs-agent-body-penalty (agent-body agent)) -1000
      (bs-agent-score agent)))

(defmethod get-percept ((env bs-world) agent)
  "Perceive bump or nil."
  (let ((abody (agent-body agent)))
    (list abody (if (object-bump abody) 'bump nil))))
  
(defmethod legal-actions ((env bs-world))
  "In the wumpus world, agents can move around, grab gold and shoot arrows."
  '(right forw left back stop))

;;;; Actions
 
(defmethod left ((env bs-world) agent-body &optional (args 1))
  "Go one step to the left from where heading."
   (let ((heading (tleft (copy-list (object-heading agent-body)))))
     (move-object-by agent-body heading env))
   (let ((loc (object-loc agent-body)))
     (when (find-object-if #'person-p loc env)
       (setf (environment-max-steps env) 0))))

(defun tleft (xy)
  (let ((x (xy-x xy)))
    (setf (xy-x xy) (if (zerop x) (- (xy-y xy)) 0)
          (xy-y xy) x)
    xy))
         
(defmethod right ((env bs-world) agent-body &optional (args 1))
  "Go one step to the right from where heading."
   (let ((heading (tright (copy-list (object-heading agent-body)))))
     (move-object-by agent-body heading env))
   (let ((loc (object-loc agent-body)))
     (when (find-object-if #'person-p loc env)
       (setf (environment-max-steps env) 0))))

(defun tright (xy)
  (let ((x (xy-x xy)))
    (setf (xy-x xy) (xy-y xy)
          (xy-y xy) (if (zerop x) 0 (- x)))
    xy))
 
(defmethod forw ((env bs-world) agent-body &optional (args 1))
  "Go one step to where heading."
   (let ((heading (object-heading agent-body)))
     (move-object-by agent-body heading env))
   (let ((loc (object-loc agent-body)))
     (when (find-object-if #'person-p loc env)
       (setf (environment-max-steps env) 0))))
         
(defmethod back ((env bs-world) agent-body &optional (args 1))
  "Go one step opposite to where heading."
   (let ((heading (tright (tright (copy-list (object-heading agent-body))))))
     (move-object-by agent-body heading env))
   (let ((loc (object-loc agent-body)))
     (when (find-object-if #'person-p loc env)
       (setf (environment-max-steps env) 0))))         
 
(defmethod stop ((env bs-world) agent-body &optional (args 1))
  "Stop bs world execution and check if persons inaccessible."
  (setf (environment-max-steps env) 0)
  (if (persons-accessible (bs-agent-body-grid agent-body)
                          (object-loc agent-body))
      (setf (bs-agent-body-penalty agent-body) -1000))) 
 
(defun persons-accessible (grid location)
  (let ((open (list location)) loc x y val)
    (loop 
      (if (null open) (return nil))
      (setf loc (first open)
            open (cdr open)
            x (xy-x loc)
            y (xy-y loc)
            val (aref grid x y))
      (when (find-if #'person-p val) (return-from persons-accessible T))
      (setf (aref grid x y) 'C)  
      (setf open (append open
                         (new-locs loc grid open (array-dimensions grid)))))))

(defun new-locs (loc grid open g-size)
  (let ((nl (mapcar #'(lambda (x) (xy-add loc x)) 
                    *dl*)))
    (remove-if #'(lambda (new-loc)
                   (or (not (inside new-loc (xy-x g-size) (xy-y g-size)))
                       (let ((val (aref grid (xy-x new-loc) (xy-y new-loc))))
                         (or (eq val 'C)
                             (find-if #'obstacle-p val)
                             (member new-loc open :test #'equal)))))
               nl)))
           
; (run-environment (make-bs-world :max-steps 10))

