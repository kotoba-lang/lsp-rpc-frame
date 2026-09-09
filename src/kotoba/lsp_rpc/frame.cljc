(ns kotoba.lsp-rpc.frame
  "frame -- addressed on its own.

  Split out of kotoba.lang.lsp-rpc on 2026-09-09 (ADR-2609091200). The unit
  here is the DEFINITION, and this repo's deps.edn names exactly the
  definitions it reaches -- nothing else.
"
  (:require [kotoba.lang.json :as json])
)

(defn frame
  "Frame a message map as a Content-Length envelope string (the LSP wire shape).
  The body is JSON (via kotoba.lang.json)."
  [msg]
  (let [body (json/encode msg)
        len  (count (.getBytes ^String body "UTF-8"))]
    (str "Content-Length: " len "\r\n\r\n" body)))
