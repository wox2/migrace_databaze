;;; File: touch-last.lisp -*- Mode: Lisp; Syntax: Common-Lisp; -*-

;;;; The Touch Last World Environment

;;; ===========================================================
;;; This definition should be changed to obtain different testing environment
;;; At least, you will put your agent name instead of ask-user-tl-agent
;;;
(defstruct (tl-world (:include grid-environment
    (size (@ 10 10))                       ; 10 x 10 grid
    (aspec '((AT FREE? ask-user-tl-agent)  ; agents that will play the game
             (AT FREE? ask-user-tl-agent))); the first is trying to catch&touch
             ;(AT FREE? ask-user-tl-agent)  ; remaining try to escape him
             ;(AT FREE? ask-user-tl-agent)))
    (bspec '((at edge wall)                ; grid border is wall
             (* 5 obstacle))))))            ; 5 obstacles at random locations

;;; ============================================================

(defstruct (tl-agent 
                (:include agent (body (make-tl-agent-body))))
  "An agent for hn world.")

(defstruct (tl-agent-body (:include agent-body))
  (grid nil)
  (ma-babu? nil))

(defstruct (ask-user-tl-agent (:include tl-agent (program 'ask-user-tl))) 
  "An agent that asks the user to type in an action.")

(defun ask-user-tl (percept)
"Ask the user what action to take."
  (let* ((agent-body (car percept))
         (ma-babu (if (tl-agent-body-ma-babu? agent-body) 'ano 'ne)))
    (setf percept (cdr percept))
    (format t "~&Agent ~A; Ma-babu: ~A Percept is ~A; 
~&action (right midright forw midleft left pyky turnleft turnright)? " 
       agent-body ma-babu percept)
    (read)))

;;; ================================================================
;;; This is to be defined when designing a new agent 
;
;(defstruct (xxx    ; replace xxx by your unique name, as e.g. FEL username
;                (:include tl-agent 
;                  (body (make-xxx-body))
;                  (program 'xxx_program))) 
;  "Your agent for  tl-world.")
;
;(defstruct (xxx-body 
;                (:include tl-agent-body))
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
;  ))
;
;;; Any number of auxiliary functions can follow
;;; 
;;; To test run the game you perform
;;; (run-environment (make-tl-world :max-steps 10)); or more than 10 steps
;;;==================================================================


;;;; Defining the generic functions

(defmethod initialize ((env tl-world))
  "Called once to do whatever is necessary to set up the environment
  for running the simulation."
  (call-next-method)
  (let* ((agents (environment-agents env))
         (body1 (agent-body (first agents))))
    (setf (tl-agent-body-ma-babu? body1) T)
    env))
      
(defmethod update-fn ((env tl-world))
  (for each agent in (environment-agents env) do
     (if (tl-agent-body-ma-babu? (agent-body agent))
         (decf (tl-agent-score agent))
         (incf (tl-agent-score agent))))
  (let ((baba 
         (find-if #'(lambda (x) (tl-agent-body-ma-babu? (agent-body x)))
                  (environment-agents env))))
    (setf (environment-agents env)
          (cons baba (delete baba (environment-agents env)))))
  (call-next-method))

(defmethod termination? ((env tl-world))
 (> (environment-step env) (environment-max-steps env)))

(defmethod performance-measure ((env tl-world) agent)
  "Score plus for not having babu, minus for having babu."
  (tl-agent-score agent))

(defmethod get-percept ((env tl-world) agent)
  "Perceive nil until the first object seen where agent is heading."
  (let* ((body (agent-body agent)) 
         (loc (object-loc body)) 
         (heading (object-heading body))
          next-loc next-obj result (n 0))
    (unless (tl-agent-body-grid body)
      (setf (tl-agent-body-grid body)
          (make-array (grid-environment-size env) :initial-element '())))
    (loop 
      (setf next-loc (xy-add loc heading))
      (if (not (empty-loc? next-loc env)) (return))
      (setf loc next-loc)
      (incf n))
    
    (let* ((n-agent    (find-object-if #'agent-body-p next-loc env))
           (n-ma-babu  (and n-agent (tl-agent-body-ma-babu? n-agent)))
           (n-obstacle (find-object-if #'obstacle-p next-loc env))
           (n-wall     (and n-obstacle (find-object-if #'wall-p next-loc env)))
           (bump (object-bump body)))
      (if bump 
          (list body 'bump)
          (prog2
            (setf result 
                  (list (cond 
                          (n-ma-babu  (cons 'baba (object-heading n-agent)))
                          (n-agent    (cons 'agent (object-heading n-agent)))
                          (n-wall     'wall)
                          (n-obstacle 'obstacle)))) 
            (dotimes (i n (cons body result)) 
              (setf result (cons nil result))))))))

(defmethod get-percept1 ((env tl-world) agent)
  "Perceive the whole grid."
  (let* ((body (agent-body agent)))
    (unless (tl-agent-body-grid body)
      (setf (tl-agent-body-grid body)
          (make-array (grid-environment-size env) :initial-element '())))
    (let ((grid (tl-agent-body-grid body))
          (loc (object-loc body)) 
          (heading (object-heading body))) 
      (dotimes (x (xy-x (grid-environment-size env))) 
        (dotimes (y (xy-y (grid-environment-size env)))
          (setf (aref grid x y) 
              (check-grid-contents env (@ x y)))))
      (setf (aref grid (xy-x loc) (xy-y loc))
            (list 'self heading))
      grid)))

(defun check-grid-contents (env loc)
  (unless (empty-loc? loc env)
    (let* ((agent-bd (find-object-if #'agent-body-p loc env))
           (baba (and agent-bd (tl-agent-body-ma-babu? agent-bd)))
           (obstacle (find-object-if #'obstacle-p loc env)))
      (cond (obstacle  'O)
            (agent-bd (list (if baba 'B 'A) 
                            (object-heading agent-bd)))))))
       
(defun looks-where (loc env)
  (let ((object (find-object-if #'bush-p loc env)))
    (if object (xy-add loc (object-heading object)))))

(defun empty-loc? (loc env)
  (null (grid-contents env loc))) 
  
(defmethod legal-actions ((env tl-world))
  "In the wumpus world, agents can move around, grab gold and shoot arrows."
  '(right midright forw midleft left pyky turnleft turnright stop))

;;;; Actions

(defmethod pyky ((env tl-world) agent-body &optional args)
  "Agent heading to a neighbor bush with person picks the person."
  (declare-ignore args) ;; They are used in other environments
  (let* ((loc (object-loc agent-body))
         (heading (object-heading agent-body))
         (neighbor (xy-add loc heading))
         (n-agent-body (find-object-if #'agent-body-p neighbor env)))
     (cond (n-agent-body
            (setf (tl-agent-body-ma-babu? agent-body) nil
                  (tl-agent-body-ma-babu? n-agent-body) t))
           (t (setf (environment-illegal-actions env)
                    (nconc (environment-illegal-actions env) 
                           (list agent-body 'pyky)))))))

(defun is-heading-to (object location)
  "Test if object in its actual position is heading to location."
  (let ((loc (object-loc object))
        (heading (object-heading object)))
    (xy-equal location (xy-add loc heading))))

(defmethod turnleft ((env tl-world) agent-body &optional args)
  "Turn left."
  (setf (object-heading agent-body) (tleft (object-heading agent-body))))

(defun tleft (xy)
  (let ((x (xy-x xy)))
    (setf (xy-x xy) (if (zerop x) (- (xy-y xy)) 0)
          (xy-y xy) x)
    xy))

(defmethod turnright ((env tl-world) agent-body &optional args)
  "Turn left."
  (setf (object-heading agent-body) (tright (object-heading agent-body))))

(defun tright (xy)
  (let ((x (xy-x xy)))
    (setf (xy-x xy) (xy-y xy)
          (xy-y xy) (if (zerop x) 0 (- x)))
    xy))
 
(defmethod left ((env tl-world) agent-body &optional (args 1))
  "Go args steps to the left from where heading."
   (let ((heading (tleft (copy-list (object-heading agent-body)))))
     (dotimes (i args)
       (move-object-by agent-body heading env))))
         
(defmethod right ((env tl-world) agent-body &optional (args 1))
  "Go args steps to the right from where heading."
   (let ((heading (tright (copy-list (object-heading agent-body)))))
     (dotimes (i args)
       (move-object-by agent-body heading env))))

(defmethod forw ((env tl-world) agent-body &optional (args 1))
  "Go args steps to where heading."
   (let ((heading (object-heading agent-body)))
     (dotimes (i args) 
       (move-object-by agent-body heading env))))
         
(defmethod midleft ((env tl-world) agent-body &optional (args 1))
"Go args steps to the left-forward from where heading."
  (cond ((not (tl-agent-body-ma-babu? agent-body)) 
         (setf (environment-illegal-actions env)
               (nconc (environment-illegal-actions env) 
                      (list agent-body 'midleft))))
        (t (let* ((heading (object-heading agent-body))
                  (direction (xy-add heading (tleft (copy-list heading)))))
             (dotimes (i args)
               (move-object-by agent-body direction env))))))
         
(defmethod midright ((env tl-world) agent-body &optional (args 1))
  "Go args steps to the right-forward from where heading."
  (cond ((not (tl-agent-body-ma-babu? agent-body)) 
         (setf (environment-illegal-actions env)
               (nconc (environment-illegal-actions env) 
                      (list agent-body 'midright))))
        (t (let* ((heading (object-heading agent-body))
          (direction (xy-add heading (tright (copy-list heading)))))
     (dotimes (i args)
       (move-object-by agent-body direction env))))))
 
(defmethod stop ((env tl-world) agent-body &optional args)
  "Stop seek world execution."
   (setf (environment-max-steps env) 0))
                 
 
; (run-environment (make-tl-world :max-steps 10))

