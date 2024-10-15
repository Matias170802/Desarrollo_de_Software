import random
import json

def generar_dna(n, incluir_mutante=True):
    """Genera una secuencia de ADN de tamaño NxN. 
    Si incluir_mutante es True, la secuencia puede ser mutante."""
    letras = ["A", "T", "C", "G"]
    dna = ['' for _ in range(n)]  # Inicializar una lista con n filas vacías

    if incluir_mutante:
        combinaciones = 0
        
        # Completar el resto de las filas con cadenas aleatorias
        for i in range(0, n):  
            if dna[i] == '':  # Si la fila está vacía
                fila = ''.join(random.choices(letras, k=n))
                dna[i] = fila

        letra = random.choice(letras)  # Elegir una letra para las secuencias iguales

        # Generar una fila con cuatro letras iguales
        fila_mutante = letra * 4 + ''.join(random.choices(letras, k=n-4))
        dna[0] = fila_mutante  # Guardar la fila mutante en la primera posición

        # Asegurarse de que la matriz esté completa antes de agregar columnas
        for i in range(4):
            if len(dna) <= i:  # Si no hay suficientes filas, inicializa con vacías
                dna.append([''] * n)  # Inicializar si no existe
            dna[i] = dna[i][:len(dna)-1] + letra + dna[i][len(dna):]  # Agregar letra en la columna
            combinaciones += 1  # Incrementar las combinaciones

    else:
        # Generar filas no mutantes
        for i in range(0, n):  # Empezar desde la segunda fila
            if dna[i] == '':  # Si la fila está vacía
                fila = ''.join(random.choices(letras, k=n))
                dna[i] = fila
    return dna

def crear_conjunto_datos(num_ejemplos, n):
    datos = []
    etiquetas = []  # Lista para almacenar las etiquetas

    for _ in range(num_ejemplos):
        if random.choice([True, False]):  # Elegir aleatoriamente si incluir un mutante 
            dna = generar_dna(n, incluir_mutante=True)
            datos.append(dna)  # Se agrega la lista de cadenas
            etiquetas.append(1)  # Etiqueta True para mutante

        else:
            dna = generar_dna(n, incluir_mutante=False)
            datos.append(dna)  # Se agrega la lista de cadenas
            etiquetas.append(0)  # Etiqueta False para no mutante

    return datos, etiquetas

# Generar un conjunto de datos
num_ejemplos = 10000  # Número total de ejemplos
n = random.randrange(4, 10)  # Tamaño de la matriz NxN
datos, etiquetas = crear_conjunto_datos(num_ejemplos, n)

# Guardar los datos en un archivo JSON
conjunto_datos = [{'secuencia': dna, 'etiqueta': etiqueta} for dna, etiqueta in zip(datos, etiquetas)]

with open('conjunto_datos_mutantes.json', 'w') as f:
    json.dump(conjunto_datos, f, indent=4)  # Guardar el conjunto de datos en formato JSON con indentación

print("Conjunto de datos generado y guardado en 'conjunto_datos_mutantes.json'.")
