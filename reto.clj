
(defn regex-section [text]
    (second (re-find #"(?s)Ingredients:\s*(.*?)\s*Instructions" text)))

(def regex-instruction-num #"-?\d+")
(def regex-instruction-frac #"\d+\s*\d*/\d+|\d+/\d+|\d+")
(def regex-espacio #"\s+")
(def regex-Ingrediente-cantidad #"(?:cup|cups|gram|grams|tbsp|tablespoon|tsp|teaspoon|clove|cloves|oz|ounce|ounces|ml|l)")
(def regex-Ingrediente-cups #"(?:cup|cups)")
(def regex-cantidad-unidad #"(\d+\s*\d*/\d+|\d+/\d+|\d+)\s*(cup|cups)")

  
(defn Ingredients [archivo]
    (regex-section(slurp archivo))
    )


(defn cantidades [regex-texto]
    (println "cantidades") 
    (let [matches (re-seq regex-cantidad-unidad regex-texto)]
    (doseq [c matches]
    (println c))
        matches))

(defn fraccion-a-decimal [s]
  (let [partes (.split (str s) " ")
        decimal (fn [f]
                  (if (.contains f "/")
                    (let [[n d] (.split f "/")]
                      (/ (Double/parseDouble n) (Double/parseDouble d)))
                    (Double/parseDouble f)))]
    (cond
      (= (count partes) 2)
      (+ (decimal (first partes)) (decimal (second partes)))

      (= (count partes) 1)
      (decimal (first partes))

      :else
      (throw (Exception. (str "Formato inesperado: " s))))))

(defn cantidades-solo-fracc [regex-texto]
    (println "cantidades") 
    (let [matches (re-seq regex-instruction-frac regex-texto)];re-seq
    (doseq [c matches]
    (println c))
matches))

;pt4 
(def calorias-por-unidad
  {"granulated sugar" {:unidad "cups" :kcal 25}
   "vegetable oil" {:unidad "Tbsp" :kcal 80}
   "steaks" {:unidad "unit" :kcal 80}
   "garlic" {:unidad "cloves" :kcal 5}
   "garlic salad" {:unidad "dash" :kcal 40}
   "fresh rosemary" {:unidad "sprig" :kcal 1}
   "new york strip steaks" {:unidad "lbs" :kcal 242}
   "ribeye" {:unidad "lbs" :kcal 200}
   "top sirloin steaks" {:unidad "lbs" :kcal 200}
   "unsalted butter" {:unidad "Tbsp" :kcal 100}
   "black pepper" {:unidad "tsp" :kcal 13}
   "sea salt" {:unidad "teaspoons" :kcal 1}
   "extra light olive oil" {:unidad "Tbsp" :kcal 2}
   "canola oil" {:unidad "Tbsp" :kcal 1}
   "butter" {:unidad "cup" :kcal 100}
   "garlic, peeled and quartered" {:unidad "cloves" :kcal 5}
   "dry fettuccine pasta" {:unidad "ounces" :kcal 210}
   "heavy cream" {:unidad "pint" :kcal 22}
   "garlic salt" {:unidad "dash" :kcal 1}
   "grated romano cheese" {:unidad "cup" :kcal 100}
   "grated parmesan cheese" {:unidad "cup" :kcal 170}
   "all-purpose flour" {:unidad "cup" :kcal 200}
   "cocoa powder" {:unidad "cup" :kcal 10}
   "powdered sugar" {:unidad "cup" :kcal 15}
   "dark chocolate chips" {:unidad "cup" :kcal 190}
   "eggs" {:unidad "large" :kcal 50}
   "water" {:unidad "tablespoons" :kcal 1}
   "vanilla" {:unidad "teaspoon" :kcal 15}
   "extra-virgin olive oil" {:unidad "tablespoons" :kcal 15}
   "dried oregano" {:unidad "teaspoon" :kcal 15}
   "red pepper flakes" {:unidad "teaspoon" :kcal 15}
   "smoked paprika" {:unidad "teaspoon" :kcal 15}
   "finely chopped fresh flat-leaf parsley" {:unidad "cup" :kcal 15}
   "bunch of parsley" {:unidad "unit" :kcal 15}}
   )

(def calorias-en-gramos
  {"granulated sugar" {:unidad "grams" :kcal 25}
  "steaks" {:unidad "grams" :kcal 80}
   "vegetable oil" {:unidad "grams" :kcal 80}
   "garlic" {:unidad "grams" :kcal 5}
   "garlic salad" {:unidad "grams" :kcal 40}
   "fresh rosemary" {:unidad "grams" :kcal 1}
   "new york strip steaks" {:unidad "grams" :kcal 242}
   "ribeye" {:unidad "grams" :kcal 200}
   "top sirloin steaks" {:unidad "grams" :kcal 200}
   "unsalted butter" {:unidad "grams" :kcal 100}
   "black pepper" {:unidad "grams" :kcal 13}
   "sea salt" {:unidad "grams" :kcal 1}
   "extra light olive oil" {:unidad "grams" :kcal 2}
   "canola oil" {:unidad "grams" :kcal 1}
   "butter" {:unidad "grams" :kcal 100}
   "garlic, peeled and quartered" {:unidad "grams" :kcal 5}
   "dry fettuccine pasta" {:unidad "grams" :kcal 210}
   "heavy cream" {:unidad "grams" :kcal 22}
   "garlic salt" {:unidad "grams" :kcal 1}
   "grated romano cheese" {:unidad "grams" :kcal 100}
   "grated parmesan cheese" {:unidad "grams" :kcal 170}
   "all-purpose flour" {:unidad "grams" :kcal 200}
   "cocoa powder" {:unidad "grams" :kcal 10}
   "powdered sugar" {:unidad "grams" :kcal 15}
   "dark chocolate chips" {:unidad "grams" :kcal 190}
   "eggs" {:unidad "grams" :kcal 50}
   "extra-virgin olive oil" {:unidad "grams" :kcal 15}
   "dried oregano" {:unidad "grams" :kcal 15}
   "red pepper flakes" {:unidad "grams" :kcal 15}
   "smoked paprika" {:unidad "grams" :kcal 15}
   "finely chopped fresh flat-leaf parsley" {:unidad "grams" :kcal 15}
   "bunch of parsley" {:unidad "grams" :kcal 15}
   "water" {:unidad "grams" :kcal 1}
   "vanilla" {:unidad "grams" :kcal 15}})

   (def calorias-en-gram
    {"granulated sugar" {:unidad "gram" :kcal 25}
    "steaks" {:unidad "gram" :kcal 80}
     "vegetable oil" {:unidad "gram" :kcal 80}
     "garlic" {:unidad "gram" :kcal 5}
     "garlic salad" {:unidad "gram" :kcal 40}
     "fresh rosemary" {:unidad "gram" :kcal 1}
     "new york strip steaks" {:unidad "gram" :kcal 242}
     "ribeye" {:unidad "gram" :kcal 200}
     "top sirloin steaks" {:unidad "gram" :kcal 200}
     "unsalted butter" {:unidad "gram" :kcal 100}
     "black pepper" {:unidad "gram" :kcal 13}
     "sea salt" {:unidad "gram" :kcal 1}
     "extra light olive oil" {:unidad "gram" :kcal 2}
     "canola oil" {:unidad "gram" :kcal 1}
     "butter" {:unidad "gram" :kcal 100}
     "garlic, peeled and quartered" {:unidad "gram" :kcal 5}
     "dry fettuccine pasta" {:unidad "gram" :kcal 210}
     "heavy cream" {:unidad "gram" :kcal 22}
     "garlic salt" {:unidad "gram" :kcal 1}
     "grated romano cheese" {:unidad "gram" :kcal 100}
     "grated parmesan cheese" {:unidad "gram" :kcal 170}
     "all-purpose flour" {:unidad "gram" :kcal 200}
     "cocoa powder" {:unidad "gram" :kcal 10}
     "powdered sugar" {:unidad "gram" :kcal 15}
     "dark chocolate chips" {:unidad "gram" :kcal 190}
     "eggs" {:unidad "gram" :kcal 50}
     "water" {:unidad "gram" :kcal 1}
     "extra-virgin olive oil" {:unidad "gram" :kcal 15}
     "dried oregano" {:unidad "gram" :kcal 15}
     "red pepper flakes" {:unidad "gram" :kcal 15}
     "smoked paprika" {:unidad "gram" :kcal 15}
     "finely chopped fresh flat-leaf parsley" {:unidad "gram" :kcal 15}
     "bunch of parsley" {:unidad "gram" :kcal 15}
     "vanilla" {:unidad "gram" :kcal 15}})

   (defn limpiar [s]
    (-> s
    str
    clojure.string/lower-case
    (clojure.string/replace #"[^\w\s-]" "") 
    clojure.string/trim))

(def regex-pt4 #"(?i)(\d+\s*\d*/\d+|\d+/\d+|\d+)\s*(cups|cup|grams|gram|teaspoons|dash|Tbsp|tsp|cloves|sprig|ounces|teaspoon|tablespoons|tablespoon|unit|eggs?)?\s*([a-zA-Z\s\-]+)")

(defn calorias-por-ingrediente [text]
  (let [matches (re-seq regex-pt4 text)]
    (for [[_ cantidad unidad ingrediente] matches
          :let [ingrediente (limpiar ingrediente)
                unidad (if unidad (limpiar unidad) "unit")
                info (or (get calorias-por-unidad ingrediente)
              (get calorias-en-gramos ingrediente) (get calorias-en-gram ingrediente))

                kcal (if info
                       (* (fraccion-a-decimal cantidad)
                          (:kcal info))
                       0)]]
      {:ingrediente ingrediente
       :cantidad cantidad
       :unidad unidad
       :kcal kcal})))



(def archivo "Best Homemade Brownies-1.txt")
(def mitad-i (Ingredients archivo))


(defn calorias [texto]
    (let [matches (re-seq regex-pt4 texto)]
    (doseq [c matches]
    (println c))))




          
  
(defn calcular-calorias [text]
  (let [matches (re-seq regex-pt4 text)]
  (reduce +
    (for [[_ cantidad unidad ingrediente] matches
      :let [ingrediente (limpiar ingrediente)
      unidad (if unidad (limpiar unidad) "unit")
      info (or (get calorias-por-unidad ingrediente)
      (get calorias-en-gramos ingrediente) (get calorias-en-gram ingrediente))
      kcal (if info
      (* (fraccion-a-decimal cantidad)
      (:kcal info))
       0)]]
  kcal))))
          
(def cal_calorias(calorias mitad-i))
(def cal_calorfias(calorias mitad-i))

;pt 5
(defn reg-arreglaso [s]
    (clojure.string/replace s #"([\[\]\(\)\{\}\^\$\.\|\*\+\?\-\\\/])" "\\\\$1"))
  
(defn escala [text quiero-cantidad]
  (let [serves (re-find #"(?i)serves\s*[-:]?\s*(\d+)" text)
        original (if serves (Integer/parseInt (second serves)) 1) 
        ing-tex (second (re-find #"(?s)Ingredients:\s*(.*?)\s*Instructions" text))
        cantidades (cantidades-solo-fracc ing-tex)
        cantidades-decimales (map fraccion-a-decimal cantidades)
        escaladas (map #(/ (* % quiero-cantidad) original) cantidades-decimales)
        reemplazado (reduce
            (fn [t [orig esc]]
            (clojure.string/replace-first
              t
            (re-pattern (str "\\b" (reg-arreglaso orig) "\\b"))
            (format "%.2f" esc)))
              text
        (map vector cantidades escaladas))]
  (println "Porciones originales:" original)
  (println "Porciones deseadas:" quiero-cantidad)
  (println "Cantidades escaladas:" escaladas)
  reemplazado))
  
  

(def texto (slurp "Best Homemade Brownies-1.txt"))
(escala texto 8)
(def recetas (repeat 100 texto)) 

;pt8
(defn reemplazar-fahrenheit [s]
    (clojure.string/replace s
      #"(\d+(?:\.\d+)?)\s*°F"
      (fn [[_ temp]]
        (let [f (Double/parseDouble temp)
              c (Math/round (* (- f 32) 5/9))]
          (str c "°C")))))

(defn celcius-farenheit [s]
    (clojure.string/replace s
    #"(\d+(?:\.\d+)?)\s*°C"
    (fn [[_ temp]]
        (let [f (Double/parseDouble temp)
                c (Math/round (+ (* f 1.8) 32))]
            (str c "°F")))))

  (defn cups-and-grams [texto]
    (-> texto
      (clojure.string/replace #"(?i)\bcups\b" "grams")
      (clojure.string/replace #"(?i)\bcup\b" "gram")))
      
  
  (defn resaltar-linea [linea]
    (let [linea (clojure.string/trim linea)
          linea (clojure.string/replace
                  linea
                  #"\d+\s*\d*/\d+|\d+/\d+|\d+"
                  #(str "<span class='cantidad' style='color:green;'>" % "</span>"))
          linea (clojure.string/replace
                  linea
                  #"\b(?:cup|cups|gram|grams|tbsp|tablespoons|tablespoon|tsp|teaspoons|dash|teaspoon|large|clove|cloves|oz|ounce|ounces|ml|l)\b"
                  #(str "<span class='unidad' style='color:blue; font-style:italic;'>" % "</span>"))
          linea (clojure.string/replace
                  linea
                  #"\b(?:granulated sugar|all-purpose flour|cocoa powder|powdered sugar|dark chocolate chips|sea salt|eggs|canola oil|water|vanilla)\b"
                  #(str "<span class='ingrediente' style='color:red; font-style:italic;'>" % "</span>"))
          linea (clojure.string/replace
                  linea
                  #"\b(?:Best Homemade Brownies|Chimichurri Sauce|Fettuccine Alfredo|Pan-Seared Steak with Garlic Butter )\b"
                  #(str "<span class='head' style='color:yellow; font-style:italic;'>" % "</span>"))        
                  linea (clojure.string/replace
                  linea
                  #"\b(?:Ingredients|Ingredient )\b"
                  #(str "<span class='head-ingredient' style='color:purple; font-style:italic;'>" % "</span>"))
                  linea (clojure.string/replace
                  linea
                  #"\b(?:Best Homemade Brownies|Chimichurri Sauce|Fettuccine Alfredo|Pan-Seared Steak with Garlic Butter)\b"
                  #(str "<span class='head-instructions' style='color:yellow; font-style:italic;'>" % "</span>"))        
                  linea (clojure.string/replace
                  linea
                  #"\b(?:Instructions|Instruction)\b"
                  #(str "<span class='head-instructions' style='color:brown; font-style:italic;'>" % "</span>"))
                  linea (clojure.string/replace
                  linea
                  #"\b(?:°C|°F )\b"
                  #(str "<span class='temperature' style='color:yellow; font-style:italic;'>" % "</span>"))        
                  linea (clojure.string/replace
                  linea
                  #"\b(?:Instructions|Instruction)\b"
                  #(str "<span class='head-temperature' style='color:pink; font-style:italic;'>" % "</span>"))       ]
      linea))

      
(defn guardar-como-htmlw [lineas ruta]
    (let [contenido (->> lineas
        (map #(str "<p>" (resaltar-linea %) "</p>"))
        (apply str))
        html (str "<html><head><meta charset='UTF-8'><title>Ingredientes</title></head><body>" contenido "</body></html>")]
  (spit ruta html)
(println "Archivo HTML creado:" ruta)))

;pt7 

(defn filtrado-paralelo-controlado [opciones-path recetas hilos]
  (let [lineas (clojure.string/split-lines (slurp opciones-path))
        extraer (fn [clave]
                  (some #(second (re-find (re-pattern (str "^" clave ":\\s*(.+)")) %)) lineas))
        temp (or (extraer "temp") "celcius")
        porciones (try (Integer/parseInt (or (extraer "porciones") "1")) (catch Exception _ 1))
        medida (or (extraer "medida") "grams")
        filtra (or (extraer "filtra") "")
        recetas-filtradas (if (or (clojure.string/blank? filtra) (= filtra "nada"))
                            recetas
                            (filter #(clojure.string/includes? (slurp %) filtra) recetas))
        grupos (partition-all (int (Math/ceil (/ (count recetas-filtradas) hilos))) recetas-filtradas)]

    (->> grupos
         (map #(future
                 (doall
                   (map (fn [ruta]
                          (let [contenido-original (slurp ruta)
                                contenido-temp (if (= (clojure.string/lower-case temp) "celcius")
                                                 (reemplazar-fahrenheit contenido-original)
                                                 contenido-original)
                                contenido-medida (if (= (clojure.string/lower-case medida) "grams")
                                                   (cups-and-grams contenido-temp)
                                                   contenido-temp)
                                contenido-escalado (escala contenido-medida porciones)
                                lineas-ajustadas (clojure.string/split-lines contenido-escalado)
                                total-calorias (calcular-calorias contenido-medida)
                                salida (clojure.string/replace ruta #"\.txt$" "-resaltado.html")]
                            (guardar-como-htmlw lineas-ajustadas salida)
                            (spit salida (str "<p><strong>Total calorías:</strong> " total-calorias "</p>") :append true)
                            {:receta ruta :calorias total-calorias}))
                        %))))
         (map deref)
         flatten)))

         (defn medir-tiempo [f & args]
          (let [start (System/nanoTime)
                res (apply f args)
                end (System/nanoTime)]
            {:resultado res
             :duracion-ms (/ (- end start) 1e6)}))
        


;pt8
(defn filtrado-secuencial [opciones-path recetas]
  (let [lineas (clojure.string/split-lines (slurp opciones-path))
        extraer (fn [clave]
                  (some #(second (re-find (re-pattern (str "^" clave ":\\s*(.+)")) %)) lineas))
        temp (or (extraer "temp") "celcius")
        porciones (try
                    (Integer/parseInt (or (extraer "porciones") "1"))
                    (catch Exception _ 1))
        medida (or (extraer "medida") "grams")
        filtra (or (extraer "filtra") "")

        recetas-filtradas (if (or (clojure.string/blank? filtra)
                                  (= filtra "nada"))
                            recetas
                            (filter #(clojure.string/includes? (slurp %) filtra) recetas))]

    (println "Opciones leídas:")
    (println "Temp:" temp)
    (println "Porciones:" porciones)
    (println "Medida:" medida)
    (println "Filtra:" filtra)
    (println "Recetas filtradas:")
    (doseq [r recetas-filtradas] (println r))

    (doall
      (for [ruta recetas-filtradas]
        (let [contenido-original (slurp ruta)
              contenido-temp (if (= (clojure.string/lower-case temp) "celcius")
                               (reemplazar-fahrenheit contenido-original)
                               contenido-original)
              contenido-medida (if (= (clojure.string/lower-case medida) "grams")
                                 (cups-and-grams contenido-temp)
                                 contenido-temp)
              contenido-escalado (escala contenido-medida porciones)
              lineas-ajustadas (clojure.string/split-lines contenido-escalado)
              total-calorias (calcular-calorias contenido-medida)
              ingredientes-texto (regex-section contenido-medida)
              detalles-calorias (calorias-por-ingrediente ingredientes-texto)
              salida (clojure.string/replace ruta #"(.*)\/?([^\/]+)\.txt$" "salida/$2-resaltado.html")]
          (println "Procesando:" ruta)
          (guardar-como-htmlw lineas-ajustadas salida)
        (spit salida "<h3>Calorías por ingrediente:</h3>\n<ul>\n" :append true)
        (doseq [{:keys [ingrediente cantidad unidad kcal]} detalles-calorias]
          (spit salida
            (format "<li>%s: %s %s - %.2f kcal</li>\n" ingrediente cantidad unidad (double kcal)) :append true))
        (spit salida "</ul>\n" :append true)
(spit salida (str "<p><strong>Total calorías:</strong> " total-calorias "</p>\n") :append true)
          (spit salida (str "<p><strong>Total calorías:</strong> " total-calorias "</p>") :append true)
          {:receta ruta :calorias total-calorias})))))
;/////////////////
(defn analizar-desempeno [opciones-path recetas max-hilos]
  (let [tiempo-seq (:duracion-ms (medir-tiempo filtrado-secuencial opciones-path recetas))]
    (println "Tiempo secuencial (ms):" tiempo-seq)
    (doseq [hilos (range 1 (inc max-hilos))]
      (let [res (medir-tiempo filtrado-paralelo-controlado opciones-path recetas hilos)
            tiempo-par (:duracion-ms res)
            speedup (/ tiempo-seq tiempo-par)
            eficiencia (/ speedup hilos)]
        (println (format "Hilos: %d | Tiempo paralelizado: %.2f ms | SpeedUp: %.2f | Eficiencia: %.2f"
                         hilos tiempo-par speedup eficiencia))))))

 (def recetas ["Best Homemade Brownies-1.txt"
  "Chimichurri Sauce.txt"
  "Fettuccine Alfredo.txt"
  "Pan-Seared Steak with Garlic Butter.txt"])       
  ;; (def recetas
  ;;   (apply concat (repeat 100
  ;;     ["Best Homemade Brownies-1.txt"
  ;;      "Chimichurri Sauce.txt"
  ;;      "Fettuccine Alfredo.txt"
  ;;      "Pan-Seared Steak with Garlic Butter.txt"])))
  (def opciones-path "opciones.txt")                

  (analizar-desempeno "opciones.txt"
    ["Best Homemade Brownies-1.txt"
     "Chimichurri Sauce.txt"
     "Fettuccine Alfredo.txt"
     "Pan-Seared Steak with Garlic Butter.txt"]4)
     
     (filtrado-secuencial "opciones.txt"
      ["Best Homemade Brownies-1.txt"
       "Chimichurri Sauce.txt"
       "Fettuccine Alfredo.txt"
       "Pan-Seared Steak with Garlic Butter.txt"])

       (println(medir-tiempo filtrado-secuencial opciones-path recetas)) 


;opciones
;temp: celcius
;filtra: sugar
;porciones: 8
;medida: grams

;temp: Farenhain
;filtra: cualquier palabra
;porciones: cualquier numero
;medida: grams / cups










