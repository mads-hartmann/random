;;; structured-ocaml-edit.el --- Less text, more AST

(defvar structured-ocaml-mode-map (make-sparse-keymap))

(define-minor-mode structured-ocaml-mode
  "A minor mode for AST-like editing of OCaml files."
  :lighter " OCamlAST"
  :keymap structured-ocaml-mode-map
  (if (bound-and-true-p structured-ocaml-mode)
      (message "structured-ocaml activated")
    (message "structured-ocaml deactivated")))

(define-key structured-ocaml-mode-map (kbd "C-c C-l") 'structured-ocaml-introduce-let)
(define-key structured-ocaml-mode-map (kbd "C-c C-m") 'structured-ocaml-introduce-match)
(define-key structured-ocaml-mode-map (kbd "C-c C-p") 'structured-ocaml-introduce-pattern)
(define-key structured-ocaml-mode-map (kbd "M-n") 'structured-ocaml-move-to-next-placeholder)
(define-key structured-ocaml-mode-map (kbd "M-p") 'structured-ocaml-move-to-previous-placeholder)

(defun structured-ocaml-is-at-placeholder-p ()
  "Predicate to test if we're currently at a placeholder."
  (looking-at "\(\\*\\*)"))

(defun structured-ocaml-move-to-previous-placeholder ()
  "Backward search for the previous placeholder."
  (interactive)
  (when (search-backward "(**)" nil t)
    (forward-char 4)
    (set-mark-command nil)
    (backward-char 4)
    (setq deactivate-mark nil)))

(defun structured-ocaml-move-to-next-placeholder ()
  "Forward search for the next placeholder."
  (interactive)
  (if (structured-ocaml-is-at-placeholder-p)
      (setq structured-ocaml-jump-count 2)
    (setq structured-ocaml-jump-count 1))
  (when (search-forward "(**)" nil t structured-ocaml-jump-count)
    (set-mark-command nil)
    (backward-char 4)
    (setq deactivate-mark nil)))

(defun structured-ocaml-insert-snippet (snippet)
  "Inserts the given snippet and jumps to the first placeholder."
  (when (region-active-p)
    (delete-region (region-beginning) (region-end)))
  (save-excursion
    (indent-according-to-mode)
    (insert snippet)
    (indent-according-to-mode))
  (structured-ocaml-move-to-next-placeholder))

(defun structured-ocaml-request-new-name ()
  "Request a name for a variable."
  (read-string "variable name: "))

(defun structured-ocaml-introduce-let ()
  (interactive)
  (let ((name (structured-ocaml-request-new-name)))
    (structured-ocaml-insert-snippet (concat "let " name " = (**) in\n(**)"))))

(defun structured-ocaml-introduce-match ()
  (interactive)
  (structured-ocaml-insert-snippet "match (**) with\n(**)"))

(defun structured-ocaml-introduce-pattern ()
  (interactive)
  (structured-ocaml-insert-snippet "| (**) -> (**)"))
